package io.github.harvies.eris.base.hystrix.collapse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private Long id;
    private String name;
}
