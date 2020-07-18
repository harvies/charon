package io.github.harvies.charon.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class CharonSpringApplicationRunListener implements SpringApplicationRunListener {
    private final SpringApplication application;

    private final String[] args;

    /**
     * SpringBoot启动监听器
     * <p>
     * Constructor<?> constructor = instanceClass.getDeclaredConstructor(parameterTypes);
     *
     * @param application
     * @param args
     * @see SpringApplication#getRunListeners(java.lang.String[])
     */
    public CharonSpringApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {
        log.info("starting application:[{}]", application);
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        log.info("environmentPrepared application:[{}] environment:[{}]", application, environment);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("contextPrepared application:[{}] context:[{}]", application, context);
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("contextLoaded application:[{}] context:[{}]", application, context);
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        log.info("started application:[{}] context:[{}]", application, context);
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        log.info("running application:[{}] context:[{}]", application, context);
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("failed application:[{}] context:[{}]", application, context);
    }
}
