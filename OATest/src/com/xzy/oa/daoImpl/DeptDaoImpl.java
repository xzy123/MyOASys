package com.xzy.oa.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.xzy.oa.dao.DeptDao;
import com.xzy.oa.domain.Department;

@Component("deptDao")
public class DeptDaoImpl implements DeptDao{

	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void save(Department t) {
		getSession().save(t);
	}

	public void delete(int id) {
		Department t = getById(id);
		getSession().delete(t);
	}

	public void update(Department t) {
		this.getSession().update(t);
	}

	public Department getById(int id) {
		return (Department)this.getSession().get(Department.class, id);
	}

	public List<Department> findAll() {
		return getSession().createQuery("FROM Department").list();
	}

	public List<Department> getByIds(int[] ids) {
		Integer[] is = new Integer[ids.length];
		for(int i=0;i<ids.length;i++)
			is[i] = ids[i];
		return getSession().createQuery(//
					"FROM Department WHERE id= IN (:is)")//
					.setParameterList("is", is)//
					.list();
	}

}
