package io.github.harvies.charon.util.serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class User implements Serializable {
    private String userName;
    private String password;
    private String sex;
}