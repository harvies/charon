## Spring执行流程

```java
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
	public ClassPathXmlApplicationContext(
			String[] configLocations, boolean refresh, @Nullable ApplicationContext parent)
			throws BeansException {

		super(parent);
		setConfigLocations(configLocations);
		if (refresh) {
			refresh();
		}
	}
}
```
### 1.创建AbstractApplicationContext
### 2.加载配置
### 3.刷新容器
```java
public abstract class AbstractApplicationContext extends DefaultResourceLoader
		implements ConfigurableApplicationContext {
@Override
	public void refresh() throws BeansException, IllegalStateException {
		synchronized (this.startupShutdownMonitor) {
			// Prepare this context for refreshing.
			// 准备此上下文以进行刷新。
			prepareRefresh();

			// Tell the subclass to refresh the internal bean factory.
			// 告诉子类刷新内部bean工厂。
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

			// Prepare the bean factory for use in this context.
			// 准备bean工厂以在此上下文中使用。
			prepareBeanFactory(beanFactory);

			try {
				// Allows post-processing of the bean factory in context subclasses.
				// 允许在上下文子类中对bean工厂进行后处理。
				postProcessBeanFactory(beanFactory);

				// Invoke factory processors registered as beans in the context.
				// 在上下文中调用注册为bean的工厂处理器。
				invokeBeanFactoryPostProcessors(beanFactory);

				// Register bean processors that intercept bean creation.
				// 注册拦截bean创建的bean处理器。
				registerBeanPostProcessors(beanFactory);

				// Initialize message source for this context.
				// 初始化此上下文的消息源。
				initMessageSource();

				// Initialize event multicaster for this context.
				// 初始化此上下文的事件多播器。
				initApplicationEventMulticaster();

				// Initialize other special beans in specific context subclasses.
				// 在特定的上下文子类中初始化其他特殊bean。
				onRefresh();

				// Check for listener beans and register them.
				// 检查监听器bean并注册它们。
				registerListeners();

				// Instantiate all remaining (non-lazy-init) singletons.
				// 实例化所有剩余（非延迟初始化）单例。
				finishBeanFactoryInitialization(beanFactory);

				// Last step: publish corresponding event.
				// 最后一步：发布相应的事件。
				finishRefresh();
			}

			catch (BeansException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Exception encountered during context initialization - " +
							"cancelling refresh attempt: " + ex);
				}

				// Destroy already created singletons to avoid dangling resources.
				destroyBeans();

				// Reset 'active' flag.
				cancelRefresh(ex);

				// Propagate exception to caller.
				throw ex;
			}

			finally {
				// Reset common introspection caches in Spring's core, since we
				// might not ever need metadata for singleton beans anymore...
				resetCommonCaches();
			}
		}
	}
}
```

