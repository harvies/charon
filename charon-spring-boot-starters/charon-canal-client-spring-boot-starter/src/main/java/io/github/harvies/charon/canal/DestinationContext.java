package io.github.harvies.charon.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.map.HashedMap;

import java.util.Map;

@Getter
@Setter
public class DestinationContext {

    public static Map<String, DestinationContext> destinationContextMap = new HashedMap<>();

    private String destination;
    private String filter;
    private CanalConnector canalConnector;
}
