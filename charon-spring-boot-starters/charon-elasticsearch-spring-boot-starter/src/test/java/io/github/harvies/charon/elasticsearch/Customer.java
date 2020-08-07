package io.github.harvies.charon.elasticsearch;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author harvies
 */
@Data
@NoArgsConstructor
@Document(indexName = "customer", replicas = 0, refreshInterval = "-1")
public class Customer {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
