package com.xzy.oa.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	private ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	@Test
	public void testBean() throws Exception{
		ActionTest at = (ActionTest)ac.getBean("actionTest");
		System.out.println(at);
	}
	
	@Test
	public void testSessionFactory() throws Exception{
		SessionFactory sf = (SessionFactory)ac.getBean("sessionFactory");
		ac.getBean("userDao");
	}
	
	@Test
	public void testTransaction() throws Exception{
		ServiceTest st = (ServiceTest)ac.getBean("serviceTest");
//		st.saveUsers();
	}
	
}
