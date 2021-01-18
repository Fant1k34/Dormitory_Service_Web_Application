<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.12.2020
  Time: 2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Управление</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <style>
        .navbar-nav {
            position: relative !important;
            min-height: 0px !important;
            margin-bottom: 0px !important;
            border: 1px solid transparent !important;
        }
        .navbar{
            height: 50px !important;
        }
        .accordion-item {
            width: 80pc;
        }
        #v-pills-tab {
            width: 20pc;
        }
        .d-flex align-items-start {
            width: 100pc !important;
        }
        .first{
            width:70%;
            height:300px;
            position:absolute;
            border:1px solid red;
        }
        .second{
            border:2px solid blue;
            width:40%;
            height:200px;
            position: relative;
            top: 315px;
        }
    </style>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-0 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">Стена</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/market">Маркет</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/service">Управленчество</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Настройки
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">${login}</a></li>
                        <li><a class="dropdown-item" href="/changepassword">Сменить пароль</a></li>
                        <li><a class="dropdown-item" href="/buy">МЕГА-подписка</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="/exit">Выход</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="d-flex align-items-start">
    <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">

        <input type="button" class='nav-link btn btn-outline-primary' id="v-pills-home-tab" value="Получение МЕГА" role="tab" aria-controls="v-pills-home" aria-selected="false">

        <c:if test="${exception != null}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong>Ошибка!</strong> ${exception}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

    </div>

    <div class="alert alert-light" role="alert">
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <form:form method="post" action="/buy">
            <input type="submit" src="/buy" class="btn btn-primary" value="Получить на день МЕГА-подписку">
        </form:form>

    </div>



</div>
</body>
</html>


