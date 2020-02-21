<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>WELCOME ${user.fullName}</h1>

    <a href="/moderator/index">MODERATOR PAGE</a>
    <a href="/admin/index">ADMIN PAGE</a>

    <form action="/signout" method="post">
        <button>SIGN OUT</button>
    </form>
</body>
</html>
