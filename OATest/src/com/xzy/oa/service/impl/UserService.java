package com.xzy.oa.service.impl;

import java.util.List;

import com.xzy.oa.domain.User;


public interface UserService {

	public List<User> findAll();

	public void delete(int id);

	public void save(User r);

	public User getById(int id);

	public void update(User r);

	public User findByLoginNameAndPassword(String loginName, String password);
	
	//public List<User> getByIds(int[] userIds);
}
