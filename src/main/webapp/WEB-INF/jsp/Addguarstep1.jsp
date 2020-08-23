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
     <title>Add guarantee step1</title>
  </head>
  
 <body>
 <div class="header">
        <jsp:include page="_header.jsp"></jsp:include>
        <br>
            </div>
            <div class="error">
    <p style="color: red;">${error}</p>
</div>
 
<form method="POST" id="form1" action="${pageContext.request.contextPath}/Addguarstep1">
<input type="hidden" name="sn" value="${sn}">
<input type="hidden" name="group_id" value="${group_id}"/>
<input type="hidden" name="group_info" value="${group_info}"/>
<input type="hidden" name="sizegr" value="${sizegr}"/>  
</form> 
 
<form method="POST" id="form2" action="${pageContext.request.contextPath}/Addguarstep1"> 
<input type="hidden" name="sn" value="${sn}">
<input type="hidden" name="group_id" value="${group_id}"/>
<input type="hidden" name="group_info" value="${group_info}"/>  
<input type="hidden" name="sizegr" value="${sizegr}"/>       
<strong>Выберите или укажите название организации</strong>     
        <p><input type="text" list="org" name="org" required style="border-color: ${color}">
               <datalist id="org">
                <c:forEach items="${org}" var="org">
		  <option value="${org.org_name}">${org.organization_id} - ${org.org_name} / ${org.address} / ${org.organization_info}</option>
		  </c:forEach>	
               </datalist></p>
    </form>     
        
 <input type="submit" value="Далее" name="submit" style="background: green;" form="form2"/>
 <input type="submit" value="Назад" name="back" style="background: red;" form="form1"/>
   
  
            <div class="footer">
        <jsp:include page="_footer.jsp"></jsp:include>
    </div>
   </body>
</html>