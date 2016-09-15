package com.bdsoft.bdceo.ibatis.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Select;

import com.bdsoft.bdceo.ibatis.bean.User;

public interface UserMapper {

	public User loadById(int id);

	@Select("select * from User where id=#{pk}")
	public User getUser(int pk);

	public HashMap<String, Object> load(int id);
	
	public void save(User user);
	
	public void update(User user);
	
	public void delete(int id);
}
