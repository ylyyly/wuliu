package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Car;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.CarDao;


public class CarDaoImpl extends HibernateDaoSupport implements  CarDao{


	public void deleteBean(Car Car) {
		this.getHibernateTemplate().delete(Car);
		
	}

	public void insertBean(Car Car) {
		this.getHibernateTemplate().save(Car);
		
	}

	@SuppressWarnings("unchecked")
	public Car selectBean(String where) {
		List<Car> list = this.getHibernateTemplate().find("from Car " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Car "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Car> selectBeanList(final int start,final int limit,final String where) {
		return (List<Car>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Car> list = session.createQuery("from Car "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Car Car) {
		this.getHibernateTemplate().update(Car);
		
	}
	
	
}
