package io.github.harvies.eris.spring.mvc.service;

import io.github.harvies.eris.spring.mvc.dao.EmployeeDAO;
import io.github.harvies.eris.spring.mvc.model.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeManagerImpl implements EmployeeManager {

	@Autowired
	EmployeeDAO dao;

	@Override
	public List<EmployeeVO> getAllEmployees()
	{
		return dao.getAllEmployees();
	}
}
