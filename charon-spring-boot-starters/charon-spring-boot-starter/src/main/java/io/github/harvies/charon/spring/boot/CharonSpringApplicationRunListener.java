package io.github.harvies.charon.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

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
        System.err.println("starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}
