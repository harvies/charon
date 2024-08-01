package io.github.harvies.charon.tests.web.dao;

import io.github.harvies.charon.tests.web.model.EmployeeVO;

import java.util.List;

public interface EmployeeDAO
{
	public List<EmployeeVO> getAllEmployees();
}
