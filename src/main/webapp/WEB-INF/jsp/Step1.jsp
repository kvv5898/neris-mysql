<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style>
   <%@include file='styles/div.css' %>
</style>
  <head>
     <meta charset="UTF-8">
     <title>Insert Equipment Step1</title>
  </head>
  
 <body>
 <div class="header">
        <jsp:include page="_header.jsp"></jsp:include>
        <br>
            </div>
            <div class="error">
    <p style="color: red;">${error}</p>
</div>
 
<form method="POST" action="${pageContext.request.contextPath}/Step1"> 
<table><tr><td><input type="text" value="${sn}" id="sn" name="sn" required placeholder="Укажите SN"></td></tr></table>
<input type="submit" value="Далее" style="background: green;">
<input type="button" value="Назад" style="background: red;" onClick='location.href="${pageContext.request.contextPath}/home"'>
   </form>
   <form>
   <br>
           </form>
            <div class="footer">
        <jsp:include page="_footer.jsp"></jsp:include>
    </div>
   </body>
</html>