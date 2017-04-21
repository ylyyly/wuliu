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
 <body>
  <div class="container">
     <div id="search_bar" class="mt10">
       <div class="box">
          <div class="box_border">
            <div class="box_top">
            <b class="pl15">姓名</b>
            <input type="text" name="name" class="input-text lh25" size="10">
             <b class="pl15">年龄</b>
            <input type="text" name="name" class="input-text lh25" size="10">
            <input type="button" name="button" class="btn btn82 btn_search" value="查询">   
            </div>
          </div>
        </div>
      </div>
    
    
     <div id="table" class="mt10">
        <div class="box span10 oh">
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                   <th width="100">标题</th>
                   <th width="100">标题</th>
                   <th width="100">标题</th>
                   <th >操作</th>
                    </tr>
                    
                <tr class="tr">
                   <td>aad</td>
                   <td>aad</td>
                   <td>aad</td>
                   <td>
                  <form name="form1" action="">
                   <input type="button"  class="btn btn82 btn_del" value="删除"  onclick="form1.action='add.jsp';form1.submit();"/>
                    <input type="button"  class="btn btn82 btn_config" value="修改"  onclick="form1.action='login.jsp';form1.submit();"/>
                   </form>
                   </td>
                
                 </tr>
                 <tr class="tr"> 
                   <td>aad</td>
                   <td>aad</td>
                   <td>aad</td>
                   <td>
                   <input type="button" name="button" class="btn btn82 btn_del" value="删除"> 
                   <input type="button" name="button" class="btn btn82 btn_config" value="修改"> 
                   </td>
                
                 </tr>
                 <tr class="tr"> 
                   <td>aad</td>
                   <td>aad</td>
                   <td>aad</td>
                   <td>
                   <input type="button" name="button" class="btn btn82 btn_del" value="删除"> 
                   <input type="button" name="button" class="btn btn82 btn_config" value="修改"> 
                   </td>
                
                 </tr>
                 <tr class="tr">
                   <td>aad</td>
                   <td>aad</td>
                   <td>aad</td>
                   <td>
                   <input type="button" name="button" class="btn btn82 btn_del" value="删除"> 
                   <input type="button" name="button" class="btn btn82 btn_config" value="修改"> 
                   </td>
                
                 </tr>
                 <tr class="tr">
                   <td>aad</td>
                   <td>aad</td>
                   <td>aad</td>
                   <td>
                   <input type="button" name="button" class="btn btn82 btn_del" value="删除"> 
                   <input type="button" name="button" class="btn btn82 btn_config" value="修改"> 
                   </td>
                
                 </tr>
                 <tr class="tr">
                   <td>aad</td>
                   <td>aad</td>
                   <td>aad</td>
                   <td>
                   <input type="button" name="button" class="btn btn82 btn_del" value="删除"> 
                   <input type="button" name="button" class="btn btn82 btn_config" value="修改"> 
                   </td>
                
                 </tr>
                 <tr class="tr">
                   <td>aad</td>
                   <td>aad</td>
                   <td>aad</td>
                   <td>
                   <input type="button" name="button" class="btn btn82 btn_del" value="删除"> 
                   <input type="button" name="button" class="btn btn82 btn_config" value="修改"> 
                   </td>
                
                 </tr>
                 <tr class="tr">
                   <td>aad</td>
                   <td>aad</td>
                   <td>aad</td>
                   <td>
                   <input type="button" name="button" class="btn btn82 btn_del" value="删除"> 
                   <input type="button" name="button" class="btn btn82 btn_config" value="修改"> 
                   </td>
                
                 </tr>
                 <tr class="tr">
                   <td>aad</td>
                   <td>aad</td>
                   <td>aad</td>
                   <td>
                   <input type="button" name="button" class="btn btn82 btn_del" value="删除"> 
                   <input type="button" name="button" class="btn btn82 btn_config" value="修改"> 
                   </td>
                
                 </tr>
              
              </table>
              <div class="page mt10">
                <div class="pagination">
                  <ul>
                      <li class="first-child"><a href="#">首页</a></li>
                      <li class="disabled"><span>上一页</span></li>
                      <li class="active"><span>1</span></li>
                      <li><a href="#">2</a></li>
                      <li><a href="#">下一页</a></li>
                      <li class="last-child"><a href="#">末页</a></li>
                  </ul>
                </div>

              </div>
        </div>
     </div>
     
   </div> 
 </body>
 </html>
  