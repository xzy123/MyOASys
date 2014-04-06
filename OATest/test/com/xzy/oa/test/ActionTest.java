package com.xzy.oa.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component("actionTest")
public class ActionTest extends ActionSupport{
	
	@Resource
	public ServiceTest st;

	@Override
	public String execute() throws Exception {
		st.saveUsers();
		System.out.println("action test");
		return "success";
	}

	
}
