<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 07.01.2021
  Time: 3:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="post" modelAttribute="search" action="/test">
    <form:input path="subsequence" type="text"/> <!-- bind to search.subsequence-->
    <form:errors path="subsequence"/>
</form:form>
</body>
</html>
