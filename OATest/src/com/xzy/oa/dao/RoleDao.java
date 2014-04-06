package com.xzy.oa.dao;

import java.util.List;

import com.xzy.oa.domain.Role;

public interface RoleDao {
	public void save(Role t);

	public void delete(int id);

	public void update(Role t);

	public Role getById(int id);

	public List<Role> findAll();

	public List<Role> getByIds(int[] ids);
}
