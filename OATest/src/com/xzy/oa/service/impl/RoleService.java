package com.xzy.oa.service.impl;

import java.util.List;

import com.xzy.oa.domain.Role;

public interface RoleService {

	public List<Role> findAll();

	public void delete(int id);

	public void save(Role r);
	
	public Role getById(int id);
	
	public void update(Role r);
	
	public List<Role> getByIds(int[] roles);
}
