package com.xzy.oa.service.impl;

import java.util.List;

import com.xzy.oa.domain.Department;


public interface DeptService {
	public List<Department> findAll();

	public void delete(int id);

	public void save(Department r);

	public Department getById(int id);

	public void update(Department r);
	
	List<Department> findTopList();
	
	List<Department> findChildren(int parentId);
}
