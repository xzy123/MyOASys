package com.xzy.oa.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.xzy.oa.dao.PrivilegeDao;
import com.xzy.oa.domain.Privilege;

@Component("privilegeDao")
public class PrivilegeDaoImpl implements PrivilegeDao {
	
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public void save(Privilege t) {
		this.getSession().save(t);
	}

	public void delete(int id) {
		Object obj = getById(id);
			if(obj != null)
		this.getSession().delete(obj);
	}

	public void update(Privilege t) {
		this.getSession().update(t);
	}

	public Privilege getById(int id) {
		return (Privilege)this.getSession().get(Privilege.class, id);
	}

	public List<Privilege> findAll() {
		return this.getSession().createQuery("FROM Privilege").list();
	}

	public List<Privilege> getByIds(int[] ids) {
		Integer[] is = new Integer[ids.length];
		for(int i=0;i<ids.length;i++)
			is[i] = ids[i];
		return getSession().createQuery(//
					"FROM Privilege WHERE id  IN (:is)")//
					.setParameterList("is", is)//
					.list();
	}

}
