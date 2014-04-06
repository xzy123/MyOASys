package com.xzy.oa.dao;

import java.util.List;

import com.xzy.oa.domain.Privilege;


public interface PrivilegeDao {
	public void save(Privilege t);

	public void delete(int id);

	public void update(Privilege t);

	public Privilege getById(int id);

	public List<Privilege> findAll();

	public List<Privilege> getByIds(int[] ids);
}
