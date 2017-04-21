package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Audit")//审核
public class Audit implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;//主键
	
	private String audits;//审核状态  1.未审核  2.通过  3.不予通过
	
	private String becase;//原因      不通过原因：该物件是违规物品，不允许发货
	
	
	
	private Date createtime;//添加时间
	
	private States states;//关联派送
	
	
	private int auditlock;//删除状态

	



	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAudits() {
		return audits;
	}

	public void setAudits(String audits) {
		this.audits = audits;
	}

	public String getBecase() {
		return becase;
	}

	public void setBecase(String becase) {
		this.becase = becase;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getAuditlock() {
		return auditlock;
	}

	public void setAuditlock(int auditlock) {
		this.auditlock = auditlock;
	}

	@ManyToOne
	@JoinColumn(name="statesid")
	public States getStates() {
		return states;
	}

	public void setStates(States states) {
		this.states = states;
	}

	
	

	
}
