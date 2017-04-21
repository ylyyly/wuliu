package dao;

import java.util.List;

import model.States;


public interface StatesDao  {
	
	
	
	public void insertBean(States States);
	
	public void deleteBean(States States);
	
	public void updateBean(States States);

	public States selectBean(String where);
	
	public List<States> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
