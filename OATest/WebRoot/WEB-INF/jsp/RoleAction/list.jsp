<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<title>岗位列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript" src="script/jquery.js"></script>
<script language="javascript" src="script/pageCommon.js" charset="utf-8"></script>
<script language="javascript" src="script/PageUtils.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css" />
<script type="text/javascript">
	
</script>
</head>
<body>

	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="style/images/title_arrow.gif" /> 岗位管理
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align="CENTER" valign="MIDDLE" id="TableTitle">
					<td width="200px">岗位名称</td>
					<td width="300px">岗位说明</td>
					<td>相关操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer" datakey="roleList">
		<!-- 	<tbody id="TableData"> -->
				<s:iterator value="#rolelist">

					<tr class="TableDetail1 template">
						<td>${name}&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td><s:a action="role_delete?id=%{id}">删除</s:a> <s:a
								action="role_editUI?id=%{id}">修改</s:a> <s:a 
							action="role_setPrivilegeUI?id=%{id}">设置权限</s:a>
							</td>
					</tr> 
				</s:iterator>
			</tbody>
		</table>

		<!-- 其他功能超链接 -->
		<div id="TableTail">
			<div id="TableTail_inside">
				<s:a action="role_addUI"><img src="style/images/createNew.png" /></s:a>
			</div>
		</div>
	</div>
</body>
</html>
