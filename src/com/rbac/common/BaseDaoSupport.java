package com.rbac.common;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;


@Component("baseDaoSupport")
public class BaseDaoSupport extends HibernateDaoSupport implements BaseDao {

	private static final Log log = LogFactory.getLog(BaseDaoSupport.class);

	/**
	 * 要持久化的类。
	 */
	private Class<?> persistentClass;

	/**
	 * 获得持久化类。
	 * 
	 * @return 返回持久 /** 构造函数。
	 * 
	 * @param persistentClass
	 *            要持久化的类。
	 */
	public BaseDaoSupport(Class<?> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public Class<?> getPersistClass() {
		return persistentClass;
	}

	public BaseDaoSupport() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssc.asset.persist.BaseDao#delete(java.lang.Object)
	 */
	public <T> void delete(T persistentInstance) {
		super.getHibernateTemplate().delete(persistentInstance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssc.asset.persist.BaseDao#merge(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public <T> T merge(T detachedInstance) {
		super.getHibernateTemplate().merge(detachedInstance);
		return detachedInstance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssc.asset.persist.BaseDao#findById(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public <T> T findById(Serializable id) {
		return (T) getHibernateTemplate().get(persistentClass.getName(), id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssc.asset.persist.BaseDao#findByExample(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByExample(T instance) {
		return getHibernateTemplate().findByExample(instance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssc.asset.persist.BaseDao#listAll()
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> listAll() {
		log.debug("Listing all instances of " + this.persistentClass);
		try {
			List<T> results = getSession().createCriteria(this.persistentClass)
					.list();
			log.debug("List all instances successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("List all instances failed", re);
			throw re;
		}
	}

	public <T> void save(T instance) {
		getHibernateTemplate().save(instance);
	}

	public <T> void saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
	}

}