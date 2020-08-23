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
     <title>Add guarantee step2</title>
  </head>
  
 <body>
 <script type="text/javascript" language="javascript">
        function DisableBackButton() {
            window.history.forward()
        }
        DisableBackButton();
        window.onload = DisableBackButton;
        window.onpageshow = function (evt) { if (evt.persisted) DisableBackButton() }
        window.onunload = function () { void (0) }
    </script>
 <div class="header">
        <jsp:include page="_header.jsp"></jsp:include>
        <br>
            </div>
            <div class="error">
    <p style="color: red;">${error}</p>
    <p style="color: green;">${msg}</p>
</div>
<form method="POST" id="form1" action="${pageContext.request.contextPath}/Addguarstep2">
<input type="hidden" name="sn" value="${sn}">
<input type="hidden" name="group_id" value="${group_id}"/>
<input type="hidden" name="group_info" value="${group_info}"/>
<input type="hidden" name="org_organization_id" value="${org_organization_id}"/>
<input type="hidden"  name="org_name" value="${org_name}"/>
</form>
 
 
<form method="POST" id="form2" action="${pageContext.request.contextPath}/Addguarstep2">
<input type="hidden" name="sn" value="${sn}">
<input type="hidden" name="group_id" value="${group_id}"/>
<input type="hidden" name="group_info" value="${group_info}"/>
<input type="hidden" name="org_organization_id" value="${org_organization_id}"/>

<i>Организация</i><input type="text" style="border: 0"  readonly="readonly" name="org_name" value="${org_name}"><strong style="color: ${color}"> - &#10004</strong>
<p>Выберите или укажите № договора на гарантийные обязательства:</p>
       
       <input type="text" list="validity" required="required" name="validity" value="" style="border-color: ${color}">
               <datalist id="validity">
                <c:forEach items="${validity}" var="validity">
		  <option value="${validity.contract}">${validity.contract} - ${validity.org_name} / ${validity.date} / ${validity.month} / ${validity.validity_info}</option>
		  </c:forEach>	
               </datalist>
   </form>
 <input type="submit" value="Далее" name="submit" style="background: green;" form="form2"/>
 <input type="submit" value="Назад" name="back" style="background: red;" form="form1"/>
   
  
            <div class="footer">
        <jsp:include page="_footer.jsp"></jsp:include>
    </div>
   </body>
</html>