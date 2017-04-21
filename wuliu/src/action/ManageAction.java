package action;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import model.Audit;
import model.Car;
import model.Gonggao;
import model.States;
import model.User;
import model.Dingdan;

import org.apache.struts2.ServletActionContext;
import util.Pager;
import util.Util;

import com.opensymphony.xwork2.ActionSupport;

import dao.AuditDao;
import dao.CarDao;
import dao.DingdanDao;
import dao.GonggaoDao;
import dao.StatesDao;
import dao.UserDao;

public class ManageAction extends ActionSupport{
	
	
	private static final long serialVersionUID = -4304509122548259589L;
	
	private UserDao userDao;
	private AuditDao auditDao;
	private DingdanDao dingdanDao;
	private GonggaoDao gonggaoDao;
	private StatesDao statesDao;
	private CarDao carDao;
	
	private String url = "./";
	
	
	
	
	public CarDao getCarDao() {
		return carDao;
	}

	public void setCarDao(CarDao carDao) {
		this.carDao = carDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public AuditDao getAuditDao() {
		return auditDao;
	}

	public void setAuditDao(AuditDao auditDao) {
		this.auditDao = auditDao;
	}

	public DingdanDao getDingdanDao() {
		return dingdanDao;
	}

	public void setDingdanDao(DingdanDao dingdanDao) {
		this.dingdanDao = dingdanDao;
	}

	public GonggaoDao getGonggaoDao() {
		return gonggaoDao;
	}

	public void setGonggaoDao(GonggaoDao gonggaoDao) {
		this.gonggaoDao = gonggaoDao;
	}

	public StatesDao getStatesDao() {
		return statesDao;
	}

	public void setStatesDao(StatesDao statesDao) {
		this.statesDao = statesDao;
	}

	//程序入口界面
	public String index(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Util.init(request);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			return "success2";
		}else{
			return "success1";
		}
	}
	
	
	//用户登陆操作
	public void login() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Integer role = Integer.parseInt(request.getParameter("role"));
		User user = userDao.selectBean(" where username='"+username+"' and password='"+password+"' and  userlock=0 and role="+role);
		if(user!=null){
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.setCharacterEncoding("gbk");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('登陆成功');window.location.href='index.jsp'; </script>");
		}else{
			response.setCharacterEncoding("gbk");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('用户名或者密码错误');window.location.href='login.jsp'; </script>");
		}

	}
	
	//用户退出操作
	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		this.setUrl("login.jsp");
		return SUCCESS;
	}
	
	
	//跳转到修改密码页面
	public String changepwd() {
		this.setUrl("user/password.jsp");
		return SUCCESS;
	}
	
	//修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"'");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('修改成功');</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误');</script>");
		}
	}
	
	
	//用户注册操作
	public void register() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();	
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String username = java.net.URLDecoder.decode(request.getParameter("username"), "utf-8");
		String password = java.net.URLDecoder.decode(request.getParameter("password"), "utf-8");
		String truename = java.net.URLDecoder.decode(request.getParameter("truename"), "utf-8");
		String telephone = java.net.URLDecoder.decode(request.getParameter("telephone"), "utf-8");
		String jiguan = java.net.URLDecoder.decode(request.getParameter("jiguan"), "utf-8");
		String address = java.net.URLDecoder.decode(request.getParameter("address"), "utf-8");
		String xingbie = java.net.URLDecoder.decode(request.getParameter("xingbie"), "utf-8");
		String age = java.net.URLDecoder.decode(request.getParameter("age"), "utf-8");
		String role = java.net.URLDecoder.decode(request.getParameter("role"), "utf-8");
		
		User user = userDao.selectBean(" where  username='"+username+"' and userlock=0");
		
		if(user==null){
			user = new User();
			user.setCreatetime(new Date());
			user.setPassword(password);
			user.setTruename(truename);
			user.setUsername(username);
			user.setTelephone(telephone);
			user.setJiguan(jiguan);
			user.setAddress(address);
			user.setXingbie(xingbie);
			user.setAge(age);
			user.setRole(Integer.parseInt(role));
			userDao.insertBean(user);
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("注册成功！您的用户名"+user.getUsername()+"");
			
		}else{
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("该用户名已经存在，请重新注册！");
		}
	}
	
	
	/*******************************管理员权限*****************************************/
	

	
	
	//用户信息管理
	public String userlist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String username=request.getParameter("username");
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(username!=null&&!"".equals(username) ){
        	sb.append("username like '%"+username+"%'");
        	sb.append(" and ");
        	request.setAttribute("username", username);
        }
        sb.append(" role=2 ");
        sb.append(" and ");
        sb.append(" userlock=0 order by id ");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = userDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<User> list = userDao.selectBeanList(currentpage-1, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!userlist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("user/userlist.jsp");
		return SUCCESS;
	}
	
	
	
	//用户信息管理
	public String userlist2(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");	
		String where =" where userlock=0 and id="+user.getId()+" order by id desc "; 
		List<User> list = userDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where);
		request.setAttribute("list", list);
		this.setUrl("user/userlist2.jsp");
		return SUCCESS;
	}
	
	//删除用户操作
	public void userdelete() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("id");
		User bean =userDao.selectBean(" where id= "+id);
		bean.setUserlock(1);
		userDao.updateBean(bean);
		response.setCharacterEncoding("gbk");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!userlist'; </script>");
		
	}
	
	//跳转到更新用户页面
	public String userupdate(){
		HttpServletRequest request = ServletActionContext.getRequest(); //**
		String id = request.getParameter("id");
		User bean =userDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("user/userupdate.jsp");
		return SUCCESS;
	}

	
	//更新用户操作
	public void userupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String truename = request.getParameter("truename");
		String telephone = request.getParameter("telephone");
		String jiguan = request.getParameter("jiguan");
		String address = request.getParameter("address");
		String xingbie = request.getParameter("xingbie");
		String age = request.getParameter("age");
		String id = request.getParameter("id");
		User bean =userDao.selectBean(" where id= "+id);
		bean.setTruename(truename);
		bean.setTelephone(telephone);
		bean.setJiguan(jiguan);
		bean.setAddress(address);
		bean.setAge(age);
		bean.setXingbie(xingbie);	
		userDao.updateBean(bean);
		response.setCharacterEncoding("gbk");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!userlist'; </script>");
		
	}
	
	
	//派送员信息管理
	public String ps_userlist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String username=request.getParameter("username");
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(username!=null&&!"".equals(username) ){
        	sb.append("username like '%"+username+"%'");
        	sb.append(" and ");
        	request.setAttribute("username", username);
        }
        sb.append(" role=3 ");
        sb.append(" and ");
        sb.append(" userlock=0 order by id ");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = userDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<User> list = userDao.selectBeanList(currentpage-1, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!ps_userlist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("user/ps_userlist.jsp");
		return SUCCESS;
	}
	
	
	
	//删除派送员操作
	public void ps_userdelete() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("id");
		User bean =userDao.selectBean(" where id= "+id);
		bean.setUserlock(1);
		userDao.updateBean(bean);
		response.setCharacterEncoding("gbk");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!ps_userlist'; </script>");
		
	}
	
	//跳转到更新派送员页面
	public String ps_userupdate(){
		HttpServletRequest request = ServletActionContext.getRequest(); //**
		String id = request.getParameter("id");
		User bean =userDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("user/ps_userupdate.jsp");
		return SUCCESS;
	}

	
	//更新派送员操作
	public void ps_userupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String truename = request.getParameter("truename");
		String telephone = request.getParameter("telephone");
		String jiguan = request.getParameter("jiguan");
		String address = request.getParameter("address");
		String xingbie = request.getParameter("xingbie");
		String age = request.getParameter("age");
		String id = request.getParameter("id");
		User bean =userDao.selectBean(" where id= "+id);
		bean.setTruename(truename);
		bean.setTelephone(telephone);
		bean.setJiguan(jiguan);
		bean.setAddress(address);
		bean.setAge(age);
		bean.setXingbie(xingbie);	
		userDao.updateBean(bean);
		response.setCharacterEncoding("gbk");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!ps_userlist'; </script>");
		
	}
	
	
	
	/**车队*/
	public String carlist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String bianhao = request.getParameter("bianhao");
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if(bianhao !=null &&!"".equals(bianhao)){
			sb.append(" bianhao like '%"+bianhao+"%' ");
			sb.append(" and ");

			request.setAttribute("bianhao", bianhao);
		}
		
		sb.append(" carlock=0 order by id desc ");

		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		
		long total = carDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		List<Car> list = carDao.selectBeanList((currentpage-1)*pagesize, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!carlist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("car/carlist.jsp");
		return SUCCESS;
	}
	
	
	//跳转到添加车队页面
	public String caradd(){
		this.setUrl("car/caradd.jsp");
		return SUCCESS;
	}
	
	
	//添加车队操作
	public void caradd2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String bianhao = request.getParameter("bianhao");
		String loads = request.getParameter("loads");
		Car bean=carDao.selectBean(" where bianhao='"+bianhao+"' and carlock=0 ");
		if(bean==null){
			bean = new Car();
			bean.setBianhao(bianhao);
			bean.setLoads(loads);
			bean.setCreatetime(new Date());
			carDao.insertBean(bean);
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!carlist'; </script>");
			
		}
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交失败，编号有重复');window.location.href='method!carlist'; </script>");
		
	}
	
	
	
	//删除车队操作
	public void cardelete() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("id");
		Car bean =carDao.selectBean(" where id= "+id);
		bean.setCarlock(1);
		carDao.updateBean(bean);
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!carlist'; </script>");
		
	}
	
	//跳转到更新车队页面
	public String carupdate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		Car bean =carDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("car/carupdate.jsp");
		return SUCCESS;
	}
	
	
	//更新车队操作
	public void carupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String bianhao = request.getParameter("bianhao");
		String loads = request.getParameter("loads");
		String id = request.getParameter("id");
		Car bean =carDao.selectBean(" where id= "+id);
		bean.setBianhao(bianhao);
		bean.setLoads(loads);
		bean.setCreatetime(new Date());
		carDao.updateBean(bean);
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!carlist'; </script>");
		
	}
	
	
	
	
	
	//订单信息管理
	public String dingdanlist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String danhao=request.getParameter("danhao");
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(danhao!=null&&!"".equals(danhao) ){
        	sb.append("danhao like '%"+danhao+"%'");
        	sb.append(" and ");
        	request.setAttribute("danhao", danhao);
        }
        sb.append("  dingdanlock=0 order by id ");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = dingdanDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Dingdan> list = dingdanDao.selectBeanList(currentpage-1, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!dingdanlist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("dingdan/dingdanlist.jsp");
		return SUCCESS;
	}
	
	
	//删除订单操作
	public void dingdandelete() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("id");
		Dingdan bean =dingdanDao.selectBean(" where id= "+id);
		bean.setDingdanlock(1);
		dingdanDao.updateBean(bean);
		response.setCharacterEncoding("gbk");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!dingdanlist'; </script>");
		
	}
		
	
	//跳转到添加订单
	public String dingdanadd(){
		this.setUrl("dingdan/dingdanadd.jsp");
		return SUCCESS;
	}
	
	
	//添加订单操作
	
	public void dingdanadd2()throws IOException{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		String pass_name=request.getParameter("pass_name");
		String start_add=request.getParameter("start_add");
		String pass_add=request.getParameter("pass_add");
		String pass_tel=request.getParameter("pass_tel");
		String close_name=request.getParameter("close_name");
		String des_add=request.getParameter("des_add");
		String close_add=request.getParameter("close_add");
		String close_tel=request.getParameter("close_tel");
		String objects=request.getParameter("objects");
		Date createtime=new Date();	
		Dingdan  bean=new Dingdan();
		bean.setDanhao(new Date().getTime()+"");
		bean.setPass_name(pass_name);
		bean.setStart_add(start_add);
		bean.setPass_add(pass_add);
		bean.setPass_tel(pass_tel);
		bean.setClose_name(close_name);
		bean.setDes_add(des_add);
		bean.setClose_add(close_add);
		bean.setClose_tel(close_tel);
		bean.setCreatetime(createtime);
		bean.setObjects(objects);
		dingdanDao.insertBean(bean);
		response.setCharacterEncoding("gbk");
		PrintWriter writer=response.getWriter();
		writer.print("<script language='javascript'>alert('提交成功');window.location.href='method!dingdanlist'; </script> ");

	   }
	
	
	//跳转到更新订单页面
	public String dingdanupdate(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String id = request.getParameter("id");
		Dingdan bean =dingdanDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("dingdan/dingdanupdate.jsp");
		return SUCCESS;
	}

	
	//更新订单操作
	public void dingdanupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String pass_name=request.getParameter("pass_name");
		String start_add=request.getParameter("start_add");
		String pass_add=request.getParameter("pass_add");
		String pass_tel=request.getParameter("pass_tel");
		String close_name=request.getParameter("close_name");
		String des_add=request.getParameter("des_add");
		String close_add=request.getParameter("close_add");
		String close_tel=request.getParameter("close_tel");
		String objects=request.getParameter("objects");
		Date createtime=new Date();
		String id = request.getParameter("id");
		Dingdan bean =dingdanDao.selectBean(" where id= "+id);
		bean.setPass_name(pass_name);
		bean.setStart_add(start_add);
		bean.setPass_add(pass_add);
		bean.setPass_tel(pass_tel);
		bean.setClose_name(close_name);
		bean.setDes_add(des_add);
		bean.setClose_add(close_add);
		bean.setClose_tel(close_tel);
		bean.setCreatetime(createtime);	
		bean.setObjects(objects);
		dingdanDao.updateBean(bean);
		response.setCharacterEncoding("gbk");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!dingdanlist'; </script>");
		
	}
	
	//跳转到查看订单页面
	public String dingdanupdate3(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String id = request.getParameter("id");
		Dingdan bean =dingdanDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("dingdan/dingdanupdate3.jsp");
		return SUCCESS;
	}
	
	
	
	//审核管理
	public String auditlist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String danhao=request.getParameter("danhao");
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(danhao!=null&&!"".equals(danhao) ){
        	sb.append("danhao like '%"+danhao+"%'");
        	sb.append(" and ");
        	request.setAttribute("danhao", danhao);
        }
        sb.append("  dingdanlock=0 order by id ");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = dingdanDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Dingdan> list = dingdanDao.selectBeanList(currentpage-1, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!auditlist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("audit/auditlist.jsp");
		return SUCCESS;
	}
	
	
	
	
	//跳转到审核页面
	public String auditupdate() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpServletResponse response = ServletActionContext.getResponse();	
		String id = request.getParameter("id");
		Dingdan bean =dingdanDao.selectBean(" where audit.audits='通过' and id= "+id);
		if(bean!=null){
			response.setCharacterEncoding("utf8");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('该订单已审核通过，不可再进行状态更改');window.location.href='method!auditlist'; </script>");
			return null;
		}else{
			bean =dingdanDao.selectBean(" where id= "+id);
			request.setAttribute("bean", bean);
			this.setUrl("audit/auditupdate.jsp");		
		}
			
		
		return SUCCESS;
		
	}
	
	
	//审核操作
	public void auditupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();	
		String id = request.getParameter("id");
		String audits=request.getParameter("audits");
		String becase=request.getParameter("becase");
		Date createtime=new Date();
		
		Dingdan d =dingdanDao.selectBean(" where id= "+id);
		if(d.getAudit()==null){
			Audit bean =new Audit();
			bean.setAudits(audits);
			bean.setBecase(becase);
			bean.setCreatetime(createtime);	
			auditDao.insertBean(bean);
			d.setAudit(bean);
			dingdanDao.updateBean(d);
			response.setCharacterEncoding("gbk");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!auditlist'; </script>");
			
		}else{
			d.getAudit().setAudits(audits);
			d.getAudit().setBecase(becase);
			d.getAudit().setCreatetime(createtime);	
			auditDao.updateBean(d.getAudit());
			response.setCharacterEncoding("gbk");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('更新成功');window.location.href='method!auditlist'; </script>");
			
		}
		
	}
	
	
	//派送管理
	public String stateslist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String danhao=request.getParameter("danhao");
		
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(danhao!=null&&!"".equals(danhao) ){
        	sb.append(" danhao like '%"+danhao+"%'");
        	sb.append(" and ");
        	request.setAttribute("danhao", danhao);
        }
        
        sb.append("  audit.audits='通过' and dingdanlock=0 order by id ");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = dingdanDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Dingdan> list2 = dingdanDao.selectBeanList(currentpage-1, pagesize, where);
		request.setAttribute("list2", list2);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!stateslist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("states/stateslist.jsp");
		return SUCCESS;
	}
	
	
	
	
	
	//跳转到派送页面
	public String statesupdate() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpServletResponse response = ServletActionContext.getResponse();	
		List<User> list = userDao.selectBeanList(0, 9999, " where role=3 and userlock=0 ");
		List<Car> list2 = carDao.selectBeanList(0, 9999, " where  carlock=0 ");
		String id = request.getParameter("id");
		Dingdan bean =dingdanDao.selectBean(" where audit.states.send='派送完成' and id= "+id);
		if(bean!=null){
			response.setCharacterEncoding("utf8");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('该物件已经完成派送，不可再进行状态更改');window.location.href='method!stateslist'; </script>");
			return null;
		}else{
			bean =dingdanDao.selectBean(" where id= "+id);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			this.setUrl("states/statesupdate.jsp");	
			
		}

		return SUCCESS;
		
	}
	
	
	
	//派送操作
	public void statesupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();	
		String id = request.getParameter("id");
		String send=request.getParameter("send");
		String user=request.getParameter("user");
		String car=request.getParameter("car");
		Date endtime=new Date();
		
		Audit d =auditDao.selectBean(" where id= "+id);
		if(d.getStates()==null){
			States bean =new States();
			bean.setSend(send);
			bean.setUser(userDao.selectBean(" where id= "+user));
			bean.setCar(carDao.selectBean(" where id= "+car));
			bean.setEndtime(endtime);	
			statesDao.insertBean(bean);
			d.setStates(bean);
			auditDao.updateBean(d);
			response.setCharacterEncoding("gbk");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!stateslist'; </script>");
			
		}else{
			d.getStates().setSend(send);
			d.getStates().setEndtime(endtime);	
			d.getStates().setUser(userDao.selectBean(" where id= "+user));
			d.getStates().setCar(carDao.selectBean(" where id= "+car));
			statesDao.updateBean(d.getStates());
			response.setCharacterEncoding("gbk");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('更新成功');window.location.href='method!stateslist'; </script>");
			
		}
		
	}
	
	
	
	//派送成功页面
	public String stateslist2(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String danhao=request.getParameter("danhao");
		
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(danhao!=null&&!"".equals(danhao) ){
        	sb.append("danhao like '%"+danhao+"%'");
        	sb.append(" and ");
        	request.setAttribute("danhao", danhao);
        }
       
        sb.append("  audit.states.send='派送完成' and dingdanlock=0 order by id ");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = dingdanDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Dingdan> list2 = dingdanDao.selectBeanList(currentpage-1, pagesize, where);
		request.setAttribute("list2", list2);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!stateslist2", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("states/stateslist2.jsp");
		return SUCCESS;
	}
	 
	
	//跳转到派送页面
	public String statesupdate3() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String id = request.getParameter("id");
		Dingdan bean =dingdanDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("states/statesupdate3.jsp");	

		return SUCCESS;
		
	}
	
	/*公告*/
	//公告列表
	public String gonggaolist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String biaoti = request.getParameter("biaoti");
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if(biaoti !=null &&!"".equals(biaoti)){
			sb.append(" biaoti like '%"+biaoti+"%' ");
			sb.append(" and ");

			request.setAttribute("biaoti", biaoti);
		}
		
		sb.append(" gonggaolock=0 order by id desc ");

		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		
		long total = gonggaoDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		List<Gonggao> list = gonggaoDao.selectBeanList((currentpage-1)*pagesize, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!gonggaolist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("gonggao/gonggaolist.jsp");
		return SUCCESS;
	}
	
	
	//跳转到添加公告页面
	public String gonggaoadd(){
		this.setUrl("gonggao/gonggaoadd.jsp");
		return SUCCESS;
	}
	
	
	//添加公告操作
	public void gonggaoadd2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String biaoti = request.getParameter("biaoti");
		String content = request.getParameter("content");
		Gonggao bean = new Gonggao();
		bean.setBiaoti(biaoti);
		bean.setContent(content);
		bean.setCreatetime(new Date());
		gonggaoDao.insertBean(bean);
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!gonggaolist'; </script>");
		
	}
	
	
	
	//删除公告操作
	public void gonggaodelete() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("id");
		Gonggao bean =gonggaoDao.selectBean(" where id= "+id);
		bean.setGonggaolock(1);
		gonggaoDao.updateBean(bean);
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!gonggaolist'; </script>");
		
	}
	
	//跳转到更新公告页面
	public String gonggaoupdate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		Gonggao bean =gonggaoDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("gonggao/gonggaoupdate.jsp");
		return SUCCESS;
	}
	
	
	//更新公告操作
	public void gonggaoupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String biaoti = request.getParameter("biaoti");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		Gonggao bean =gonggaoDao.selectBean(" where id= "+id);
		bean.setBiaoti(biaoti);
		bean.setContent(content);
		gonggaoDao.updateBean(bean);
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		PrintWriter writer = response.getWriter();
		writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!gonggaolist'; </script>");
		
	}
	
	//跳转到查看公告页面
	public String gonggaoupdate3(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		Gonggao bean =gonggaoDao.selectBean(" where id= "+id);
		request.setAttribute("bean", bean);
		this.setUrl("gonggao/gonggaoupdate3.jsp");
		return SUCCESS;
	}
	
	
	
	
	/*******************************用户权限*****************************************/
	
	//审核查询
	public String auditlist2(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String danhao=request.getParameter("danhao");
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(danhao!=null&&!"".equals(danhao) ){
        	sb.append("danhao like '%"+danhao+"%'");
        	sb.append(" and ");
        	request.setAttribute("danhao", danhao);
        }
        sb.append("  dingdanlock=0 order by id ");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = dingdanDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Dingdan> list = dingdanDao.selectBeanList(currentpage-1, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!auditlist2", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("audit/auditlist2.jsp");
		return SUCCESS;
	}
	
	
	
	
	//派送查询
	public String stateslist3(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String danhao=request.getParameter("danhao");
		
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(danhao!=null&&!"".equals(danhao) ){
        	sb.append("danhao like '%"+danhao+"%'");
        	sb.append(" and ");
        	request.setAttribute("danhao", danhao);
        }
        
        sb.append("  audit.audits='通过' and dingdanlock=0 order by id ");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = dingdanDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Dingdan> list2 = dingdanDao.selectBeanList(currentpage-1, pagesize, where);
		request.setAttribute("list2", list2);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!stateslist3", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("states/stateslist3.jsp");
		return SUCCESS;
	}
	
	
	//公告列表
	public String gonggaolist2(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String biaoti = request.getParameter("biaoti");
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if(biaoti !=null &&!"".equals(biaoti)){
			sb.append(" biaoti like '%"+biaoti+"%' ");
			sb.append(" and ");

			request.setAttribute("biaoti", biaoti);
		}
		
		sb.append(" gonggaolock=0 order by id desc ");

		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		
		long total = gonggaoDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		List<Gonggao> list = gonggaoDao.selectBeanList((currentpage-1)*pagesize, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!gonggaolist2", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("gonggao/gonggaolist2.jsp");
		return SUCCESS;
	}
	
	
	/**********************派送员*******************************/
	
	//派送员派送管理
	public String fin_stateslist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");	
		String danhao=request.getParameter("danhao");
		
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(danhao!=null&&!"".equals(danhao) ){
        	sb.append(" danhao like '%"+danhao+"%'");
        	sb.append(" and ");
        	request.setAttribute("danhao", danhao);
        }
        
        sb.append("  audit.audits='通过' and dingdanlock=0 and audit.states.user='"+user.getId()+"' order by id ");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = dingdanDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Dingdan> list2 = dingdanDao.selectBeanList(currentpage-1, pagesize, where);
		request.setAttribute("list2", list2);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!fin_stateslist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("states/fin_stateslist.jsp");
		return SUCCESS;
	}
	
	
	
	//跳转到派送页面
	public String fin_statesupdate() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpServletResponse response = ServletActionContext.getResponse();	
		String id = request.getParameter("id");
		Dingdan bean =dingdanDao.selectBean(" where audit.states.send='派送完成' and id= "+id);
		if(bean!=null){
			response.setCharacterEncoding("utf8");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('该物件已经完成派送，不可再进行状态更改');window.location.href='method!fin_stateslist'; </script>");
			return null;
		}else{
			bean =dingdanDao.selectBean(" where id= "+id);
			request.setAttribute("bean", bean);
			this.setUrl("states/fin_statesupdate.jsp");	
			
		}

		return SUCCESS;
		
	}
	
	
	
	//派送操作
	public void fin_statesupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();	
		String id = request.getParameter("id");
		String send=request.getParameter("send");
		Date endtime=new Date();
		
		Audit d =auditDao.selectBean(" where id= "+id);
		if(d.getStates()==null){
			States bean =new States();
			bean.setSend(send);
			bean.setEndtime(endtime);	
			statesDao.insertBean(bean);
			d.setStates(bean);
			auditDao.updateBean(d);
			response.setCharacterEncoding("gbk");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('提交成功');window.location.href='method!fin_stateslist'; </script>");
			
		}else{
			d.getStates().setSend(send);
			d.getStates().setEndtime(endtime);	
			statesDao.updateBean(d.getStates());
			response.setCharacterEncoding("gbk");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('更新成功');window.location.href='method!fin_stateslist'; </script>");
			
		}
		
	}
	
	//派送成功页面
	public String suc_stateslist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");	
		String danhao=request.getParameter("danhao");
		
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if(danhao!=null&&!"".equals(danhao) ){
        	sb.append("danhao like '%"+danhao+"%'");
        	sb.append(" and ");
        	request.setAttribute("danhao", danhao);
        }
       
        sb.append("  audit.states.send='派送完成' and audit.states.user='"+user.getId()+"' and dingdanlock=0 order by id ");
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum") != null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		String where =sb.toString();
		long total = dingdanDao.selectBeanCount(where.replaceAll(" order by id desc", ""));
		List<Dingdan> list2 = dingdanDao.selectBeanList(currentpage-1, pagesize, where);
		request.setAttribute("list2", list2);
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "method!suc_stateslist", "共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		this.setUrl("states/suc_stateslist.jsp");
		return SUCCESS;
	}
	
}
