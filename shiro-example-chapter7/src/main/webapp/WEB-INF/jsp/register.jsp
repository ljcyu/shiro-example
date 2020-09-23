<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <style>.error{color:red;}</style>
</head>
<body>

<div class="error">${error}</div>
<form action="${pageContext.request.contextPath}/register" method="post">
    用户名：<input type="text" name="username"><br/>
    密码：<input type="password" name="pass"><br/>
    <input type="submit" value="注册">
</form>

</body>
</html>