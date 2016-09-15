<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>控制台</title>
</head>
<body>
	<%=new Date().toLocaleString()%>
	<br />
	<br />
	<form action="/spring/rate.do">
		<input type="text" name="tradeDate"><input type="submit">
	</form>
	<br />
	<a href="/spring/groupAdmin.do?op=update">GroupAdminDelegate-update</a>
</body>
</html>