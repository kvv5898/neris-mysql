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
    <title>Add Org</title>
</head>

<body>
    <jsp:include page="_header.jsp"></jsp:include>
    <br>
    <h3>Организации</h3>
    <p style="color: red;">${error}</p>
    <p style="color: green;">${msg}</p>
<br>
<font color="red">*</font> - <strong>Поля обязательно для заполнения</strong>

    <form method="POST" name="form1" action="${pageContext.request.contextPath}/Editorg">
        <table style="width: auto;">
            <tr>
                <td><strong style="color: ${org_namecolor}">Название</strong>
                    <font color="red">*</font>
                </td>
                <td>
                    <p><input type="text" required name="org_name"></p>
                </td>
                <td><strong style="color: ${telcolor}">Контактные данные</strong>
                    <font color="red">*</font>
                </td>
                <td>
                    <p><input type="text" required name="tel"></p>
                </td>

                <td><strong style="color: ${addresscolor}">Адрес</strong>
                    <font color="red">*</font>
                </td>
                <td>
                    <p><input type="text" required name="address"></p>
                </td>
                <td><strong style="color: ${organization_infocolor}">Дополнительная информация</strong>
                    <font color="red">*</font>
                </td>
                <td>
                    <p><input type="text" required name="organization_info"></p>
                </td>
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
            <th>Название</th>
            <th>Контактные данные</th>
            <th>Адрес</th>
            <th>Дополнительная информация</th>
        </thead>
        <c:forEach var="org" items="${org}">
            <tr>
                <td>${org.organization_id}</td>
                <td>${org.org_name}</td>
                <td>${org.tel}</td>
                <td>${org.address}</td>
                <td>${org.organization_info}</td>
            </tr>
        </c:forEach>
    </table>

    <jsp:include page="_footer.jsp"></jsp:include>
</body>

</html>