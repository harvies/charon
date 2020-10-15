> 本文基于SpringBoot2.3.3

## SpringBoot应用启动入口

```java
@SpringBootApplication
public class CharonSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(CharonSpringBootApplication.class, args);
    }
}
```

## 创建SpringApplication实例

```java
	public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
		//默认传的null
    this.resourceLoader = resourceLoader;
		Assert.notNull(primarySources, "PrimarySources must not be null");
    //入口类
		this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
    //检测web应用类型，see WebApplicationType 有NONE,SERVLET,REACTIVE三种类型
		this.webApplicationType = WebApplicationType.deduceFromClasspath();
    //创建ApplicationContextInitializer实现类
		setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
    //创建ApplicationListener实现类
		setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
    //检测应用入口类
		this.mainApplicationClass = deduceMainApplicationClass();
	}
```

### 获取Spring工厂实例

```java
	private <T> Collection<T> getSpringFactoriesInstances(Class<T> type, Class<?>[] parameterTypes, Object... args) {
		ClassLoader classLoader = getClassLoader();
		// Use names and ensure unique to protect against duplicates
    //从“META-INF/spring”加载给定类型的工厂实现的完全限定类名
		Set<String> names = new LinkedHashSet<>(SpringFactoriesLoader.loadFactoryNames(type, classLoader));
    //创建Spring工厂实现
		List<T> instances = createSpringFactoriesInstances(type, parameterTypes, classLoader, args, names);
		//按优先级排序
    AnnotationAwareOrderComparator.sort(instances);
		return instances;
	}
```

### 创建Spring工厂实例

```java
private <T> List<T> createSpringFactoriesInstances(Class<T> type, Class<?>[] parameterTypes,
			ClassLoader classLoader, Object[] args, Set<String> names) {
		List<T> instances = new ArrayList<>(names.size());
		for (String name : names) {
			try {
				Class<?> instanceClass = ClassUtils.forName(name, classLoader);
				Assert.isAssignable(type, instanceClass);
				Constructor<?> constructor = instanceClass.getDeclaredConstructor(parameterTypes);
				T instance = (T) BeanUtils.instantiateClass(constructor, args);
				instances.add(instance);
			}
			catch (Throwable ex) {
				throw new IllegalArgumentException("Cannot instantiate " + type + " : " + name, ex);
			}
		}
		return instances;
	}
```

### 检测入口类

通过获取当前线程堆栈找到当前正在执行main方法的类

```java
	private Class<?> deduceMainApplicationClass() {
		try {
			StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
			for (StackTraceElement stackTraceElement : stackTrace) {
				if ("main".equals(stackTraceElement.getMethodName())) {
					return Class.forName(stackTraceElement.getClassName());
				}
			}
		}
		catch (ClassNotFoundException ex) {
			// Swallow and continue
		}
		return null;
	}
```

## 运行
org.springframework.boot.SpringApplication.run(java.lang.String...)
```java
public ConfigurableApplicationContext run(String... args) {
   StopWatch stopWatch = new StopWatch();
   //启动计时器
   stopWatch.start();
   ConfigurableApplicationContext context = null;
   //异常信息
   Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
   //设置Headless属性，默认true
   configureHeadlessProperty();
   //从META-INF/spring.factories加载并创建 SpringApplicationRunListener 实现类
   SpringApplicationRunListeners listeners = getRunListeners(args);
   //调用所有SpringApplicationRunListener实现类的starting方法
   listeners.starting();
   try {
      ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
      //准备环境信息(配置文件加载等)
      ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
      //todo 暂不知道用途
      configureIgnoreBeanInfo(environment);
      //打印banner信息
      Banner printedBanner = printBanner(environment);
      //通过反射创建Spring上下文，默认是 org.springframework.context.annotation.AnnotationConfigApplicationContext
      context = createApplicationContext();
      //从META-INF/spring.factories加载并创建 异常报告 SpringBootExceptionReporter 实现类(默认是 FailureAnalyzers )
      exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class,
            new Class[] { ConfigurableApplicationContext.class }, context);
      //准备上下文
      prepareContext(context, environment, listeners, applicationArguments, printedBanner);
      //刷新上下文
      refreshContext(context);
      //后置处理
      afterRefresh(context, applicationArguments);
      //停止计时
      stopWatch.stop();
      //打印启动日志
      if (this.logStartupInfo) {
         new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
      }
      //发送 ApplicationStartedEvent ，AvailabilityChangeEvent 准备就绪状态 事件
      listeners.started(context);
      //调用ApplicationRunner/CommandLineRunner run方法
      callRunners(context, applicationArguments);
   }
   catch (Throwable ex) {
      //启动异常处理，注册 SpringBootExceptionHandler，发送 ExitCodeEvent 事件
      handleRunFailure(context, ex, exceptionReporters, listeners);
      throw new IllegalStateException(ex);
   }

   try {
      //发送 ApplicationReadyEvent 事件
      listeners.running(context);
   }
   catch (Throwable ex) {
      handleRunFailure(context, ex, exceptionReporters, null);
      throw new IllegalStateException(ex);
   }
   return context;
}
```

### Initializers

默认有7个Initializer,CharonApplicationContextInitializer 是笔者自己定义的

