package com.xzy.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xzy.oa.dao.DeptDao;
import com.xzy.oa.domain.Department;

@Component("deptservice")
@Transactional
@Scope("prototype")
public class DeptServiceImpl implements DeptService{

	@Resource
	private DeptDao deptDao;
	
	@Resource
	private SessionFactory sessionFactory;
	
	public List<Department> findAll() {
		return deptDao.findAll();
	}

	public void delete(int id) {
		deptDao.delete(id);
	}

	public void save(Department r) {
		deptDao.save(r);
	}

	public Department getById(int id) {
		if(id == 0)
			return null;
		return deptDao.getById(id);
	}

	public void update(Department r) {
		deptDao.update(r);
	}

	
	public List<Department> findTopList(){
		
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Department d WHERE d.parent IS NULL")//
				.list();
	}
	
	public List<Department> findChildren(int parentId){
		
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Department d WHERE d.parent.id=?")//
				.setParameter(0, parentId)//
				.list();
	}
}
