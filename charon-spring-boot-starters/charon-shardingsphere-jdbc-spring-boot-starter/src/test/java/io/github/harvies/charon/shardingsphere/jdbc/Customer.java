package io.github.harvies.charon.shardingsphere.jdbc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author harvies
 */
@Data
@AllArgsConstructor
@Builder
public class Customer {
    private long id;
    private String firstName, lastName;
}
