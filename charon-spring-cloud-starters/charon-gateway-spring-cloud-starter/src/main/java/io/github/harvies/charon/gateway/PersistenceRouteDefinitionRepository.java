package io.github.harvies.charon.gateway;

import java.util.List;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Slf4j
public class PersistenceRouteDefinitionRepository implements RouteDefinitionRepository {

    @Resource
    private ConfigService configService;

    @Value("${spring.profiles.active}")
    private String activeProfiles;

    @Value("${spring.application.name}")
    private String applicationName;
    @Resource
    private NacosConfigProperties nacosConfigProperties;

    @SneakyThrows
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        String dataId = applicationName + "-route-rule-" + activeProfiles;
        String config = configService.getConfig(dataId, nacosConfigProperties.getGroup(), 5000);
        log.info("从nacos拉取最新的网关路由规则 dataId:[{}] group:[{}] config:[{}]", dataId, nacosConfigProperties.getGroup(), config);
        List<RouteDefinition> routeDefinitions = JSON.parseArray(config, RouteDefinition.class);
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
