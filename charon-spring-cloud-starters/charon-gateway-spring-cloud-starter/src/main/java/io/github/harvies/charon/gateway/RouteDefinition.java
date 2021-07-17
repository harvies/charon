package io.github.harvies.charon.gateway;

import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.util.StringUtils.tokenizeToStringArray;

/**
 * 提供路由实体类，主要有predicates匹配来自用户的请求，filters服务路由
 */
@Validated
public class RouteDefinition extends org.springframework.cloud.gateway.route.RouteDefinition {
    @NotEmpty
    private String id = UUID.randomUUID().toString();

    @NotEmpty
    @Valid
    private List<PredicateDefinition> predicates = new ArrayList<>();

    @Valid
    private List<FilterDefinition> filters = new ArrayList<>();

    @NotNull
    private URI uri;

    private int order = 0;

    public RouteDefinition() {
    }

    public RouteDefinition(String text) {
        int eqIdx = text.indexOf("=");
        if (eqIdx <= 0) {
            throw new ValidationException("Unable to parse RouteDefinition text '" + text + "'" +
                    ", must be of the form name=value");
        }

        setId(text.substring(0, eqIdx));

        String[] args = tokenizeToStringArray(text.substring(eqIdx + 1), ",");

        setUri(URI.create(args[0]));

        for (int i = 1; i < args.length; i++) {
            this.predicates.add(new PredicateDefinition(args[i]));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PredicateDefinition> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<PredicateDefinition> predicates) {
        this.predicates = predicates;
    }

    public List<FilterDefinition> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterDefinition> filters) {
        this.filters = filters;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteDefinition routeDefinition = (RouteDefinition) o;
        return Objects.equals(id, routeDefinition.id) &&
                Objects.equals(predicates, routeDefinition.predicates) &&
                Objects.equals(order, routeDefinition.order) &&
                Objects.equals(uri, routeDefinition.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, predicates, uri);
    }

    @Override
    public String toString() {
        return "RouteDefinition{" +
                "id='" + id + '\'' +
                ", predicates=" + predicates +
                ", filters=" + filters +
                ", uri=" + uri +
                ", order=" + order +
                '}';
    }
}