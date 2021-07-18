package io.github.harvies.charon.gateway;

import java.util.Collections;
import java.util.List;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    @Resource
    private NacosConfigProperties nacosConfigProperties;

    @SneakyThrows
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        String dataId = "routing-rule";
        String config = configService.getConfig(dataId, nacosConfigProperties.getGroup(), 5000);
        if (StringUtils.isBlank(config)) {
            log.info("从nacos没有拉取到配置 dataId:[{}] group:[{}]", dataId, nacosConfigProperties.getGroup());
            return Flux.fromIterable(Collections.emptyList());
        }
        if (log.isDebugEnabled()) {
            log.debug("从nacos拉取最新的网关路由规则 dataId:[{}] group:[{}] config:[{}]", dataId, nacosConfigProperties.getGroup(), config);
        }
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
