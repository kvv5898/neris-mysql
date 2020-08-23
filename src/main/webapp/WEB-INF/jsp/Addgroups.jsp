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
      <title>Add Groups</title>
   </head>
   <body>
   <div class="header">
        <jsp:include page="_header.jsp"></jsp:include>
        <br>
            </div>
      <h3>Добавление новой группы</h3>
      <p style="color: red;">${error}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/Addgroups">
      <p><strong>Серийный номер - </strong><input style="text-align: center;" type="text" readonly="readonly" name="sn" value="${sn}"> <strong style="color: green;"> - &#10004;</strong></p> 
        
               <p><strong style="color: ${desccolor}">Описание</strong><font color="red">*</font>
               
               <c:choose>
   <c:when test = "${repeatdesc != null}">
   <input type="text" value="${repeatdesc}" name="description" readonly="readonly"></c:when>
   <c:otherwise><input type="text" list="description" name="description">
               <datalist id="description" >
                <c:forEach items="${description}" var="desc">
		  <option value="${desc}"></option>
		  </c:forEach>	
               </datalist>
               </c:otherwise>
   </c:choose>
   
              <strong style="color: ${modelcolor}">Модель</strong><font color="red">*</font>
               <c:choose>
   <c:when test = "${repeatmodel != null}">
  <input type="text" value="${repeatmodel}" name="model" readonly="readonly"></c:when>          
     <c:otherwise><td><input type="text" list="model" name="model">
               <datalist id="model">
               <c:forEach items="${model}" var="model">
		  <option value="${model}"></option>
		  </c:forEach>	
               </datalist>
               </c:otherwise>       
     </c:choose>
     
              
               
               <strong style="color: ${group_infocolor}">Дополнительная информация</strong><font color="red">*</font>
               <c:choose>
               <c:when test = "${repeatgroup_info != null}">
               <input type="text" value="${repeatgroup_info}" name="group_info" readonly="readonly"></c:when>   
               <c:otherwise><input type="text" list="group_info" name="group_info">
               <datalist id="group_info">
                <c:forEach items="${group_info}" var="group_info">
		  <option value="${group_info}"></option>
		  </c:forEach>	
               </datalist>
               </c:otherwise>       
     </c:choose>
            </p>
                                 
                   <input type="submit" value="Добавить" name="submit" onclick="msg()" style="background: green;" />
                   <input type="reset" />
                   <input type="submit" value="Назад" name="back" style="background: red;">
      </form>
      <br>
      <font color="red">*</font> - <strong>Поля обязательно для заполнения</strong>
      <div class="footer">
        <jsp:include page="_footer.jsp"></jsp:include>
    </div>
      </body>
      </html>
       