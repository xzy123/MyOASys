package com.xzy.oa.util;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xzy.oa.domain.Privilege;
import com.xzy.oa.domain.User;

@Component("installer")
public class Installer {

	@Resource
	private SessionFactory sessionFactory;
	
	@Transactional
	public void install(){
		Session session = sessionFactory.getCurrentSession();
		
		//保存超级管理员用户
		
		User user = new User();
		user.setLoginName("admin");
		user.setName("超级管理员");
		user.setPassword("admin");
		session.save(user);
		
		//保存权限数据
		Privilege menu,menu1,menu2,menu3,menu4,menu5;
		menu = new Privilege("系统管理",null,null);
		menu1 = new Privilege("岗位管理","/role_list",menu);
		menu2 = new Privilege("部门管理","/dept_list",menu);
		menu3 = new Privilege("用户管理","/user_list",menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		
		session.save(new Privilege("岗位列表","/role_list",menu1));
		session.save(new Privilege("岗位删除","/role_delete",menu1));
		session.save(new Privilege("岗位添加","/role_add",menu1));
		session.save(new Privilege("岗位修改","/role_edit",menu1));
		
		session.save(new Privilege("部门列表","/dept_list",menu2));
		session.save(new Privilege("部门删除","/dept_delete",menu2));
		session.save(new Privilege("部门添加","/dept_add",menu2));
		session.save(new Privilege("部门修改","/dept_edit",menu2));
		
		session.save(new Privilege("用户列表","/user_list",menu3));
		session.save(new Privilege("用户删除","/user_delete",menu3));
		session.save(new Privilege("用户添加","/user_add",menu3));
		session.save(new Privilege("用户修改","/user_edit",menu3));
		
		
	}
	
	public static void main(String[] args){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();
	}
}
