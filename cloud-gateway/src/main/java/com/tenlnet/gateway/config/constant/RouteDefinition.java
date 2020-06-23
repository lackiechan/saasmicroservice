package com.tenlnet.gateway.config.constant;

import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.util.StringUtils;
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

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-08-11 09:04
 */
@Validated
public class RouteDefinition {
    @NotEmpty
    private String id = UUID.randomUUID().toString();
    @NotEmpty
    private String name = "";
    @NotEmpty
    @Valid
    private List<PredicateDefinition> predicates = new ArrayList();
    @Valid
    private List<FilterDefinition> filters = new ArrayList();
    @NotNull
    private URI uri;
    private int order = 0;

    public RouteDefinition() {
    }

    public RouteDefinition(String text) {
        int eqIdx = text.indexOf(61);
        if (eqIdx <= 0) {
            throw new ValidationException("Unable to parse RouteDefinition text '" + text + "', must be of the form name=value");
        } else {
            this.setId(text.substring(0, eqIdx));
            String[] args = StringUtils.tokenizeToStringArray(text.substring(eqIdx + 1), ",");
            this.setUri(URI.create(args[0]));

            for (int i = 1; i < args.length; ++i) {
                this.predicates.add(new PredicateDefinition(args[i]));
            }

        }
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PredicateDefinition> getPredicates() {
        return this.predicates;
    }

    public void setPredicates(List<PredicateDefinition> predicates) {
        this.predicates = predicates;
    }

    public List<FilterDefinition> getFilters() {
        return this.filters;
    }

    public void setFilters(List<FilterDefinition> filters) {
        this.filters = filters;
    }

    public URI getUri() {
        return this.uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            RouteDefinition routeDefinition = (RouteDefinition) o;
            return Objects.equals(this.id, routeDefinition.id) && Objects.equals(this.predicates, routeDefinition.predicates) && Objects.equals(this.order, routeDefinition.order) && Objects.equals(this.uri, routeDefinition.uri);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.predicates, this.uri});
    }

    public String toString() {
        return "RouteDefinition{id='" + this.id + '\'' + ", predicates=" + this.predicates + ", filters=" + this.filters + ", uri=" + this.uri + ", order=" + this.order + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
