package com.rbac.common;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;


@Component("baseDaoSupport")
public class BaseDaoSupport extends HibernateDaoSupport implements BaseDao {

	private static final Log log = LogFactory.getLog(BaseDaoSupport.class);

	public BaseDaoSupport() {
	}

	/**
	 * 保存实体
	 */
	public <T> void saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
	}
	
	/**
	 * 根据主键查找实体
	 */
	public <T> T findById(Class clazz, Serializable id) {
		return (T)getHibernateTemplate().get(clazz, id);
	}

}