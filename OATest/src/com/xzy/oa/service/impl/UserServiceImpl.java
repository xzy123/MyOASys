package com.xzy.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xzy.oa.dao.UserDao;
import com.xzy.oa.domain.User;

@Component
@Transactional
@Scope("prototype")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public List<User> findAll() {

		return userDao.findAll();
	}

	public void delete(int id) {
		userDao.delete(id);
	}

	public void save(User r) {
		userDao.save(r);
	}

	public User getById(int id) {
		if (id == 0)
			return null;
		return userDao.getById(id);
	}

	public void update(User r) {
		userDao.update(r);
	}

	public List<User> getByIds(int[] userIds) {
		return this.getSession().createQuery(//
				"FROM User WHERE id IN (:ids)")
				.setParameter("ids", userIds).list();
	}

	public User findByLoginNameAndPassword(String loginName, String password) {
		return (User)this.getSession().createQuery(//
				"FROM User u WHERE u.loginName=? AND u.password=?")//
				.setParameter(0, loginName)
				.setParameter(1, password)
				.uniqueResult();
	}

}
