<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成功</title>
    <style>.error{color:red;}</style>
    <meta http-equiv="refresh" content="5;url=${pageContext.request.contextPath}/login">
</head>
<body>

<div class="error">${error}</div>
5s后自动跳转到登录页面
</body>
</html>