## Spring事务实现原理
简单讲下 Spring事务是通过AOP实现 ，AOP是通过动态代理实现，Controller里调用加了加了@Transaction注解的Service方法时，
Spring会为Service生成代理对象，代理对象在方法执行前打开事务，执行完提交/回滚事务
