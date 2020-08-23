<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
 <%@include file='styles/login.css' %>
</style>
</head>
<body>

<h2>Welcome to the NERIS website</h2>
<p style="color: red;">${errorMessage}</p>


<div>                  
  <form class="modal-content animate" method="POST" action="${pageContext.request.contextPath}/login">
    <div class="container">
      <label for="uname"><b>Username</b></label>
      <input type="text" placeholder="Enter Username" name="userName" required 
      pattern="^[a-zA-Z0-9]+$" 
      title="English letters and numbers">

      <label for="psw"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="password" required
          pattern="^[a-zA-Z0-9]{8,}" title="minimum 8 english letters and (or) numbers"
    required autocomplete="off">
        
      <button type="submit">Login</button>
    </div>
  </form>
</div>
</body>
</html>