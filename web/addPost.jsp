<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add post</title>
</head>
<body>
<div class="container">
    <jsp:include page="navBar.jsp"></jsp:include>
    <br>
        <div class="row mt-3">
            <div class="col-6 mx-auto">
                <form action="${pageContext.request.contextPath}/addPost" method="post">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Title</label>
                        <input name="title" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Content</label>
                        <textarea name="content" class="form-control"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
</div>

</body>
</html>
