package io.github.harvies.charon.elasticsearch;

import io.github.harvies.charon.elasticsearch.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import javax.annotation.Resource;

@Slf4j
public class CharonElasticSearchSpringBootTest extends BaseTest {

    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    @Resource
    private CustomerRepository repository;

    @Test
    public void elasticsearchOperations() {
        Assertions.assertNotNull(elasticsearchOperations);
    }

    @Test
    public void test() {
        repository.deleteAll();
        saveCustomers();
        fetchAllCustomers();
        fetchIndividualCustomers();
    }

    private void saveCustomers() {
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));
    }

    private void fetchAllCustomers() {
        Iterable<Customer> all = repository.findAll();
        all.forEach(customer -> log.warn("all customer:{}", customer));
    }

    private void fetchIndividualCustomers() {
        log.warn("firstName:{}", repository.findByFirstName("Alice"));
        log.warn("lastName:{}", repository.findByLastName("Smith"));
    }
}
