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
@Table(name="t_States")//派送
public class States implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private String send;//派送状态  1.未派送  2.派送中 3.派送完成    
	
	private Date endtime;//派送完成时间
	
	private User user;//指定派送人员
	
	private Car car;//指定派送车辆
	
	private int stateslock;

	



	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public int getStateslock() {
		return stateslock;
	}

	public void setStateslock(int stateslock) {
		this.stateslock = stateslock;
	}

	

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	@ManyToOne
	@JoinColumn(name="userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name="carid")
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

		

	

	
	
}