![](https://harvies-oss.oss-cn-hangzhou.aliyuncs.com/2020/10/15/20201615111600004-image.png)

### Listeners

默认有11个Listener,CharonApplicationListener 是笔者自定义的

![](https://harvies-oss.oss-cn-hangzhou.aliyuncs.com/2020/10/15/20201215111200026-image.png)


### 准备环境信息
org.springframework.boot.SpringApplication.prepareEnvironment

```java
private ConfigurableEnvironment prepareEnvironment(SpringApplicationRunListeners listeners,
      ApplicationArguments applicationArguments) {
   // Create and configure the environment
   ConfigurableEnvironment environment = getOrCreateEnvironment();
   configureEnvironment(environment, applicationArguments.getSourceArgs());
   ConfigurationPropertySources.attach(environment);
   //环境信息准备(配置文件加载等)
   listeners.environmentPrepared(environment);
   //从环境信息里绑定参数到SpringApplication对象
   bindToSpringApplication(environment);
   if (!this.isCustomEnvironment) {
      environment = new EnvironmentConverter(getClassLoader()).convertEnvironmentIfNecessary(environment,
            deduceEnvironmentClass());
   }
   ConfigurationPropertySources.attach(environment);
   return environment;
}
```
### 环境信息准备
广播发送 ApplicationEnvironmentPreparedEvent 事件
```java
@Override
	public void environmentPrepared(ConfigurableEnvironment environment) {
		this.initialMulticaster
				.multicastEvent(new ApplicationEnvironmentPreparedEvent(this.application, this.args, environment));
	}
```
接收 ApplicationEnvironmentPreparedEvent 事件的类
![](https://harvies-oss.oss-cn-hangzhou.aliyuncs.com/2020/10/15/20204415114400028-image.png)
ConfigFileApplicationListener 接收到 ApplicationEnvironmentPreparedEvent 事件后的处理流程
```java
	private void onApplicationEnvironmentPreparedEvent(ApplicationEnvironmentPreparedEvent event) {
        //从META-INF/spring.factories中加载EnvironmentPostProcessor实现类
		List<EnvironmentPostProcessor> postProcessors = loadPostProcessors();
        //添加自身
		postProcessors.add(this);
        //排序
		AnnotationAwareOrderComparator.sort(postProcessors);
        //循环执行EnvironmentPostProcessor的postProcessEnvironment方法
		for (EnvironmentPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessEnvironment(event.getEnvironment(), event.getSpringApplication());
		}
	}
```
![](https://harvies-oss.oss-cn-hangzhou.aliyuncs.com/2020/10/15/20203315143300011-image.png)
application.properties & application.yaml文件即是在此时被加载，我们可以通过实现 EnvironmentPostProcessor 接口实现加载自定义配置文件
```java
	protected void addPropertySources(ConfigurableEnvironment environment, ResourceLoader resourceLoader) {
		RandomValuePropertySource.addToEnvironment(environment);
		new Loader(environment, resourceLoader).load();
	}
```
可通过实现 PropertySourceLoader 接口，并配置到 META-INF/spring.factories 中即可定义其他格式配置文件(例如json)
```java
	Loader(ConfigurableEnvironment environment, ResourceLoader resourceLoader) {
			this.environment = environment;
			this.placeholdersResolver = new PropertySourcesPlaceholdersResolver(this.environment);
			this.resourceLoader = (resourceLoader != null) ? resourceLoader : new DefaultResourceLoader(null);
			this.propertySourceLoaders = SpringFactoriesLoader.loadFactories(PropertySourceLoader.class,
					getClass().getClassLoader());
		}
```
![](https://harvies-oss.oss-cn-hangzhou.aliyuncs.com/2020/10/15/20204515144500056-image.png)

通过Binder类可实现环境信息自动映射到指定类
Binder.get(environment).bind("spring.output.ansi.enabled", AnsiOutput.Enabled.class)

![](https://harvies-oss.oss-cn-hangzhou.aliyuncs.com/2020/10/15/20201615151600047-image.png)


### createApplicationContext


### prepareContext
```java
private void prepareContext(ConfigurableApplicationContext context, ConfigurableEnvironment environment,
			SpringApplicationRunListeners listeners, ApplicationArguments applicationArguments, Banner printedBanner) {
		context.setEnvironment(environment);
		postProcessApplicationContext(context);
		applyInitializers(context);
		listeners.contextPrepared(context);
		if (this.logStartupInfo) {
			logStartupInfo(context.getParent() == null);
			logStartupProfileInfo(context);
		}
		// Add boot specific singleton beans
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		beanFactory.registerSingleton("springApplicationArguments", applicationArguments);
		if (printedBanner != null) {
			beanFactory.registerSingleton("springBootBanner", printedBanner);
		}
		if (beanFactory instanceof DefaultListableBeanFactory) {
			((DefaultListableBeanFactory) beanFactory)
					.setAllowBeanDefinitionOverriding(this.allowBeanDefinitionOverriding);
		}
		if (this.lazyInitialization) {
			context.addBeanFactoryPostProcessor(new LazyInitializationBeanFactoryPostProcessor());
		}
		// Load the sources
		Set<Object> sources = getAllSources();
		Assert.notEmpty(sources, "Sources must not be empty");
		load(context, sources.toArray(new Object[0]));
		listeners.contextLoaded(context);
	}
```


### refreshContext


### afterRefresh