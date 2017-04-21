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
   <script language="javascript" type="text/javascript">
function checkform()
{
	
	 if (document.getElementById('userid').value=="")
	{
		alert("派送员必须选择");
		return false;
	}
	return true;	
}
</script>
   

<script language="javascript" type="text/javascript" src="js/jquery.min.js"></script>

</head>


	<body>
<form action="method!fin_statesupdate2" method="post"  onsubmit="return checkform()">
<input type="hidden" name="id" value="${bean.id }"/>

    <div id="regdiv">
         <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">派送</b></div>
            <div class="box_center">
              
               <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
                 
                
                
                <tr>
                  <td class="td_right">单号:</td>
                  <td><input type="text"   value="${bean.danhao }" class="input-text lh30" size="30" readonly="readonly"/></td>
                </tr>
                
              
                
                
                 <tr>
                  <td class="td_right">派送:</td>
                   <td class="">
                    <span class="fl">
                      <div class="select_border"> 
                        <div class="select_containers "> 
                        <select name="send" class="select">
         <option value="" >--请选择--</option>
          <option value="派送中" <c:if test="${bean.audit.states.send=='派送中' }">selected</c:if> >派送中</option>
         <option value="派送完成" <c:if test="${bean.audit.states.send=='派送完成' }">selected</c:if>>派送完成</option>  
         </select>
                        </div> 
                      </div> 
                    </span>
                  </td>
                </tr>
                
                
                 
                 <tr>
                   <td class="td_right">&nbsp;</td>
                   <td class="">
                    <input type="submit" class="btn btn82 btn_save2" value="保存" onclick="checkregisterform()"/>
                    <input type="button" class="btn btn82 btn_nochecked" value="取消" onclick="window.history.go(-1);"/>
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
