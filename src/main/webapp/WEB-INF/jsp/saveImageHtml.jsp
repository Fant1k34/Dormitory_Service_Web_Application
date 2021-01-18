<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 03.01.2021
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Управление</title>
</head>
<body>
<form method="post" enctype="multipart/form-data" action="/uploadImage">
    <input type="file" name="imageFile">
    <input type="submit" value="Upload"/>
</form>
</body>
</html>
