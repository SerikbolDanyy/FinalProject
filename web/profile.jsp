<%@ page import="Connector.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<div class="container">
    <jsp:include page="navBar.jsp"></jsp:include>
    <br>
    <%
        User user = (User) request.getSession().getAttribute("CurrentUser");
    %>
    Hello, <%=user.getFullName()%>
    <br><br>
    <form action="${pageContext.request.contextPath}/logout" method="get">
        <button class="btn btn-primary" aria-current="page" href="#">Log out</button>
    </form>
</div>
</body>
</html>
