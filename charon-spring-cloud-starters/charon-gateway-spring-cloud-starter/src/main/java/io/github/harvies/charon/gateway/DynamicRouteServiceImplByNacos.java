package io.github.harvies.charon.gateway;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@Configuration
@EnableConfigurationProperties(NacosGatewayProperties.class)
public class DynamicRouteServiceImplByNacos {

    @Resource
    private DynamicRouteServiceImpl dynamicRouteService;

    @Resource
    private NacosGatewayProperties nacosGatewayProperties;

    private static final Executor executor = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("pool-nacos-%d").build());

    @PostConstruct
    public void init() {
        dynamicRouteByNacosListener();
    }

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
                            log.info("receiveConfigInfo configInfo:[{}]", configInfo);
                            updateRoute(configInfo);
                        }

                        @Override
                        public Executor getExecutor() {
                            log.info("getExecutor"); //com.alibaba.nacos.client.config.impl.CacheData.safeNotifyListener 238行，会导致该行日志会打印2次
                            // nacos配置变更会回调该方法使用该线程接收配置信息
                            return executor;
                        }
                    }
            );
        } catch (NacosException e) {
            log.error("dynamicRouteByNacosListener error", e);
        }
    }

    private void updateRoute(String configInfo) {
        List<RouteDefinition> list = JSON.parseArray(configInfo, RouteDefinition.class);
        list.forEach(definition -> dynamicRouteService.update(definition));
    }

}