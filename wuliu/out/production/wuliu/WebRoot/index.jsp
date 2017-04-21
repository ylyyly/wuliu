<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if((model.User)session.getAttribute("user")==null){
	response.sendRedirect("login.jsp");
	return;
}
%>
<!doctype html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/style.css">
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
  <script type="text/javascript">
  $(function(){
      $(".sideMenu").slide({
         titCell:"h3", 
         targetCell:"ul",
         defaultIndex:0, 
         effect:'slideDown', 
         delayTime:'500' , 
         trigger:'click', 
         triggerTime:'150', 
         defaultPlay:true, 
         returnDefault:false,
         easing:'easeInQuint',
         endFun:function(){
              scrollWW();
         }
       });
      $(window).resize(function() {
          scrollWW();
      });
  });
  function scrollWW(){
    if($(".side").height()<$(".sideMenu").height()){
       $(".scroll").show();
       var pos = $(".sideMenu ul:visible").position().top-38;
       $('.sideMenu').animate({top:-pos});
    }else{
       $(".scroll").hide();
       $('.sideMenu').animate({top:0});
       n=1;
    }
  } 

var n=1;
function menuScroll(num){
  var Scroll = $('.sideMenu');
  var ScrollP = $('.sideMenu').position();
  /*alert(n);
  alert(ScrollP.top);*/
  if(num==1){
     Scroll.animate({top:ScrollP.top-38});
     n = n+1;
  }else{
    if (ScrollP.top > -38 && ScrollP.top != 0) { ScrollP.top = -38; }
    if (ScrollP.top<0) {
      Scroll.animate({top:38+ScrollP.top});
    }else{
      n=1;
    }
    if(n>1){
      n = n-1;
    }
  }
}
  </script>
  <title>后台首页</title>
</head>
<body>
    <div class="top" align="center">
      <div id="top_t" >
        <div class="fl" style="padding-top:15px " >
        <span style="font-size: 35px; font-weight: bold;color: white;">天天物流公司信息管理系统
        </span>
        </div>
        <div id="photo_info" class="fr" >
          <div id="photo" class="fl">
            <a href="#"><img src="images/a.png" alt="" width="60" height="60"></a>
            
          </div>
          <div style="margin:40px;padding-right:20px " align="center">
              <a href="method!changepwd" target="right"><span style="font-size: 15px;font-weight: bold;color: white;">密码修改&nbsp;||</span></a>
              <a href="method!loginout" ><span style="font-size: 15px;font-weight: bold;color: white;">退出&nbsp;</span></a>
          </div>
        </div>
      </div>
      <div id="side_here">
        <div id="side_here_l" class="fl"></div>
        <div id="here_area" class="fl">
                  
                   当前角色： <c:if test="${user.role==1}">系统管理员</c:if>
			     <c:if test="${user.role==2}">操作员</c:if>
			     <c:if test="${user.role==3}">派送员</c:if>
					&nbsp;&nbsp;&nbsp;&nbsp;
	         当前用户：${user.username }		
        </div>
      </div>
    </div>
    
    <c:if test="${user.role==1}">
    <div class="side">
        <div class="sideMenu" style="margin:0 auto">
          <h3>用户管理</h3>
          <ul>
            <li><a href="method!userlist" target="right">用户管理</a></li>
             <li><a href="method!ps_userlist" target="right">派送员管理</a></li>
          </ul>
          <h3> 车队管理</h3>
          <ul>
            <li><a href="method!carlist" target="right">车队管理</a></li>
          </ul>
          <h3> 订单管理</h3>
          <ul>
            <li><a href="method!dingdanlist" target="right">订单管理</a></li>
          </ul>
          <h3>订单审核</h3>
          <ul>
            <li><a href="method!auditlist" target="right">订单审核</a></li>
           
          </ul>
          
           <h3>订单派送</h3>
          <ul>
            <li><a href="method!stateslist" target="right">订单派送</a></li>
           
          </ul>
           <h3>派送成功的订单</h3>
          <ul>
            <li><a href="method!stateslist2" target="right">订单派送成功查询</a></li>
           
          </ul>
          <h3>公告管理</h3>
          <ul>
            <li><a href="method!gonggaolist" target="right">公告管理</a></li>
           
          </ul>
          
       </div>
    </div>
    </c:if>
    
    <c:if test="${user.role==2}">
     <div class="side">
        <div class="sideMenu" style="margin:0 auto">
          <h3>个人信息查询</h3>
          <ul>
            <li><a href="method!userlist2" target="right">个人信息查询</a></li> 
          </ul>
          <h3> 订单管理</h3>
          <ul>
            <li><a href="method!dingdanlist" target="right">订单管理</a></li>
          </ul>
          <h3>订单审核查询</h3>
          <ul>
            <li><a href="method!auditlist2" target="right">订单审核查询</a></li>
           
          </ul>
          
           <h3>订单派送查询</h3>
          <ul>
            <li><a href="method!stateslist3" target="right">订单派送查询</a></li>
           
          </ul>
           <h3>派送成功的订单</h3>
          <ul>
            <li><a href="method!stateslist2" target="right">订单派送成功查询</a></li>
           
          </ul>
          <h3>公告查询</h3>
          <ul>
            <li><a href="method!gonggaolist2" target="right">公告查询</a></li>
           
          </ul>

       </div>
    </div>
    </c:if>
    
    
    
    <c:if test="${user.role==3}">
     <div class="side">
        <div class="sideMenu" style="margin:0 auto">
          <h3>个人信息查询</h3>
          <ul>
            <li><a href="method!userlist2" target="right">个人信息查询</a></li> 
          </ul>
         
          
           <h3>订单派送</h3>
          <ul>
            <li><a href="method!fin_stateslist" target="right">订单派送</a></li>
           
          </ul>
           <h3>派送成功的订单</h3>
          <ul>
            <li><a href="method!suc_stateslist" target="right">订单派送成功查询</a></li>
           
          </ul>
          <h3>公告查询</h3>
          <ul>
            <li><a href="method!gonggaolist2" target="right">公告查询</a></li>
           
          </ul>

       </div>
    </div>
    </c:if>
    
    
    <div class="main">
       <iframe name="right" id="rightMain" src="main.jsp" frameborder="no" scrolling="auto" width="100%" height="auto" allowtransparency="true"></iframe>
    </div>
    <div class="bottom">
      <div id="bottom_bg">版权</div>
    </div>
    <div class="scroll">
          <a href="javascript:;" class="per" title="使用鼠标滚轴滚动侧栏" onclick="menuScroll(1);"></a>
          <a href="javascript:;" class="next" title="使用鼠标滚轴滚动侧栏" onclick="menuScroll(2);"></a>
    </div>
</body>

</html>
   
 