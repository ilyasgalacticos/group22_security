<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <form action="/register" method="post">
            EMAIL : <input type="email" name="user_email">
            PASSWORD : <input type="password" name="user_password">
            RE-PASSWORD : <input type="password" name="re_user_password">
            FULL NAME : <input type="text" name="user_full_name">
            <button>SIGN UP</button>
        </form>
    </body>
</html>
