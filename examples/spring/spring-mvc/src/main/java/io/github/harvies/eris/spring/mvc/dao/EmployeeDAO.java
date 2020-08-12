package io.github.harvies.eris.spring.mvc.dao;

import io.github.harvies.eris.spring.mvc.model.EmployeeVO;

import java.util.List;

public interface EmployeeDAO
{
	public List<EmployeeVO> getAllEmployees();
}
