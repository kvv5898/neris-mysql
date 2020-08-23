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
     <title>Authorization History</title>
  </head>
  
  <body>
   <div class="header">
        <jsp:include page="_header.jsp"></jsp:include>
            </div>
  
	<br>
	<p>Активных сессии: ${active_sessions}</p>
	<p>Активных пользователи: ${active_users}</p>
	<br>
	
	<table align="center" border="1">
	<thead>
	<th>Id</th>
	<th>Начало сессии</th>
	<th>Конец сессии</th>
	<th>IP</th>
	<th>HOST</th>
	<th>GETWAY</th>
	<th>Пользователь</th>
	<th>Пароль</th>
	<th>ID сессии</th>
	<th>Статус</th>
	</thead>   
	<c:forEach var="a" items="${auth}">
	<tr>
	<td>${a.id()}</td>
	
	<td>${a.date_start()}</td>
	<td>${a.date_end()}</td>
	
	<td>${a.ip}</td>
	<td>${a.host}</td>
	<td>${a.getway}</td>
	
	<td>${a.user_in}</td>
	
	<td>${a.pass_in}</td>
	
	<td>${a.session}</td>
	
	<td>${a.status}</td>
	</tr>
	</c:forEach>
	</table>
        <div class="footer">
        <jsp:include page="_footer.jsp"></jsp:include>
    </div>
  </body>

</html>