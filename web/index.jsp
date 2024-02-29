<%@ page import="Connector.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="Database.UserDB" %><%--
  Created by IntelliJ IDEA.
  User: serik
  Date: 29.02.2024
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
    <title>Main page</title>
</head>
<body>
    <div class="container">
        <jsp:include page="navBar.jsp"></jsp:include>
        <%
            List<Blog> blogs = UserDB.getBlog();
            for (Blog blog: blogs){
        %>
        <br>
        <div class="card w-75">
            <div class="card-body">
                <h5 class="card-title"><%=blog.getTitle()%></h5>
                <p class="card-text"><%=blog.getContent()%></p>
                <p><%=blog.getUser().getFullName()%> <%=blog.getPostDate()%></p>
                <a href="/details?id=<%=blog.getId()%>" class="btn btn-primary">Details</a>
            </div>
        </div>
        <%}%>
    </div>
</body>
</html>
