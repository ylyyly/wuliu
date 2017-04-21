package dao;

import java.util.List;

import model.Audit;


public interface AuditDao  {
	
	
	
	public void insertBean(Audit Audit);
	
	public void deleteBean(Audit Audit);
	
	public void updateBean(Audit Audit);

	public Audit selectBean(String where);
	
	public List<Audit> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
