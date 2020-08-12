## 线上问题
### Controller同步处理请求导致tomcat线程耗尽
打单项目在进入订单页面时会去调用淘宝接口更新订单数据，某天客户反馈打不开，切到线上环境打开提示Nginx 504 网关超时
jstat -gcutil pid time 查了下内存没问题，jstck pid 导出了jstack重启了项目。
猜测是tomcat出问题了，查了下tomcat线程名"http-nio-端口-exec"，tomcat8以上默认nio，刚好200个，都是
![image](https://raw.githubusercontent.com/harvies/oss/master/2019/10/tomcat-thread.0o47qir7fobi.jpg)
后面改为异步解决


## 知识点
### 类加载器双亲委派模型机制？
当一个类收到了类加载请求,他首先不会尝试自己去加载这个类,而是把这个请求委派给父
类去完成,每一个层次类加载器都是如此,因此所有的加载请求都应该传送到启动类加载其中,
只有当父类加载器反馈自己无法完成这个请求的时候(在它的加载路径下没有找到所需加载的
Class),子类加载器才会尝试自己去加载。

采用双亲委派的一个好处是比如加载位于 rt.jar 包中的类 java.lang.Object,不管是哪个加载
器加载这个类,最终都是委托给顶层的启动类加载器进行加载,这样就保证了使用不同的类加载
器最终得到的都是同样一个 Object 对象。
![alt text](images/jvm/ClassLoad.png "ClassLoad")

