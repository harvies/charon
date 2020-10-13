## Launcher
### 介绍
sun.misc.Launcher类是java的入口，在启动java应用的时候会首先创建Launcher类，创建Launcher类的时候会准备应用程序运行中需要的类加载器。
Launcher作为JAVA应用的入口，根据双亲委派模型，Launcher是由JVM创建的，它类加载器应该是BootStrapClassLoader， 这是一个C++编写的类加载器，是java应用体系中最顶层的类加载器，负责加载JVM需要的一些类库(<JAVA_HOME>/lib)。可以通过一个简单的代码验证一下我们的想法。
```java
import sun.misc.Launcher;

public class LauncherTest {
    public static void main(String[] args) {
        ClassLoader classLoader = Launcher.class.getClassLoader();
        System.out.println(classLoader); //null
    }
}
```
以上代码运行会输出null，说明Launcher确实是BootstrapClassLoader加载的

### 构造方法
```java
public Launcher() {
        Launcher.ExtClassLoader var1;
        try {
            //创建ExtClassLoader
            var1 = Launcher.ExtClassLoader.getExtClassLoader();
        } catch (IOException var10) {
            throw new InternalError("Could not create extension class loader", var10);
        }

        try {
            //创建AppClassLoader的父亲为ExtClassLoader
            this.loader = Launcher.AppClassLoader.getAppClassLoader(var1);
        } catch (IOException var9) {
            throw new InternalError("Could not create application class loader", var9);
        }
        //设置当前线程上下文类加载器为AppClassLoader
        Thread.currentThread().setContextClassLoader(this.loader);
        String var2 = System.getProperty("java.security.manager");
        if (var2 != null) {
            SecurityManager var3 = null;
            if (!"".equals(var2) && !"default".equals(var2)) {
                try {
                    var3 = (SecurityManager)this.loader.loadClass(var2).newInstance();
                } catch (IllegalAccessException var5) {
                } catch (InstantiationException var6) {
                } catch (ClassNotFoundException var7) {
                } catch (ClassCastException var8) {
                }
            } else {
                var3 = new SecurityManager();
            }

            if (var3 == null) {
                throw new InternalError("Could not create SecurityManager: " + var2);
            }

            System.setSecurityManager(var3);
        }

    }
```
从以上代码可以看出，会先创建ExtClassLoader，再创建AppClassLoader

## ExtClassLoader
### 介绍
sun.misc.Launcher.ExtClassLoader是sun.misc.Launcher的静态内部类，是一个拓展类加载器
### 加载哪些类？

```java
private static File[] getExtDirs() {
            String var0 = System.getProperty("java.ext.dirs");
            File[] var1;
            if (var0 != null) {
                StringTokenizer var2 = new StringTokenizer(var0, File.pathSeparator);
                int var3 = var2.countTokens();
                var1 = new File[var3];

                for(int var4 = 0; var4 < var3; ++var4) {
                    var1[var4] = new File(var2.nextToken());
                }
            } else {
                var1 = new File[0];
            }

            return var1;
        }
```
通过查看源码，ExtClassLoader会加载系统属性为java.ext.dirs的类

通过代码打印出路径
```java

import java.io.File;
import java.util.StringTokenizer;

public class ExtClassLoaderTest {

    public static void main(String[] args) {
        System.out.println("ext class load----------------------");
        final String s = System.getProperty("java.ext.dirs");//对应路径
        System.out.println(s);
        System.out.println("-----------");

        File[] dirs;
        if (s != null) {
            StringTokenizer st =
                    new StringTokenizer(s, File.pathSeparator);
            int count = st.countTokens();
            dirs = new File[count];
            for (int i = 0; i < count; i++) {
                dirs[i] = new File(st.nextToken());
            }
        } else {
            dirs = new File[0];
        }

        for (File f : dirs) {
            System.out.println(f.getAbsolutePath());
        }
    }
}

```
打印结果
```shell script
ext class load----------------------
/Users/harvies/Library/Java/Extensions:/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/ext:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java
-----------
/Users/harvies/Library/Java/Extensions
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/ext
/Library/Java/Extensions
/Network/Library/Java/Extensions
/System/Library/Java/Extensions
/usr/lib/java
```
看下/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/ext下的文件
```shell script
 ~/IdeaProjects/charon   master ●✚  ll /Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/ext
total 51560
-rw-r--r--  1 root  wheel   3.7M Oct  5  2019 cldrdata.jar
-rw-r--r--  1 root  wheel   8.1K Oct  5  2019 dnsns.jar
-rw-r--r--  1 root  wheel    43K Oct  5  2019 jaccess.jar
-rw-r--r--  1 root  wheel    18M Sep 11  2019 jfxrt.jar
-rw-r--r--  1 root  wheel   1.1M Oct  5  2019 localedata.jar
-rw-r--r--  1 root  wheel   1.2K Oct  5  2019 meta-index
-rw-r--r--  1 root  wheel   1.9M Oct  5  2019 nashorn.jar
-rw-r--r--  1 root  wheel    59K Oct  5  2019 sunec.jar
-rw-r--r--  1 root  wheel   274K Oct  5  2019 sunjce_provider.jar
-rw-r--r--  1 root  wheel   248K Oct  5  2019 sunpkcs11.jar
-rw-r--r--  1 root  wheel    67K Oct  5  2019 zipfs.jar

```
最终我们知道了，ExtClassLoader主要是用来加载jre/lib/ext目录下的类

