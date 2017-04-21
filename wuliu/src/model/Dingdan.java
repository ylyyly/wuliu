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
@Table(name="t_Dingdan")//订单表
public class Dingdan implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;//主键
	
	private String danhao;//单号
	
	private String pass_name;//寄件人姓名
	
	private String start_add;//始发地

	private String pass_add;//寄件地址
	
	private String pass_tel;//寄件人电话
	
	private String close_name;//收件人姓名
	
	private String des_add;//目的地
	
	private String close_add;//收件人地址
	
	private String close_tel;//收件人电话
	
	private Date createtime;//添加时间

	private int dingdanlock;//状态
	
	private Audit audit;//关联审核
	
	private String objects;//物件

	
	
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDanhao() {
		return danhao;
	}

	public void setDanhao(String danhao) {
		this.danhao = danhao;
	}

	public String getPass_name() {
		return pass_name;
	}

	public void setPass_name(String passName) {
		pass_name = passName;
	}

	public String getStart_add() {
		return start_add;
	}

	public void setStart_add(String startAdd) {
		start_add = startAdd;
	}

	public String getPass_add() {
		return pass_add;
	}

	public void setPass_add(String passAdd) {
		pass_add = passAdd;
	}

	public String getPass_tel() {
		return pass_tel;
	}

	public void setPass_tel(String passTel) {
		pass_tel = passTel;
	}

	public String getClose_name() {
		return close_name;
	}

	public void setClose_name(String closeName) {
		close_name = closeName;
	}

	public String getDes_add() {
		return des_add;
	}

	public void setDes_add(String desAdd) {
		des_add = desAdd;
	}

	public String getClose_add() {
		return close_add;
	}

	public void setClose_add(String closeAdd) {
		close_add = closeAdd;
	}

	public String getClose_tel() {
		return close_tel;
	}

	public void setClose_tel(String closeTel) {
		close_tel = closeTel;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getDingdanlock() {
		return dingdanlock;
	}

	public void setDingdanlock(int dingdanlock) {
		this.dingdanlock = dingdanlock;
	}

	@ManyToOne
	@JoinColumn(name="auditid")
	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	public String getObjects() {
		return objects;
	}

	public void setObjects(String objects) {
		this.objects = objects;
	}

	

	
	
}
