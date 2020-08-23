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
<title>New View</title>

</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<p style="color: red;">${errorString}</p>
	<strong>Поступившее оборудование:</strong>
	<table border="1">
	<thead>
	<th>ID оборудования</th>
	<th>Серийный номер</th>
	<th>Модель</th>
	<th>Организация</th>
	<th>Гарантийный кантракт</th>
		</thead> 
		<c:forEach var="add" items="${add}">
			<tr>
				<td>${add.equipment_id}</td>
				<td>${add.sn}</td>
				<td>${add.model}</td>
				<td>${add.org_name}</td>
				<td>${add.contract}</td>
			</tr>
		</c:forEach>
	</table>
 <jsp:include page="_footer.jsp"></jsp:include>	
</body>
</html>