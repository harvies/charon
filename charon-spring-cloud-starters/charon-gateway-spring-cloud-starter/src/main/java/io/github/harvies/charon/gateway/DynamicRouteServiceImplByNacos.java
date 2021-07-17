package io.github.harvies.charon.gateway;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;

@Slf4j
@Configuration
@EnableConfigurationProperties(NacosGatewayProperties.class)
public class DynamicRouteServiceImplByNacos implements CommandLineRunner {

    @Resource
    private DynamicRouteServiceImpl dynamicRouteService;

    @Resource
    private NacosGatewayProperties nacosGatewayProperties;

    @Resource
    private Executor executor;

    //TODO:2021/7/18 fix动态加载

    /**
     * 监听Nacos Server下发的动态路由配置
     */
    public void dynamicRouteByNacosListener() {
        try {
            ConfigService configService = NacosFactory.createConfigService(nacosGatewayProperties.getAddress());
            String content = configService.getConfig(nacosGatewayProperties.getDataId(), nacosGatewayProperties.getGroupId(), nacosGatewayProperties.getTimeout());
            log.info("load route from nacos content:[{}]", content);
            updateRoute(content);
            configService.addListener(nacosGatewayProperties.getDataId(), nacosGatewayProperties.getGroupId(), new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    updateRoute(configInfo);
                }

                @Override
                public Executor getExecutor() {
                    return executor;
                }
            });
        } catch (NacosException e) {
            log.error("dynamicRouteByNacosListener error", e);
        }
    }

    private void updateRoute(String configInfo) {
        List<RouteDefinition> list = JSON.parseArray(configInfo, RouteDefinition.class);
        list.forEach(definition -> {
            dynamicRouteService.update(definition);
        });
    }

    @Override
    public void run(String... args) {
        dynamicRouteByNacosListener();
    }

}