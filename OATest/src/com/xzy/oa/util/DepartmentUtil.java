package com.xzy.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.xzy.oa.domain.Department;

public class DepartmentUtil {

	public static List<Department> getAllDepartments(List<Department> topList) {
		List<Department> list = new ArrayList<Department>();
		walkDepartmentTreeList(topList,"|-",list);
		return list;
	}

	private static void walkDepartmentTreeList(Collection<Department> topList,String prefix,List<Department> list){
		for(Department top : topList){
//			System.out.println(top.getName());
			Department tmp = new Department();
			tmp.setName(prefix + top.getName());
			tmp.setId(top.getId());
			list.add(tmp);
			walkDepartmentTreeList(top.getChildren(),"　" + prefix,list);
		}
	}
}
