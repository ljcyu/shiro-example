<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>首页</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap-4.5.0.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.slim.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle-4.5.0.js"></script>
</head>
<body>
<div class="container">
登录用户：${subject.principal}<br/>
<a href="${pageContext.request.contextPath}/showRegister">注册</a><br/>
<a href="${pageContext.request.contextPath}/login">登录</a><br/>
<a href="${pageContext.request.contextPath}/logout">注销</a><br/>
<a href="${pageContext.request.contextPath}/authenticated">已身份认证</a><br/>
<a href="${pageContext.request.contextPath}/role">角色授权 roles[admin]</a><br/>
<a href="${pageContext.request.contextPath}/permission">权限授权 perms["user:create"]</a><br/>
<hr width="30%" align="left" color="green" class="divider">
<p class="h3">使用shiro标签后</p>
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
<hr width="30%" align="left" color="green" class="divider">
    <p class="h3">action中使用shiro注释，liu有以下权限</p>
    <div class="list-group">
    <a class="list-group-item" href="${pageContext.request.contextPath}/annotation/requires_guest">@RequiresGuest</a><br/>
    <a class="list-group-item" href="${pageContext.request.contextPath}/annotation/requires_authentication">@RequiresAuthentication</a><br/>
    <a class="list-group-item" href="${pageContext.request.contextPath}/annotation/requires_user">@RequiresUser</a><br/>
    <a class="list-group-item" href="${pageContext.request.contextPath}/annotation/requires_roles">@RequiresRoles("test")</a><br/>
    <a class="list-group-item" href="${pageContext.request.contextPath}/annotation/requires_perms">@RequiresPermissions("test:list")</a><br/>
    </div>
</div>
</body>
</html>