<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.12.2020
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Управление</title>
</head>
<body>
<form th:action="@{/}" method="post">
    ${message}
    <label>
        <input type="text" name="login" text="Пожалуйста, авторизируйтесь" th:field="*{login}">
    </label>
    <label>
        <input type="password" name="passw" th:field="*{passw}">
    </label>
    <input type="submit" value="Join">
</form>
</body>
</html>

