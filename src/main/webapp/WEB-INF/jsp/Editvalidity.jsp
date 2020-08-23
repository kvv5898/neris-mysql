<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<style>
    <%@include file='styles/div.css'%>
   .ssilka {
color:black;
background: red; 
text-decoration: none; 
border: 1px solid black; 
text-align: center; 
padding: .1em .5em
} 
</style>

<head>
    <meta charset="UTF-8">
    <title>Add Validity</title>
</head>

<body>
    <jsp:include page="_header.jsp"></jsp:include>
    <br>
    <h3>Гарантийные обязательства</h3>
    <p style="color: red;">${error}</p>
    <p style="color: green;">${msg}</p>
<br>
    <form method="POST" action="${pageContext.request.contextPath}/Editvalidity">
    
    <table style="width: auto; border: 1px solid black">
              <thead>
                <th>Название организации</th>
                <th>Дата начала гарантийных обязательств</th>
                <th>Гарантийный срок</th>
                <th>Контракт</th>
                <th>Доп. информация по гарантии</th>
       </thead>     
        <tr>
        <td> <select name="org" required>
    <option disabled selected value=''>Выберите организацию</option>
    <c:forEach items="${org}" var="org">
    <option value="${org.org_name}">${org.org_name}</option>
    </c:forEach>
   </select> 
   </td>
               <td><input type="date" name="date" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" class="filterInput"></td>
               <td><input type="number" name="month" placeholder="кол. месяцев" required class="filterInput"></td>
               <td><input type="text" name="contract" placeholder="Контракт" required class="filterInput"></td>
               <td><input type="text" name="validity_info" placeholder="условие гарантии" required class="filterInput"></td>
</tr>
              
                <tr>
                <td colspan="2">
                    <input type="submit" value="Добавить" name="submit" style="background: green;" />
                    <input type="reset" />
                    <a href="home" class="ssilka">Отмена</a>
                </td>
            </tr>
             </table>
                  </form>
    
        
           
    <br>

    <table align="center" border="1">
        <thead>
            <th>Id</th>
           <th>Дата начала гарантийных обязательств</th>
           <th>Гарантийный срок</th>
           <th>Название организации</th>
           <th>Контракт</th>
           <th>Доп. информация по гарантии</th>
        </thead>
        <c:forEach var="validity" items="${validity}">
            <tr>
                <td>${validity.validity_id}</td>
                <td>${validity.date}</td>
                <td>${validity.month}</td>
                <td>${validity.org_name}</td>
                <td>${validity.contract}</td>
                <td>${validity.validity_info}</td>
            </tr>
        </c:forEach>
    </table>
<div class="footer">
    <jsp:include page="_footer.jsp"></jsp:include>
    </div>
</body>

</html>