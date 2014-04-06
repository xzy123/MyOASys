package com.xzy.oa.view.action;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xzy.oa.domain.Privilege;
import com.xzy.oa.domain.Role;
import com.xzy.oa.service.impl.PrivilegeService;
import com.xzy.oa.service.impl.RoleService;

@Component("roleAction")
@Scope("prototype")
public class RoleAction extends ActionSupport {
	
	private int[] privilegeIds;

	public int[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(int[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	private int id;
	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RoleService getRoleservice() {
		return roleservice;
	}

	public void setRoleservice(RoleService roleservice) {
		this.roleservice = roleservice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Resource
	private RoleService roleservice;

	public String list() throws Exception {
		List<Role> rolelist = roleservice.findAll();
		ActionContext.getContext().put("rolelist", rolelist);
		return "list";
	}

	public String delete() throws Exception {
		roleservice.delete(id);
		return "toList";
	}

	public String add() throws Exception {

		Role role = new Role();
		role.setName(getName());
		role.setDescription(getDescription());

		roleservice.save(role);
		return "toList";

	}

	public String addUI() throws Exception {

		return "saveUI";
	}

	/*
	 * public String saveUI() throws Exception{ return "saveUI"; }
	 */

	public String edit() throws Exception {

		Role role = roleservice.getById(id);
		role.setName(getName());
		role.setDescription(description);

		roleservice.update(role);
		return "toList";
	}

	public String editUI() throws Exception {

		Role role = roleservice.getById(id);
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}
	
	@Resource
	private PrivilegeService privilegeservice;

	//设置权限页面  
	public String setPrivilegeUI() throws Exception {

		Role role = roleservice.getById(id);
		ActionContext.getContext().getValueStack().push(role);
		
		if(role.getPrivileges() != null){
			privilegeIds = new int[role.getPrivileges().size()];
			int index = 0;
			for(Privilege priv : role.getPrivileges()){
				privilegeIds[index++] = priv.getId();
			}
		}
		
		List<Privilege> privilegelist = this.privilegeservice.findAll();
		ActionContext.getContext().put("privilegelist", privilegelist);
		
		return "setPrivilegeUI";
	}

	//设置权限
	public String setPrivilege() throws Exception {

		Role role = roleservice.getById(id);
		
		List<Privilege> privilegelist = privilegeservice.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegelist));

		roleservice.update(role);
		
		return "toList";
	}
}
