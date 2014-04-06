package com.xzy.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class HomeAction extends ActionSupport {

	public String index() throws Exception {

		return "index";
	}

	public String top() throws Exception {

		return "top";
	}

	public String bottom() throws Exception {

		return "bottom";
	}

	public String left() throws Exception {

		return "left";
	}

	public String right() throws Exception {

		return "right";
	}

}
