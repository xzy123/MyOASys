package com.xzy.oa.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.xzy.oa.dao.RoleDao;
import com.xzy.oa.domain.Role;

@Component("roledao")
public class RoleDaoImpl implements RoleDao {

	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public List findAll() {
		return getSession().createQuery("FROM Role").list();
	}
	
	public void delete(int id) {
		Object obj = getById(id);
		if(obj != null)
			getSession().delete(obj);
	}

	public void update(Role t) {
		getSession().update(t);
	}

	public Role getById(int id) {
		return (Role)getSession().get(Role.class, id);
	}


	public void save(Role t) {
		Session session = getSession();
		session.save(t);
	}

	public List<Role> getByIds(int[] ids) {
		Integer[] is = new Integer[ids.length];
		for(int i=0;i<ids.length;i++)
			is[i] = ids[i];
		return getSession().createQuery(//
					"FROM Role WHERE id  IN (:is)")//
					.setParameterList("is", is)//
					.list();
	}

//	@Override
//	public void save(Object t) {
//		// TODO Auto-generated method stub
//		
//	}

}
