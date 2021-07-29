package io.github.harvies.charon.tests.base.hystrix.collapse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private Long id;
    private String name;
}
