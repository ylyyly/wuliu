﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		
	<style type="text/css">
#regdiv {
	position: absolute;
	width: 600px;
	height: 700px;
	background-image: url(img/b2c_04.jpg);
}
</style>
   <link rel="stylesheet" href="css/common.css"/>
   <link rel="stylesheet" href="css/main.css"/>
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

<script language="javascript" type="text/javascript" src="js/jquery.min.js"></script>

</head>


	<body>

<input type="hidden" name="id" value="${bean.id }"/>
    <div id="regdiv">
         <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">修改订单信息</b></div>
            <div class="box_center">
              
               <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">

                <tr>
                  <td class="td_right">寄件人姓名:</td>
                  <td><input type="text" name="pass_name"  value="${bean.pass_name }" class="input-text lh30" size="40" readonly="readonly"/></td>
                </tr>
                <tr>
                  <td class="td_right">始发地:</td>
                  <td><input type="text" name="start_add"  value="${bean.start_add }" class="input-text lh30" size="40" readonly="readonly" /></td>
                </tr>
                <tr>
                  <td class="td_right">寄件地址:</td>
                  <td><input type="text" name="pass_add"  value="${bean.pass_add }" class="input-text lh30" size="40" readonly="readonly"/></td>
                </tr>
                <tr>
                  <td class="td_right">寄件人电话:</td>
                  <td><input type="text" name="pass_tel" value="${bean.pass_tel }" class="input-text lh30" size="40" readonly="readonly"/></td>
                </tr>
                <tr>
                  <td class="td_right">收件人姓名:</td>
                  <td><input type="text" name="close_name"  value="${bean.close_name }" class="input-text lh30" size="40" readonly="readonly"/></td>
                </tr>
                <tr>
                  <td class="td_right">目的地:</td>
                  <td><input type="text" name="des_add"  value="${bean.des_add }" class="input-text lh30" size="40" readonly="readonly"/></td>
                </tr>
               <tr>
                  <td class="td_right">收件人地址:</td>
                  <td><input type="text" name="close_add"  value="${bean.close_add }" class="input-text lh30" size="40" readonly="readonly"/></td>
                </tr>
                 <tr>
                  <td class="td_right">收件人电话:</td>
                  <td><input type="text" name="close_tel"  value="${bean.close_tel }" class="input-text lh30" size="40" readonly="readonly"/></td>
                </tr>
                <tr>
                  <td class="td_right">物品:</td>
                  <td><input type="text" name="objects"  value="${bean.objects }" class="input-text lh30" size="40" readonly="readonly"/></td>
                </tr>
                 
                 <tr>
                   <td class="td_right">&nbsp;</td>
                   <td class="">
                    
                   <input class="btn btn82 btn_save2" onclick="javascript:history.go(-1);"  type="button" value="返回" />
                   </td>
                 </tr>
               </table>
               
            </div>
          </div>
        </div>
     </div>

</div>

</form>
	</body>

</html>
