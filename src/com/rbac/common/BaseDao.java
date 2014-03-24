package com.rbac.common;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
public interface BaseDao {
	
	public abstract <T> void save(T instance);

	public abstract <T> void delete(T persistentInstance);

	public abstract <T> T merge(T detachedInstance);

	public abstract <T> T findById(Serializable id);

	public abstract <T> List<T> findByExample(T instance);
	
	public abstract <T> List<T> listAll();
	
}