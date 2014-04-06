package com.xzy.oa.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xzy.oa.domain.User;

@Component("serviceTest")
public class ServiceTest {

	@Resource
	private  SessionFactory sessionFactory;

	@Transactional
	public void saveUsers() {
		Session session = sessionFactory.getCurrentSession();
		session.save(new User());
	}
}
