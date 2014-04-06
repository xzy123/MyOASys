package com.xzy.oa.util;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xzy.oa.domain.Privilege;
import com.xzy.oa.service.impl.PrivilegeService;

public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {

		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(arg0.getServletContext());

		PrivilegeService privilegeservice = (PrivilegeService) ac
				.getBean("privilegeService");

		List<Privilege> topPrivilegelist = privilegeservice.findTopList();
		arg0.getServletContext().setAttribute("topPrivilegelist",
				topPrivilegelist);
		System.out.println("====已准备数据=====");
	}

}
