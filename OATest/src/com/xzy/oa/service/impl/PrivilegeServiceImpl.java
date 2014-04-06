package com.xzy.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xzy.oa.dao.PrivilegeDao;
import com.xzy.oa.domain.Privilege;

@Component("privilegeService")
@Transactional
@Scope("prototype")
public class PrivilegeServiceImpl implements PrivilegeService{
	

	@Resource
	PrivilegeDao privilegeDao;
	
	@Resource
	private SessionFactory sessionFactory;
	
	Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public List<Privilege> findAll() {
		
		return privilegeDao.findAll();
	}

	public void delete(int id) {
		this.privilegeDao.delete(id);
	}

	public void save(Privilege r) {
		this.privilegeDao.save(r);
	}

	public Privilege getById(int id) {
		return this.privilegeDao.getById(id);
	}

	public void update(Privilege r) {
		this.privilegeDao.update(r);
	}

	public List<Privilege> getByIds(int[] roles) {
		return this.privilegeDao.getByIds(roles);
	}

	public List<Privilege> findTopList() {
		return this.getSession().createQuery(//
				"FROM Privilege p WHERE p.parent IS NULL").list();
	}

}
