package io.github.harvies.charon.tests.base.guava.collect;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private Long id;
    private String username;
}
