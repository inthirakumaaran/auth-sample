<%--
  Created by IntelliJ IDEA.
  User: kumar
  Date: 6/21/18
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Auth Training</title>
    <link href="/bootstrap.min.css" rel="stylesheet">
    <script src="/jquery-2.2.1.min.js"></script>
    <script src="/bootstrap.min.js"></script>
</head>
<body>
    <div class="col-md-2">
    <form action="/logout" method="post">
        <button type="submit" class="btn btn-danger">Log Out</button>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
    </div>
    <div class="container col-md-4 text-center" style="margin: 50px;border: 1px solid green;">

        <h1>Welcome</h1>
    </div>
</body>
</html>

