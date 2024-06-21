package io.github.harvies.charon.jpa;

import com.google.common.collect.Lists;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import io.github.harvies.charon.util.StringUtils;
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

        BooleanExpression expression = Expressions.TRUE;
        if (StringUtils.isNotBlank(customer.getName())) {
            expression = expression.and(QCustomer.customer.name.eq(customer.getName()));
        }
        if (StringUtils.isNotBlank(customer.getEmail())) {
            expression = expression.and(QCustomer.customer.email.eq(customer.getEmail()));
        }
        List<Customer> customers = Lists.newArrayList(customerRepository.findAll(expression));
        assertEquals(1, customers.size());
        assertEquals("Alice", customers.get(0).getName());
    }
}
