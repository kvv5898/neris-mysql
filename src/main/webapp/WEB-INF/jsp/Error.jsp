<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style>
<%@include file='styles/div.css' %>
.center {
	position: absolute;
	width: 100%;
	top: 50%;
	text-align: center;
	animation: blink 2s infinite;
	text-align: center;
}

@keyframes blink {from { opacity:0;
	
}

to {
	opacity: 2;
}
}
</style>
  <head>
  <title>Error</title>
  </head>
  <body>
 <div class="header">
        <jsp:include page="_header.jsp"></jsp:include>
        <br>
        </div>
	<div class="center">
		<P style="color: red;">ERROR</P>
	</div>
	 <div class="footer">
        <jsp:include page="_footer.jsp"></jsp:include>
    </div>
   </body>
</html>