package io.github.harvies.charon.jpa;

import com.google.common.collect.Lists;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerRepositoryTest extends BaseTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSaveAndGetCustomer() {
        Customer customer = new Customer();
        customer.setName("Alice");
        customer.setEmail("alice@example.com");

        customerRepository.save(customer);

        List<Customer> customers = customerRepository.findAll();
        assertEquals(1, customers.size());
        assertEquals("Alice", customers.get(0).getName());
    }

    @Test
    public void testFindAll() {
        Customer customer = new Customer();
        customer.setName("Alice");
        customer.setEmail("alice@example.com");

        customerRepository.save(customer);
        BooleanExpression expression = Expressions.asBoolean(true);

        List<Customer> customers = Lists.newArrayList(customerRepository.findAll(expression));
        assertEquals(1, customers.size());
        assertEquals("Alice", customers.get(0).getName());
    }
}