## AppClassLoader
### 介绍
sun.misc.Launcher.AppClassLoader是sun.misc.Launcher的静态内部类，是一个应用类加载器
### 加载哪些类？
```java
   public static ClassLoader getAppClassLoader(final ClassLoader var0) throws IOException {
            final String var1 = System.getProperty("java.class.path");
            final File[] var2 = var1 == null ? new File[0] : Launcher.getClassPath(var1);
            return (ClassLoader)AccessController.doPrivileged(new PrivilegedAction<Launcher.AppClassLoader>() {
                public Launcher.AppClassLoader run() {
                    URL[] var1x = var1 == null ? new URL[0] : Launcher.pathToURLs(var2);
                    return new Launcher.AppClassLoader(var1x, var0);
                }
            });
        }
```
通过查看源码会加载系统属性为java.class.path下的类
```java
public class AppClassLoaderTest {

    public static void main(String[] args) {
        System.out.println("app class load----------------------");
        final String s = System.getProperty("java.class.path");
        System.out.println(s);
    }
}
```
输出结果
```shell script
app class load----------------------
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/charsets.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/deploy.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/ext/cldrdata.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/ext/dnsns.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/ext/jaccess.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/ext/jfxrt.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/ext/localedata.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/ext/nashorn.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/ext/sunec.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/ext/zipfs.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/javaws.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/jce.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/jfr.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/jfxswt.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/jsse.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/management-agent.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/plugin.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/resources.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre/lib/rt.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/lib/ant-javafx.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/lib/dt.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/lib/javafx-mx.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/lib/jconsole.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/lib/packager.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/lib/sa-jdi.jar
/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/lib/tools.jar
/Users/harvies/IdeaProjects/charon/charon-tests/target/test-classes
/Users/harvies/IdeaProjects/charon/charon-tests/target/classes
/Users/harvies/IdeaProjects/charon/charon-core/target/classes
/Users/harvies/.m2/repository/org/mapstruct/mapstruct/1.3.1.Final/mapstruct-1.3.1.Final.jar
/Users/harvies/.m2/repository/org/slf4j/slf4j-api/1.7.26/slf4j-api-1.7.26.jar
/Users/harvies/IdeaProjects/charon/charon-extra/target/classes
/Users/harvies/IdeaProjects/charon/charon-json/target/classes
/Users/harvies/.m2/repository/com/alibaba/fastjson/1.2.72/fastjson-1.2.72.jar
/Users/harvies/.m2/repository/org/dom4j/dom4j/2.0.0/dom4j-2.0.0.jar
/Users/harvies/.m2/repository/jaxen/jaxen/1.2.0/jaxen-1.2.0.jar
/Users/harvies/IdeaProjects/charon/charon-file/charon-picture/target/classes
/Users/harvies/.m2/repository/net/coobird/thumbnailator/0.4.12/thumbnailator-0.4.12.jar
/Users/harvies/IdeaProjects/charon/charon-file/charon-excel/target/classes
/Users/harvies/.m2/repository/com/alibaba/easyexcel/2.2.6/easyexcel-2.2.6.jar
/Users/harvies/.m2/repository/com/sun/istack/istack-commons-runtime/3.0.11/istack-commons-runtime-3.0.11.jar
/Users/harvies/IdeaProjects/charon/charon-http/target/classes
/Users/harvies/.m2/repository/net/dongliu/requests/5.0.7/requests-5.0.7.jar
/Users/harvies/.m2/repository/net/dongliu/commons/6.10.8/commons-6.10.8.jar
/Users/harvies/IdeaProjects/charon/charon-log/target/classes
/Users/harvies/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar
/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar
```
可以看出AppClassLoader可加载/lib/,/jre/lib/,/jre/lib/ext/,classpath下的类

## 参考
https://blog.csdn.net/feiyingHiei/article/details/86553614 