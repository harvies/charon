package io.github.harvies.eris.spring.mvc.service;

import io.github.harvies.eris.spring.mvc.model.EmployeeVO;

import java.util.List;

public interface EmployeeManager
{
	public List<EmployeeVO> getAllEmployees();
}
