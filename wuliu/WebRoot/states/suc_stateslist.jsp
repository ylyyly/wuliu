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
   <link rel="stylesheet" href="css/common.css">
   <link rel="stylesheet" href="css/main.css">
   <script type="text/javascript" src="js/jquery.min.js"></script>
   <script type="text/javascript" src="js/colResizable-1.3.min.js"></script>
   <script type="text/javascript" src="js/common.js"></script>
   
   <script type="text/javascript">
      $(function(){  
        $(".list_table").colResizable({
          liveDrag:true,
          gripInnerHtml:"<div class='grip'></div>", 
          draggingClass:"dragging", 
          minWidth:30
        }); 
        
      }); 
   </script>
   
   <title>Document</title>
 </head>
 <body >
<div id="table" class="mt10">
        <div class="box span10 oh">

     <div id="table" class="mt10">
        <div class="box span10 oh">
         <div id="search_bar" class="mt10">
<form action="method!suc_stateslist" method="post">
       <div class="box">
          <div class="box_border">
            <div class="box_top">
            <b class="pl15">单号</b>
            <input type="text" name="danhao"  value="${danhao }" class="input-text lh25" size="20">
            <input type="submit"  class="btn btn82 btn_search" value="查询">   
            </div>
          </div>
        </div>     
        
        </form>
      </div>

        
         <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                  
                  <tr>
                   <th width="100%" align="left">派送列表</th>
                    </tr>
                    </table>
                   
                    
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                   <th width="15%">单号</th>
                    <th width="10%">物品</th>
                   <th width="10%">审核状态</th>
                   <th width="20%">审核时间</th>
                   <th width="10%">派送状态</th>
                   <th width="20%">派送时间</th>
                   <th width="15%">操作</th>
                    </tr>
                    
                <c:forEach items="${list2}" var="bean"> 
                <tr class="tr">
                <td>${bean.danhao }</td>
                <td>${bean.objects }</td>
                <td>${bean.audit.audits }</td>
                <td>${bean.audit.createtime }</td>
                <td><span style="color: red;">${bean.audit.states.send }</span></td>
                 <td>${bean.audit.states.endtime }</td>
                   <td>  
                   <form name="form1" action="">
                  <input type="button"  class="btn btn82 btn_config" value="详情"   onclick="location.href='method!statesupdate3?id=${bean.id }'"/>
                   </form>
                   </td>
                 </tr>
                 </c:forEach> 
              </table>
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                   <th width="50%" align="left"> ${pagerinfo }</th>
                    </tr>
              </table>
        </div>
     </div>
   
   </div>
   </div>
 </body>
 </html>
  