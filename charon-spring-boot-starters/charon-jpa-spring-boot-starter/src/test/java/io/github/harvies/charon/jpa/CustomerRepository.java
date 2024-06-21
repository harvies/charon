package io.github.harvies.charon.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // 可以定义自定义查询方法
}
