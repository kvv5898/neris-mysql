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
     <title>Edit SN Status</title>
  </head>
  
 <body>
 <div class="header">
        <jsp:include page="_header.jsp"></jsp:include>
        <br>
            </div>
            <div class="error">
    <p style="color: red;">${error}</p>
     <p style="color: green;">${msg}</p>
</div>
 
<form method="POST" action="${pageContext.request.contextPath}/StatusEdit"> 

                    <select name="sn" required >
                            <option disabled selected value="" >Выберите SN</option>
                            <c:forEach items="${sn}" var="sn">
                                <option value="${sn}"> ${sn}</option>
                            </c:forEach>
                        </select>
                
        <input type="text" list="status" name="status" required placeholder="Выберите или укажите новый статус SN" size="35">
               <datalist id="status">
                <c:forEach items="${status}" var="status">
		  <option value="${status}">${status}</option>
		  </c:forEach>	
               </datalist>
               
         <select name="org" required >
                            <option disabled selected value="" >Выберите организацию</option>
                            <c:forEach items="${org}" var="org">
                                <option value="${org}"> ${org}</option>
                            </c:forEach>
                        </select>       

        <input type="reset" />
        <p><input type="submit" value="Далее" name="submit" style="background: green;"/>
        <input type="button" value="Отмена" style="background: red;" onClick='location.href="${pageContext.request.contextPath}/home"'></p>
    </form>

 <table align="center" border="1">
        <thead>
           <th>Id</th>
           <th>SN</th>
           <th>Дата</th>
           <th>Статус</th>
           <th>Организация</th>
        </thead>
        <c:forEach var="history" items="${history}">
            <tr>
                <td>${history.history_id}</td>
                <td>${history.sn}</td>
                <td>${history.date}</td>
                <td>${history.status}</td>
                <td>${history.organization}</td>
            </tr>
        </c:forEach>
    </table>    
    
            <div class="footer">
        <jsp:include page="_footer.jsp"></jsp:include>
    </div>
   </body>
</html>