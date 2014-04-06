package com.xzy.oa.view.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xzy.oa.domain.Department;
import com.xzy.oa.domain.Role;
import com.xzy.oa.domain.User;
import com.xzy.oa.service.impl.DeptService;
import com.xzy.oa.service.impl.RoleService;
import com.xzy.oa.service.impl.UserService;
import com.xzy.oa.util.DepartmentUtil;

@Component("userAction")
@Scope("prototype")
public class UserAction  extends ActionSupport {

	private int id;
	private String name; 
	private String description;
	private Department department;
	private Set<Role> roles;
	private String loginName;
	private String password;
	private String gender;
	private String phoneNumber;
	private String email;
	
	private int departmentId;
	private int[] roleIds;
	
	public int[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(int[] roleIds) {
		this.roleIds = roleIds;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DeptService getDeptservice() {
		return deptservice;
	}

	public void setDeptservice(DeptService deptservice) {
		this.deptservice = deptservice;
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

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	@Resource
	private UserService userservice;
	
	@Resource
	private DeptService deptservice;
	
	@Resource
	private RoleService roleservice;
	
	public String list() throws Exception {
		List<User> userlist =userservice.findAll();
		ActionContext.getContext().put("userlist", userlist);
		return "list";
	}
	
	public String delete() throws Exception {
		userservice.delete(id);
		return "toList";
	}
	public String addUI() throws Exception {
		List<Department> topList = deptservice.findTopList();
		List<Department> departmentlist = DepartmentUtil.getAllDepartments(topList);
		ActionContext.getContext().put("deptlist", departmentlist);
		
		List<Role> rolelist =roleservice.findAll();
		ActionContext.getContext().put("rolelist", rolelist);
		return "saveUI";
	}
	public String add() throws Exception {
		User u = new User();
		u.setId(id);
		u.setName(name);
		u.setDescription(description);
		
		u.setDepartment(deptservice.getById(departmentId));
		List<Role> rolelist = roleservice.getByIds(roleIds);
		if(rolelist.size() == 0)
			System.out.println("kong kong kong kong ");
		else
			System.out.println("no kong no kong no kong no kong ");
		u.setRoles(new HashSet<Role>(rolelist));
		u.setPassword("1234");
		
		userservice.save(u);
		return "toList";
	}
	public String editUI() throws Exception {
		List<Department> topList = deptservice.findTopList();
		List<Department> departmentlist = DepartmentUtil.getAllDepartments(topList);
		ActionContext.getContext().put("deptlist", departmentlist);
		
		List<Role> rolelist =roleservice.findAll();
		ActionContext.getContext().put("rolelist", rolelist);
		
		User user = userservice.getById(id);
		ActionContext.getContext().getValueStack().push(user);
		
		if(user.getDepartment() != null)
			departmentId = user.getDepartment().getId();
		
		if(user.getRoles() != null){
			roleIds = new int[user.getRoles().size()];
			int index = 0;
			for(Role role : user.getRoles()){
				roleIds[index++] = role.getId();
				
			}
		}
		
		return "saveUI";
	}
	public String edit() throws Exception {
		User u = userservice.getById(this.getId());
		
		u.setLoginName(loginName);
		u.setName(name);
		u.setGender(gender);
		u.setPhoneNumber(phoneNumber);
		u.setEmail(this.email);
		u.setDescription(description);
		
		u.setDepartment(deptservice.getById(departmentId));
		
		List<Role> rolelist = roleservice.getByIds(roleIds);
		u.setRoles(new HashSet<Role>(rolelist));
		
		userservice.update(u);
		return "toList";
	}
	public String initPassword() throws Exception {
		User user = userservice.getById(id);
		
		user.setPassword("1234");
		
		userservice.update(user);
		return "toList";
	}
	
	/**
	 * login page
	 */
	public String loginUI() throws Exception{
		
		return "loginUI";
	}
	
	/**
	 * login
	 */
	public String login() throws Exception{
		User user = this.userservice.findByLoginNameAndPassword(this.loginName,this.password);
		if(user == null){
			this.addFieldError("login", "用户名或密码不正确");
			return "loginUI";
		}else{
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}
		
	}
	
	/**
	 * logout
	 */
	public String logout() throws Exception{
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
}
