package io.github.harvies.eris.spring.boot.data.jpa.repository;

import io.github.harvies.eris.spring.boot.data.jpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
