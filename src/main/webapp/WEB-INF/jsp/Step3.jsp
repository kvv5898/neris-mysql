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
     <title>Insert Equipment Step3</title>
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
            </div>
           
     <p style="color: green;">${msg}</p>       
    <p style="color: red;">${error_gr}<br>${error_guar}<br>${error} </p>

<br>
<form method="POST" action="${pageContext.request.contextPath}/Step3">
<p><strong>Серийный номер - </strong><input style="text-align: center;" type="text" readonly="readonly" name="sn" value="${sn}"> <strong style="color: green;"> - &#10004;</strong></p>
<p><strong>Группа - </strong><input type="text" readonly="readonly" name="group_info" size="${sizegr}" value="${group_info}"/>
<input type="hidden" name="group_id" value="${group_id}"/>
<strong style="color: green;"> - &#10004;</strong></p>


<table>
<tr>
          <td>
         <select name="guar" required style="border-color:  ${color};">
          <option disabled selected >Выберите гарантийные обязательства</option>
          <c:forEach items="${guar}" var="guar">
		  <option value="${guar.guarantee_id}"> ${guar.org_name} / ${guar.month} / ${guar.contract}</option>
		  </c:forEach>
          </select>
          </td>
          </tr>
</table>
<p> <input type="reset"/>
 <input type="submit" value="Добавить гарантийные обязательства" name="Addguarstep1"></p>
 <input type="submit" value="Добавить запись в БД" name="submit" style="background: green;">
 <input type="submit" value="Назад" name="cancel" style="background: red;">
   </form>
  
            <div class="footer">
        <jsp:include page="_footer.jsp"></jsp:include>
    </div>
   </body>
</html>