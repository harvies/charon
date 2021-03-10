package io.github.harvies.charon.elasticsearch;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private Long id;
    private String username;
    private String password;
}
