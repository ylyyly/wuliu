package dao;

import java.util.List;

import model.Dingdan;


public interface DingdanDao  {
	
	
	
	public void insertBean(Dingdan Dingdan);
	
	public void deleteBean(Dingdan Dingdan);
	
	public void updateBean(Dingdan Dingdan);

	public Dingdan selectBean(String where);
	
	public List<Dingdan> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
