package com.xzy.oa.dao;

import java.util.List;

import com.xzy.oa.domain.Department;


public interface DeptDao {
	public void save(Department t);

	public void delete(int id);

	public void update(Department t);

	public Department getById(int id);

	public List<Department> findAll();

	public List<Department> getByIds(int[] ids);
}
