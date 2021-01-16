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
    <title>Title</title>
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
                    <a class="nav-link active" aria-current="page" href="#">Стена</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Маркет</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Управленчество</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Настройки
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="d-flex align-items-start">
    <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">

        <input type="button" class='nav-link btn btn-outline-primary' id="v-pills-home-tab" value="Смена пароля" role="tab" aria-controls="v-pills-home" aria-selected="false">

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

        <form:form method="post" modelAttribute="register" action="/register">



            <div class="row g-3 align-items-center">
                <div class="col-auto">
                    <label for="inputPassword" class="col-form-label">Придумайте логин</label>
                </div>
                <div class="col-auto">
                    <form:input path="login" id="inputPassword" type="text" class="form-control"/>
                    <form:errors path="login"/>
                </div>
            </div>


            <div class="row g-3 align-items-center">
                <div class="col-auto">
                    <label for="inputPassword2" class="col-form-label">Придумайте пароль</label>
                </div>
                <div class="col-auto">
                    <form:input path="password" id="inputPassword2" type="password" class="form-control"/>
                    <form:errors path="password"/>
                </div>
            </div>


            <div class="row g-3 align-items-center">
                <div class="col-auto">
                    <label for="inputPassword" class="col-form-label">Имя</label>
                </div>
                <div class="col-auto">
                    <form:input path="name" id="inputPassword" type="text" class="form-control"/>
                    <form:errors path="name"/>
                </div>
            </div>

            <div class="row g-3 align-items-center">
                <div class="col-auto">
                    <label for="inputPassword" class="col-form-label">Фамилия</label>
                </div>
                <div class="col-auto">
                    <form:input path="surname" id="inputPassword" type="text" class="form-control"/>
                    <form:errors path="surname"/>
                </div>
            </div>

            <div class="row g-3 align-items-center">
                <div class="col-auto">
                    <label for="inputPassword" class="col-form-label">Номер ису</label>
                </div>
                <div class="col-auto">
                    <form:input path="isu_number" id="inputPassword" type="text" class="form-control"/>
                    <form:errors path="isu_number"/>
                </div>
            </div>

            <div class="row g-3 align-items-center">
                <div class="col-auto">
                    <label for="inputPassword" class="col-form-label">Номер комнаты</label>
                </div>
                <div class="col-auto">
                    <form:input path="block_id" id="inputPassword" type="text" class="form-control"/>
                    <form:errors path="block_id"/>
                </div>
            </div>

            <div class="row g-3 align-items-center">
                <div class="col-auto">
                    <label for="inputPassword" class="col-form-label">Введите код</label>
                </div>
                <div class="col-auto">
                    <form:input path="code" id="inputPassword" type="text" class="form-control"/>
                    <form:errors path="code"/>
                </div>
            </div>

            <input type="submit" src="/register" class="btn btn-primary" value="Зарегистрироваться">
        </form:form>

    </div>



</div>
</body>
</html>


