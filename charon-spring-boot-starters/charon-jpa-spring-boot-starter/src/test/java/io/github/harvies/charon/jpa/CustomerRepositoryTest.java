package io.github.harvies.charon.jpa;

import com.google.common.collect.Lists;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.github.harvies.charon.util.StringUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerRepositoryTest extends BaseTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Resource
    private JPAQueryFactory jpaQueryFactory;

    @AfterEach
    public void afterEach(){
        customerRepository.deleteAll();
    }

    @Test
    public void testSaveAndGetCustomer() {
        Customer customer = new Customer();
        customer.setName("Alice");
        customer.setEmail("alice@example.com");

        customerRepository.save(customer);

        List<Customer> customers = customerRepository.findAll();
        assertEquals(1, customers.size());
        assertEquals("Alice", customers.getFirst().getName());
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
        assertEquals("Alice", customers.getFirst().getName());
    }

    @Test
    public void test() {
        Customer customer = new Customer();
        customer.setName("Alice");
        customer.setEmail("alice@example.com");
        customerRepository.save(customer);
        Customer fetchedFirst = jpaQueryFactory.selectFrom(QCustomer.customer).where(QCustomer.customer.name.eq("Alice")).fetchFirst();
        assertNotNull(fetchedFirst);
    }
}
