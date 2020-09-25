<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
<hr width="30%" align="left" color="green">
使用shiro标签后<br>
登录后才会出现：<shiro:user>
    欢迎[<shiro:principal/>]登录，<a href="${pageContext.request.contextPath}/logout">注销</a><br/>
</shiro:user>
授权后才会出现：<shiro:authenticated>
<a href="${pageContext.request.contextPath}/authenticated">已身份认证</a><br/>
</shiro:authenticated>
有admin角色才会出现：<shiro:hasRole name="admin">
<a href="${pageContext.request.contextPath}/role">角色授权 roles[admin]</a><br/>
</shiro:hasRole>
有user:create权限才会出现：<shiro:hasPermission name="user:create">
<a href="${pageContext.request.contextPath}/permission">权限授权 perms["user:create"]</a><br/>
</shiro:hasPermission>

</body>
</html>