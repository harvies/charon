package io.github.harvies.charon.util.lang;

import java.util.Map;
import java.util.Properties;

/**
 * 系统变量与应用变量
 *
 * @author harvies
 */
public class SystemTest {
    public static void main(String[] args) {
        Map<String, String> env = System.getenv();
        System.out.println("---开始输出系统变量---");
        env.forEach((s, s2) -> System.out.println(s + "=" + s2));
        System.out.println("---系统变量输出完毕---");


        Properties properties = System.getProperties();
        System.out.println("---开始输出应用变量---");
        properties.forEach((s, s2) -> System.out.println(s + "=" + s2));
        System.out.println("---应用变量输出完毕---");

        /**
         * ---开始输出系统变量---
         * PATH=/usr/local/bin:/usr/local/sbin:/usr/bin:/opt/activemq/bin/linux-x86-64:/usr/lib/jvm/default/bin:/usr/bin/site_perl:/usr/bin/vendor_perl:/usr/bin/core_perl
         * XAUTHORITY=/run/user/1000/gdm/Xauthority
         * XMODIFIERS=@im=fcitx
         * XDG_DATA_DIRS=/home/harvies/.local/share/flatpak/exports/share/:/var/lib/flatpak/exports/share/:/usr/local/share/:/usr/share/
         * GDMSESSION=gnome-xorg
         * QT_IM_MODULE=fcitx
         * GTK_IM_MODULE=fcitx
         * LANG=en_US.UTF-8
         * DBUS_SESSION_BUS_ADDRESS=unix:path=/run/user/1000/bus
         * XDG_SESSION_TYPE=x11
         * XDG_SESSION_ID=7
         * GRADLE_HOME=/usr/share/java/gradle
         * XDG_CURRENT_DESKTOP=GNOME
         * DISPLAY=:0
         * MAIL=/var/spool/mail/harvies
         * VDPAU_DRIVER=va_gl
         * MOZ_PLUGIN_PATH=/usr/lib/mozilla/plugins
         * ACTIVEMQ_HOME=/opt/activemq
         * SESSION_MANAGER=local/archlinux:@/tmp/.ICE-unix/2663,unix/archlinux:/tmp/.ICE-unix/2663
         * USERNAME=harvies
         * LOGNAME=harvies
         * PWD=/home/harvies/IdeaProjects/eris
         * XDG_SESSION_CLASS=user
         * _=/usr/lib/jvm/java-8-jdk/bin/java
         * GJS_DEBUG_TOPICS=JS ERROR;JS LOG
         * SHELL=/bin/zsh
         * GDM_LANG=en_US.UTF-8
         * GIO_LAUNCHED_DESKTOP_FILE=/home/harvies/.local/share/applications/jetbrains-idea.desktop
         * DESKTOP_SESSION=gnome-xorg
         * OLDPWD=/opt/intellij-idea-ultimate-edition/bin
         * USER=harvies
         * XDG_MENU_PREFIX=gnome-
         * GIO_LAUNCHED_DESKTOP_FILE_PID=133282
         * WINDOWPATH=7
         * GJS_DEBUG_OUTPUT=stderr
         * SSH_AUTH_SOCK=/run/user/1000/keyring/ssh
         * XDG_SEAT=seat0
         * XDG_SESSION_DESKTOP=gnome-xorg
         * XDG_VTNR=7
         * XDG_RUNTIME_DIR=/run/user/1000
         * HOME=/home/harvies
         * SHLVL=1
         * ---系统变量输出完毕---
         * ---开始输出应用变量---
         * sun.cpu.isalist=
         * sun.io.unicode.encoding=UnicodeLittle
         * sun.cpu.endian=little
         * java.vendor.url.bug=http://bugreport.sun.com/bugreport/
         * file.separator=/
         * java.vendor=Oracle Corporation
         * sun.boot.class.path=/usr/lib/jvm/java-8-jdk/jre/lib/resources.jar:/usr/lib/jvm/java-8-jdk/jre/lib/rt.jar:/usr/lib/jvm/java-8-jdk/jre/lib/sunrsasign.jar:/usr/lib/jvm/java-8-jdk/jre/lib/jsse.jar:/usr/lib/jvm/java-8-jdk/jre/lib/jce.jar:/usr/lib/jvm/java-8-jdk/jre/lib/charsets.jar:/usr/lib/jvm/java-8-jdk/jre/lib/jfr.jar:/usr/lib/jvm/java-8-jdk/jre/classes
         * java.ext.dirs=/usr/lib/jvm/java-8-jdk/jre/lib/ext:/usr/java/packages/lib/ext
         * java.version=1.8.0_202
         * java.vm.info=mixed mode
         * awt.toolkit=sun.awt.X11.XToolkit
         * user.language=en
         * java.specification.vendor=Oracle Corporation
         * sun.java.command=io.github.harvies.eris.base.jdk.lang.SystemTest
         * java.home=/usr/lib/jvm/java-8-jdk/jre
         * sun.arch.data.model=64
         * java.vm.specification.version=1.8
         * java.class.path=/usr/lib/jvm/java-8-jdk/jre/lib/charsets.jar:/usr/lib/jvm/java-8-jdk/jre/lib/deploy.jar:/usr/lib/jvm/java-8-jdk/jre/lib/ext/cldrdata.jar:/usr/lib/jvm/java-8-jdk/jre/lib/ext/dnsns.jar:/usr/lib/jvm/java-8-jdk/jre/lib/ext/jaccess.jar:/usr/lib/jvm/java-8-jdk/jre/lib/ext/jfxrt.jar:/usr/lib/jvm/java-8-jdk/jre/lib/ext/localedata.jar:/usr/lib/jvm/java-8-jdk/jre/lib/ext/nashorn.jar:/usr/lib/jvm/java-8-jdk/jre/lib/ext/sunec.jar:/usr/lib/jvm/java-8-jdk/jre/lib/ext/sunjce_provider.jar:/usr/lib/jvm/java-8-jdk/jre/lib/ext/sunpkcs11.jar:/usr/lib/jvm/java-8-jdk/jre/lib/ext/zipfs.jar:/usr/lib/jvm/java-8-jdk/jre/lib/javaws.jar:/usr/lib/jvm/java-8-jdk/jre/lib/jce.jar:/usr/lib/jvm/java-8-jdk/jre/lib/jfr.jar:/usr/lib/jvm/java-8-jdk/jre/lib/jfxswt.jar:/usr/lib/jvm/java-8-jdk/jre/lib/jsse.jar:/usr/lib/jvm/java-8-jdk/jre/lib/management-agent.jar:/usr/lib/jvm/java-8-jdk/jre/lib/plugin.jar:/usr/lib/jvm/java-8-jdk/jre/lib/resources.jar:/usr/lib/jvm/java-8-jdk/jre/lib/rt.jar:/home/harvies/IdeaProjects/eris/base/target/classes:/home/harvies/.m2/repository/org/apache/commons/commons-collections4/4.2/commons-collections4-4.2.jar:/home/harvies/.m2/repository/commons-beanutils/commons-beanutils/1.9.3/commons-beanutils-1.9.3.jar:/home/harvies/.m2/repository/commons-logging/commons-logging/1.2/commons-logging-1.2.jar:/home/harvies/.m2/repository/commons-collections/commons-collections/3.2.2/commons-collections-3.2.2.jar:/home/harvies/.m2/repository/commons-codec/commons-codec/1.11/commons-codec-1.11.jar:/home/harvies/.m2/repository/org/apache/commons/commons-text/1.6/commons-text-1.6.jar:/home/harvies/.m2/repository/com/google/guava/guava/27.1-jre/guava-27.1-jre.jar:/home/harvies/.m2/repository/com/google/guava/failureaccess/1.0.1/failureaccess-1.0.1.jar:/home/harvies/.m2/repository/com/google/guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar:/home/harvies/.m2/repository/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar:/home/harvies/.m2/repository/org/checkerframework/checker-qual/2.5.2/checker-qual-2.5.2.jar:/home/harvies/.m2/repository/com/google/errorprone/error_prone_annotations/2.2.0/error_prone_annotations-2.2.0.jar:/home/harvies/.m2/repository/com/google/j2objc/j2objc-annotations/1.1/j2objc-annotations-1.1.jar:/home/harvies/.m2/repository/org/codehaus/mojo/animal-sniffer-annotations/1.17/animal-sniffer-annotations-1.17.jar:/home/harvies/IdeaProjects/eris/core/target/classes:/home/harvies/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:/home/harvies/.m2/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar:/home/harvies/.m2/repository/com/alibaba/fastjson/1.2.54/fastjson-1.2.54.jar:/home/harvies/.m2/repository/commons-io/commons-io/2.6/commons-io-2.6.jar:/home/harvies/.m2/repository/com/github/oshi/oshi-core/3.13.3/oshi-core-3.13.3.jar:/home/harvies/.m2/repository/net/java/dev/jna/jna/4.5.2/jna-4.5.2.jar:/home/harvies/.m2/repository/net/java/dev/jna/jna-platform/4.5.2/jna-platform-4.5.2.jar:/home/harvies/.m2/repository/com/netflix/hystrix/hystrix-core/1.5.18/hystrix-core-1.5.18.jar:/home/harvies/.m2/repository/com/netflix/archaius/archaius-core/0.4.1/archaius-core-0.4.1.jar:/home/harvies/.m2/repository/commons-configuration/commons-configuration/1.8/commons-configuration-1.8.jar:/home/harvies/.m2/repository/commons-lang/commons-lang/2.6/commons-lang-2.6.jar:/home/harvies/.m2/repository/io/reactivex/rxjava/1.3.8/rxjava-1.3.8.jar:/home/harvies/.m2/repository/org/hdrhistogram/HdrHistogram/2.1.9/HdrHistogram-2.1.9.jar:/home/harvies/.m2/repository/org/slf4j/slf4j-api/1.7.26/slf4j-api-1.7.26.jar:/home/harvies/.m2/repository/org/apache/commons/commons-lang3/3.8.1/commons-lang3-3.8.1.jar:/opt/intellij-idea-ultimate-edition/lib/idea_rt.jar
         * user.name=harvies
         * file.encoding=UTF-8
         * java.specification.version=1.8
         * java.awt.printerjob=sun.print.PSPrinterJob
         * user.timezone=
         * user.home=/home/harvies
         * os.version=5.2.14-arch2-1-ARCH
         * sun.management.compiler=HotSpot 64-Bit Tiered Compilers
         * java.specification.name=Java Platform API Specification
         * java.class.version=52.0
         * java.library.path=/usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib
         * sun.jnu.encoding=UTF-8
         * os.name=Linux
         * java.vm.specification.vendor=Oracle Corporation
         * java.io.tmpdir=/tmp
         * line.separator=
         *
         * java.endorsed.dirs=/usr/lib/jvm/java-8-jdk/jre/lib/endorsed
         * os.arch=amd64
         * java.awt.graphicsenv=sun.awt.X11GraphicsEnvironment
         * java.runtime.version=1.8.0_202-b08
         * java.vm.specification.name=Java Virtual Machine Specification
         * user.dir=/home/harvies/IdeaProjects/eris
         * user.country=US
         * sun.java.launcher=SUN_STANDARD
         * sun.os.patch.level=unknown
         * java.vm.name=Java HotSpot(TM) 64-Bit Server VM
         * file.encoding.pkg=sun.io
         * path.separator=:
         * java.vm.vendor=Oracle Corporation
         * java.vendor.url=http://java.oracle.com/
         * sun.boot.library.path=/usr/lib/jvm/java-8-jdk/jre/lib/amd64
         * java.vm.version=25.202-b08
         * java.runtime.name=Java(TM) SE Runtime Environment
         * ---应用变量输出完毕---
         */
    }
}
