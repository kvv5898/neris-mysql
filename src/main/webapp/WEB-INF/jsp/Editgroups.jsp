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
      <title>Add Groups</title>
   </head>
   <body>
    <jsp:include page="_header.jsp"></jsp:include>
        <br>
      <h3>Группы оборудования</h3>
      <p style="color: red;">${error}</p>
      <p style="color: green;">${msg}</p>
      
      <form method="POST" name="form1" action="${pageContext.request.contextPath}/Editgroups">
    
         <table style="width: auto;">
            <tr>
               <td><strong style="color: ${desccolor}">Описание</strong><font color="red">*</font></td>
               
               <c:choose>
   <c:when test = "${repeatdesc != null}">
   <td><p><input type="text" value="${repeatdesc}" name="description" readonly="readonly"></p></td></c:when>
   <c:otherwise><td><p><input type="text" list="description" name="description">
               <datalist id="description" >
                <c:forEach items="${description}" var="desc">
		  <option value="${desc}"></option>
		  </c:forEach>	
               </datalist>
               </p></td></c:otherwise>
   </c:choose>
   
              <td><strong style="color: ${modelcolor}">Модель</strong><font color="red">*</font></td>
               <c:choose>
   <c:when test = "${repeatmodel != null}">
   <td><p><input type="text" value="${repeatmodel}" name="model" readonly="readonly"></p></td></c:when>          
     <c:otherwise><td><p><input type="text" list="model" name="model">
               <datalist id="model">
               <c:forEach items="${model}" var="model">
		  <option value="${model}"></option>
		  </c:forEach>	
               </datalist>
               </p></td>  </c:otherwise>       
     </c:choose>
     
              
               
               <td><strong style="color: ${group_infocolor}">Дополнительная информация</strong><font color="red">*</font></td>
               <c:choose>
               <c:when test = "${repeatgroup_info != null}">
                <td><p><input type="text" value="${repeatgroup_info}" name="group_info" readonly="readonly"></p></td></c:when>   
               <c:otherwise><td><p><input type="text" list="group_info" name="group_info">
               <datalist id="group_info">
                <c:forEach items="${group_info}" var="group_info">
		  <option value="${group_info}"></option>
		  </c:forEach>	
               </datalist>
               </p></td></c:otherwise>       
     </c:choose>
            <tr>
               <td colspan="2">
                <input type="submit" value="Добавить" name="submit" style="background: green;" />                   
                <input type="reset"/>
                <a href="home" class="ssilka">Отмена</a>
               </td>
            </tr>
         </table>
      </form> 
      
      <br>
      <font color="red">*</font> - <strong>Поля обязательно для заполнения</strong>
      
      <table align="center" border="1">
	<thead>
	<th>Id</th>
	<th>Описание</th>
	<th>Модель</th>
	<th>Информация</th>
	</thead>   
	<c:forEach var="group" items="${gr}">
	<tr>
	<td>${group.group_id}</td>
	<td>${group.description}</td>
	<td>${group.model}</td>
	<td>${group.group_info}</td>
	</tr>
	</c:forEach>
	</table>
      
    <jsp:include page="_footer.jsp"></jsp:include>    
      </body>
      </html>
       