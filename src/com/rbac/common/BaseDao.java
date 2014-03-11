package com.rbac.common;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
public interface BaseDao {
	
	/**
	 * 
	 */
	public abstract <T> void save(T instance);

	/**
	 * 删除对象。
	 * @param persistentInstance 要删除的对象
	 */
	public abstract <T> void delete(T persistentInstance);

	/**
	 * 合并对象。
	 * @param detachedInstance 要合并的脱管对象。
	 * @return 合并后的关联对象。
	 */
	public abstract <T> T merge(T detachedInstance);

	/**
	 * 根据主键查找对象。
	 * @param id 主键值。
	 * @return 找到的对象。
	 */
	public abstract <T> T findById(Serializable id);

	/**
	 * 根据例子查找对象。
	 * @param instance 作为例子的对象。
	 * @return 找到的对象列表。
	 */
	public abstract <T> List<T> findByExample(T instance);
	
	/**
	 * 列出所有对象。
	 * @return 所有对象列表。
	 */
	public abstract <T> List<T> listAll();
	
}