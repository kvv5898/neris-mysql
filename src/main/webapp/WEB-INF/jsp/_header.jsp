<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>History discounts</title>
<style type="text/css">
<%@include file='styles/menu.css' %>
</style>
</head>
<body>

	<table border="3" align="center">
		<th width="150">

			<ul id="menu">
				<li><a href="#">Меню</a>
					<ul class="sub-menu">
						<li><a href="${pageContext.request.contextPath}/home">Главная</a></li>
						<li><a href="${pageContext.request.contextPath}/Step1">Добавить оборудование по SN</a></li>
						<li><a href="${pageContext.request.contextPath}/Sngroup">Добавить оборудование группой</a></li>
						<li><a href="${pageContext.request.contextPath}/StatusEdit">Изменить статуст SN</a></li>
						<li><a href="${pageContext.request.contextPath}/equipmentreceived">Поступившее оборудование</a></li>
						<li><a href="${pageContext.request.contextPath}/Editgroups">Добавить тип обордования</a></li>
						<li><a href="${pageContext.request.contextPath}/Editorg">Добавить организацию</a></li>
						<li><a href="${pageContext.request.contextPath}/Editvalidity">Добавить гарантийные обязательства</a></li>
						<li><a href="${pageContext.request.contextPath}/exit">Выход</a></li>
					</ul></li>

			</ul>
		</th>
		<th width="800">
		<P>Welcome to the site "Network Equipment Registration Information System"</P>
		</th>
		<th width="150">

			<ul id="menu">
				<li><a href="#">Управление</a>
					<ul class="sub-menu">
						<li><a href="${pageContext.request.contextPath}/auth">История посещений</a></li>
						<li><a href="${pageContext.request.contextPath}/Logsqlview">Журнал sql запросов</a></li>

					</ul></li>

			</ul>
		</th>
	</table>
</body>
</html>