<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style>
    <%@include file='styles/tab_stat.css'%>
</style>

<head>
<meta charset="UTF-8">
<title>Log SQL</title>

</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<p style="color: red;">${errorString}</p>
	<strong>Журнал SQL запросов:</strong>
	<table border="1">
	<thead>
	<th>Log ID</th>
	<th>User Name</th>
	<th>Date</th>
	<th>SQL code</th>
		</thead> 
		<c:forEach var="logsql" items="${logsql}">
			<tr>
				<td>${logsql.log_id}</td>
				<td>${logsql.user_name}</td>
				<td>${logsql.date}</td>
				<td>${logsql.sql_code}</td>
			</tr>
		</c:forEach>
	</table>
 <jsp:include page="_footer.jsp"></jsp:include>	
</body>
</html>