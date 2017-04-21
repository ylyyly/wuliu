package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_Car")//车队
public class Car implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;//主键
	
	private String bianhao;//车辆编号
	
	private String loads;//路线
	
	private Date createtime;//添加时间
	
	private int carlock;//删除状态

	



	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBianhao() {
		return bianhao;
	}

	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}

	public String getLoads() {
		return loads;
	}

	public void setLoads(String loads) {
		this.loads = loads;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getCarlock() {
		return carlock;
	}

	public void setCarlock(int carlock) {
		this.carlock = carlock;
	}

	
	
	

	
}
