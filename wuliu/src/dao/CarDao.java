package dao;

import java.util.List;

import model.Car;


public interface CarDao  {
	
	
	
	public void insertBean(Car Car);
	
	public void deleteBean(Car Car);
	
	public void updateBean(Car Car);

	public Car selectBean(String where);
	
	public List<Car> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
