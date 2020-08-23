<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style>
   <%@include file='styles/div.css' %>
</style>
  <head>
  <title>Home page</title>
  </head>
  <body>
 <div class="header">
        <jsp:include page="_header.jsp"></jsp:include>
        <br>
            </div>
 
  <body>
	<div class="home">
		<strong>Система предназначена для учёта оборудования. 
		Оборудование можно добавить как последовательно по серийному номеру (SN), так и группой. 
		Серийные номера являются уникальными, принадлежат организации, входят в группу и имеют гарантийные условие. 
		В ходе эксплуатации могут менять статус (например, эксплуатация - ремонт -эксплуатация - демонтаж - списание). 
		Для поиска и устранения возможных проблем, ведётся журнал sql запросов к базе данных.</strong>
	</div>
	 <div class="footer">
        <jsp:include page="_footer.jsp"></jsp:include>
    </div>
   </body>
</html>