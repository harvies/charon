package io.github.harvies.charon.elasticsearch.repository;

import io.github.harvies.charon.elasticsearch.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author harvies
 */
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

    /**
     * 根据firstName查询
     *
     * @param firstName
     * @return
     */
    Customer findByFirstName(String firstName);

    /**
     * 根据lastName查询
     *
     * @param lastName
     * @return
     */
    List<Customer> findByLastName(String lastName);

}
