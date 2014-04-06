package com.xzy.oa.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.xzy.oa.dao.UserDao;
import com.xzy.oa.domain.Department;
import com.xzy.oa.domain.User;

@Component("userDao")
public class UserDaoImpl implements UserDao {

	@Resource
	private SessionFactory sessionFactory;
	
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(User t) {
		this.getSession().save(t);
	}

	public void delete(int id) {
		User t = getById(id);
		this.getSession().delete(t);
	}

	public void update(User t) {
		this.getSession().update(t);
	}

	public User getById(int id) {
		return (User)this.getSession().get(User.class, id);
	}

	public List<User> findAll() {
		return getSession().createQuery("FROM User").list();
	}

	public List<User> getByIds(int[] ids) {
		Integer[] is = new Integer[ids.length];
		for(int i=0;i<ids.length;i++)
			is[i] = ids[i];
		return getSession().createQuery(//
					"FROM Department WHERE id= IN (:is)")//
					.setParameterList("is", is)//
					.list();
	}

	

	
}
