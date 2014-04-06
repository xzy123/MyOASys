package com.xzy.oa.dao;

import java.util.List;

import com.xzy.oa.domain.User;

public interface UserDao{
	public void save(User t);

	public void delete(int id);

	public void update(User t);

	public User getById(int id);

	public List<User> findAll();

	public List<User> getByIds(int[] ids);
}
