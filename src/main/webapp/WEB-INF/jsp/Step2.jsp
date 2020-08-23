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
    <title>Insert Equipment Step2</title>
    <script src="http://code.jquery.com/jquery.min.js" type="text/javascript"></script>
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
        <p style="color: red;">${errorgr}</p>
        <p style="color: red;">${errorguar}</p>
    </div>

    <form method="POST" action="${pageContext.request.contextPath}/Step2">
        <p><strong>Серийный номер</strong> - <input style="text-align: center;" type="text" name="sn" id="sn"
                readonly="readonly" value="${sn}"> <strong style="color: green;"> - &#10004;</strong></p>
        <script type="text/javascript">
            $(document).keydown(function (e) { if (e.keyCode == 8) $('#sn').focus(); });
        </script>
        <table>
            <tr>
                <td>
                    <select name="gr" required style="border-color:  ${color};">
                            <option disabled selected >Выберите группу</option>
                            <c:forEach items="${gr}" var="gr">
                                <option value="${gr.group_id}"> ${gr.description} / ${gr.model} / ${gr.group_info}/
                                </option>
                            </c:forEach>
                        </select>
                </td>
            </tr>
        </table>
        <input type="reset" />
        <input type="submit" value="Добавить новую группу" name="Addgroups">
        <p><input type="submit" value="Далее" name="submit" style="background: green;"/>
        <input type="button" value="Отмена" style="background: red;" onClick='location.href="${pageContext.request.contextPath}/home"'></p>
    </form>

    <div class="footer">
        <jsp:include page="_footer.jsp"></jsp:include>
    </div>
</body>

</html>