<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'addUI.jsp' starting page</title>
  </head>
  
  <body>
    
    <s:form action="role_add">
    	<s:textfield name="name"></s:textfield>
    	<s:textarea name="description"></s:textarea>
    	<s:submit value="submit"></s:submit>
    </s:form>
  </body>
</html>
