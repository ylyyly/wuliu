package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Audit;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.AuditDao;









public class AuditDaoImpl extends HibernateDaoSupport implements  AuditDao{


	public void deleteBean(Audit Audit) {
		this.getHibernateTemplate().delete(Audit);
		
	}

	public void insertBean(Audit Audit) {
		this.getHibernateTemplate().save(Audit);
		
	}

	@SuppressWarnings("unchecked")
	public Audit selectBean(String where) {
		List<Audit> list = this.getHibernateTemplate().find("from Audit " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Audit "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Audit> selectBeanList(final int start,final int limit,final String where) {
		return (List<Audit>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Audit> list = session.createQuery("from Audit "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Audit Audit) {
		this.getHibernateTemplate().update(Audit);
		
	}
	
	
}
