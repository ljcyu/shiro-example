<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login success</title>
    <meta http-equiv="refresh" content="5;url=${pageContext.request.contextPath}/">
</head>
<body>
欢迎${subject.principal}登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a>
</body>
</html>