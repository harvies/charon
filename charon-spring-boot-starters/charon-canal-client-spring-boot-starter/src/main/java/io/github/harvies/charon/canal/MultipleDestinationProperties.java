package io.github.harvies.charon.canal;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * canal多destination配置
 */
@Getter
@Setter
public class MultipleDestinationProperties {

    private String host;

    private int port;
    /**
     * destinationsMap
     */
    private Map<String, Destination> destinations = new LinkedHashMap<>();

    @Getter
    @Setter
    public static class Destination {
        private String filter;
    }
}
