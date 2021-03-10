package io.github.harvies.charon.elasticsearch;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class User {
    private Long id;
    private String username;
    private String password;
    private List<String> tagList;
}
