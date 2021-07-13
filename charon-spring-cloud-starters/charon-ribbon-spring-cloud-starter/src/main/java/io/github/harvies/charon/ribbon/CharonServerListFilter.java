package io.github.harvies.charon.ribbon;

import com.netflix.loadbalancer.Server;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter;

import java.util.List;
import java.util.stream.Collectors;

public class CharonServerListFilter extends ZonePreferenceServerListFilter {
    @Override
    public List<Server> getFilteredListOfServers(List<Server> servers) {
        servers = servers.stream().filter(server -> !StringUtils.equals(server.getHost(), "192.168.7.xx")).collect(Collectors.toList());
        return super.getFilteredListOfServers(servers);
    }
}
