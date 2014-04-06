package com.xzy.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xzy.oa.dao.RoleDao;
import com.xzy.oa.domain.Role;

@Component
@Transactional
@Scope("prototype")
public class RoleServiceImpl implements RoleService{
	

	@Resource
	private RoleDao roleDao;
	
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	public void delete(int id) {
		roleDao.delete(id);
	}

	public void save(Role r) {
		roleDao.save(r);
	}

	public Role getById(int id) {
		return roleDao.getById(id);
	}

	public void update(Role r) {
		roleDao.update(r);
	}
	
	public List<Role> getByIds(int[] roleIds){
		if(roleIds == null || roleIds.length == 0)
			return new ArrayList<Role>();
		return roleDao.getByIds(roleIds);
	}
	
	

}
