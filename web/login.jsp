<%--
  Created by IntelliJ IDEA.
  User: dream
  Date: 09.02.2021
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<% if (request.getAttribute("message") != null) { %>
<p style="color: red"><%= request.getAttribute("message") %></p>
<% } %>
<form action="/login" method="post">
    <input type="text" name="email" placeholder="Email"><br>
    <input type="password" name="password" placeholder="Password"><br>
    <input type="submit" value="Login"> <br>
    <a href="index.jsp">Back</a>
</form>
</body>
</html>
