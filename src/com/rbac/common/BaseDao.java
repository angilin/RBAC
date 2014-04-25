package com.rbac.common;

import java.io.Serializable;


@SuppressWarnings("unchecked")
public interface BaseDao {
	
	public <T> void saveOrUpdate(T t);
	
	public <T> T findById(Class clazz, Serializable id);
	
}