package com.xzy.oa.service.impl;

import java.util.List;

import com.xzy.oa.domain.Privilege;


public interface PrivilegeService {
	
	public List<Privilege> findAll();

	public void delete(int id);

	public void save(Privilege r);
	
	public Privilege getById(int id);
	
	public void update(Privilege r);
	
	public List<Privilege> getByIds(int[] roles);

	public List<Privilege> findTopList();
}
