package com.xzy.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xzy.oa.domain.Department;
import com.xzy.oa.domain.Role;
import com.xzy.oa.service.impl.DeptService;
import com.xzy.oa.util.DepartmentUtil;

@Component("departmentAction")
@Scope("prototype")
public class DepartmentAction extends ActionSupport {

	private int id;
	private String name;
	private String description;

	private int parentId;

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Resource
	private DeptService deptservice;

	public String list() throws Exception {
		List<Department> deptlist = null;
		
		if(parentId == 0){
			deptlist = deptservice.findTopList();
		}else{
			deptlist = deptservice.findChildren(parentId);
			Department parent = deptservice.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("deptlist", deptlist);
		return "list";
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

	public String add() throws Exception {

		Department dept = new Department();
		dept.setName(getName());
		dept.setDescription(getDescription());
		dept.setParent(deptservice.getById(parentId));
		deptservice.save(dept);
		return "toList";
	}

	public String addUI() throws Exception {

		List<Department> topList = deptservice.findTopList();
		List<Department> departmentlist = DepartmentUtil.getAllDepartments(topList);
		ActionContext.getContext().put("deptlist", departmentlist);
		return "saveUI";
	}

	public String edit() throws Exception {

		Department dept = deptservice.getById(getId());
		dept.setName(name);
		dept.setDescription(description);
		dept.setParent(deptservice.getById(parentId));
		deptservice.update(dept);
		return "toList";
	}

	public String editUI() throws Exception {

		List<Department> topList = deptservice.findTopList();
		List<Department> departmentlist = DepartmentUtil.getAllDepartments(topList);
		ActionContext.getContext().put("deptlist", departmentlist);

		// 数据回显
		Department dept = deptservice.getById(id);
			ActionContext.getContext().getValueStack().push(dept);

		if (dept.getParent() != null)
			parentId = dept.getParent().getId();

		return "saveUI";
	}

	public String delete() throws Exception {
		deptservice.delete(id);
		return "toList";
	}

}
