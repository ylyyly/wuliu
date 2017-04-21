package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.States;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.StatesDao;









public class StatesDaoImpl extends HibernateDaoSupport implements  StatesDao{


	public void deleteBean(States States) {
		this.getHibernateTemplate().delete(States);
		
	}

	public void insertBean(States States) {
		this.getHibernateTemplate().save(States);
		
	}

	@SuppressWarnings("unchecked")
	public States selectBean(String where) {
		List<States> list = this.getHibernateTemplate().find("from States " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from States "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<States> selectBeanList(final int start,final int limit,final String where) {
		return (List<States>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<States> list = session.createQuery("from States "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(States States) {
		this.getHibernateTemplate().update(States);
		
	}
	
	
}
