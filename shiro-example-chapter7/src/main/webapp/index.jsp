<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
登录用户：${subject.principal}<br/>
<a href="${pageContext.request.contextPath}/register">注册</a><br/>
<a href="${pageContext.request.contextPath}/login">登录</a><br/>
<a href="${pageContext.request.contextPath}/logout">注销</a><br/>
<a href="${pageContext.request.contextPath}/authenticated">已身份认证</a><br/>
<a href="${pageContext.request.contextPath}/role">角色授权 roles[admin]</a><br/>
<a href="${pageContext.request.contextPath}/permission">权限授权 perms["user:create"]</a><br/>

</body>
</html>