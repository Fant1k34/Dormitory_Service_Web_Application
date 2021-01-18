<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.01.2021
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Управление</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

  </head>
  <body>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

  <div class="alert alert-warning alert-dismissible fade show" role="alert">
      <strong>ОШИБКА: </strong> ${error}
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    <a href="/">Перезайдите в сервис</a>
  </div>


  </body>
</html>
