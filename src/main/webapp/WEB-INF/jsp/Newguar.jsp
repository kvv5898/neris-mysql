<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style>
   <%@include file='styles/div.css' %>
  input {
	text-align: center;
}
</style>
  <head>
     <meta charset="UTF-8">
     <title>Add New Guarantee</title>
  </head>
  
 <body>
 <div class="header">
        <jsp:include page="_header.jsp"></jsp:include>
        <br>
            </div>
            <div class="error">
    <p style="color: red;">${error}</p>
    <p style="color: red;">${errorguar}</p>
</div>
 
 
 <form method="POST" id="form1" action="${pageContext.request.contextPath}/Newguar">
<p><input type="hidden" name="sn" value="${sn}"></p>
<p><input type="hidden" name="group_id" value="${group_id}"/>
<input type="hidden" name="sizegr" value="${sizegr}"/>
<input type="hidden"  name="group_info" value="${group_info}"/></p>
<input type="hidden"  name="org_organization_id" value="${org_organization_id}"/>
<p><input type="hidden" name="org_name" value="${org_name}"/></p>
</form> 
 
 
<form method="POST" id="form2" action="${pageContext.request.contextPath}/Newguar">
<p><input type="hidden" readonly="readonly" name="sn" value="${sn}"></p>
<p><input type="hidden" readonly="readonly" name="group_id" value="${group_id}"/>
<input type="hidden" name="sizegr" value="${sizegr}"/>
<input type="hidden" readonly="readonly" name="group_info" value="${group_info}"/></p>
<input type="hidden" readonly="readonly" name="org_organization_id" value="${org_organization_id}"/>
<p>Организация: <input type="text" style="border: 0" readonly="readonly" name="org_name" value="${org_name}"/></p>

       <table border="1">
         <strong>Необходимо заполнить:</strong>
     <thead>
                <th>Контактный телефон</th>
                <th>Адрес</th>
                <th>Доп. информация по организации</th>
                <th>Дата начала гарантийных обязательств</th>
                <th>Гарантийный срок</th>
                <th>Контракт</th>
                <th>Доп. информация по гарантии</th>
       </thead>     
        <tr>
               <td><input type="text" name="tel" placeholder="+375xxxxxxx" required class="filterInput"></td>
               <td><input type="text" name="address" placeholder="г. Витебск ул. Горовца д.12 каб.17 " required class="filterInput"></td>
               <td><input type="text" name="organization_info" placeholder="№ договора, Контактное лицо" required class="filterInput"></td>
               <td><input type="date" name="date" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" class="filterInput"></td>
               <td><input type="number" name="month" placeholder="кол. месяцев" required class="filterInput"></td>
               <td><input type="text" name="contract" placeholder="№ гарантийного документа" required class="filterInput"></td>
               <td><input type="text" name="validity_info" placeholder="условие гарантии" required class="filterInput"></td>
</tr>
               </table>
   </form>
 <input type="submit" value="Добавить" name="submit" style="background: green;" form="form2"/>
 <input type="submit" value="Назад" name="back" style="background: red;" form="form1"/>  
  
            <div class="footer">
        <jsp:include page="_footer.jsp"></jsp:include>
    </div>
   </body>
</html>