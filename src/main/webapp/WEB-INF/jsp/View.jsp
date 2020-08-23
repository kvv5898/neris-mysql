<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style>
   <%@include file='styles/login.css' %>
</style>
<head>
  <meta charset="utf-8">
</head>

<body>
<p style="color: red;">${errorMessage}</p>
  <div class="box">
    <form method="POST" action="${pageContext.request.contextPath}/login">
        <input type="hidden" name="redirectId" value="${param.redirectId}" />
        <table >
           <tr>
              
              <td ><input type="text" name="userName" value= "${user.userName}"  placeholder="Логин"/> </td>
           </tr>
           <tr>
              
              <td><input type="password" name="password" value= "${user.password}" placeholder="Пароль"/> </td>
           </tr>
         
           <tr>
              <td colspan ="2">
                 <input type="submit" value= "Вход" />
                
              </td>
           </tr>
        </table>
     </form> 
</body>

</html>