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
    <title>Add SN Groups</title>
    
    <script src="http://code.jquery.com/jquery-2.2.4.js" 
                type="text/javascript"></script>
        <script src="js/org-ajax.js" type="text/javascript"></script>
    
   
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
                       
    
    <c:forEach items="${error}" var="error">
   <ul style="color: red;"><li>${error} - duplicate</li></ul>
    </c:forEach>
    <c:forEach items="${msg}" var="msg">
     <ul style="color: green;"><li> ${msg} - Added successfully </li></ul>
     </c:forEach>


<br>
    <form method="POST" action="${pageContext.request.contextPath}/Sngroup">
    
   <select name="groups" required="required">
    <option disabled selected value=''>Выберите группу</option>
    <c:forEach items="${groups}" var="groups">
    <option value="${groups.group_id}">${groups.description} / ${groups.model} / ${groups.group_info}</option>
    </c:forEach>
   </select> 
  
   
   <select name="guarantee" required="required">
    <option disabled selected value=''>Выберите гарантийные обязательства</option>
    <c:forEach items="${guarantee}" var="guarantee">
    <option value="${guarantee.guarantee_id}">${guarantee.org_name} / ${guarantee.month} / ${guarantee.contract} / ${guarantee.validity_info}</option>
    </c:forEach>
   </select> 
 <input type="text" value="${sn}" id="sn" name="sn" required placeholder="Укажите SN через запятую (,)">
    <p><input type="submit" value="Далее" name="submit" style="background: green;">
    <input type="button" value="Назад" style="background: red;" onClick='location.href="${pageContext.request.contextPath}/home"'></p>
   
    </form>
    <table align="center" border="1">
	<thead>
	<th>ID оборудования</th>
	<th>Серийный номер</th>
	<th>Модель</th>
	<th>Организация</th>
	<th>Гарантийный кантракт</th>
		</thead> 
		<c:forEach var="add" items="${add}">
			<tr>
				<td>${add.equipment_id}</td>
				<td>${add.sn}</td>
				<td>${add.model}</td>
				<td>${add.org_name}</td>
				<td>${add.contract}</td>
			</tr>
		</c:forEach>
	</table>

    <div class="footer">
        <jsp:include page="_footer.jsp"></jsp:include>
    </div>
</body>

</html>