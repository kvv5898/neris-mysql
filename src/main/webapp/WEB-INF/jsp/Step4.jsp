<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style>
    <%@include file='styles/div.css'%>
</style>

<head>
    <meta charset="UTF-8">
    <title>Insert Equipment</title>
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

   
     
        <p> <input type="button" value="Добавить новый SN" onClick='location.href="${pageContext.request.contextPath}/Step1"'>
            <input type="button" value="Показать поступившее оборудование" onClick='location.href="${pageContext.request.contextPath}/equipmentreceived"'></p>

</body>

</html>