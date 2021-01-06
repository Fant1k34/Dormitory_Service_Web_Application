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
        #v-pills-tabContent {
            width: 80pc !important;
        }
        #v-pills-tab {
            width: 20pc !important;
        }
        .d-flex align-items-start {
            width: 100pc !important;
        }
        .card-img-top {
            width: 55%; /* Ширина */
            float: left; /* Выстраиваем элементы по горизонтали */
            margin: 0 0 0 3.5%; /* Отступ слева */
            background: #f0f0f0; /* Цвет фона */
            border-radius: 5px; /* Радиус скругления */
            padding: 2%; /* Поля */
        }
        .myclass1:hover {
            color: rgb(0,0,255);
        }
        .myclass2:hover {
            color: rgb(255,0,0);
        }
        .myclass3:hover {
            color: rgb(0,255,0);
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
                    <a class="nav-link" aria-current="page" href="/">Стена</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/market">Маркет</a>
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
                        <li><a class="dropdown-item" href="#">Сменить пароль</a></li>
                        <li><a class="dropdown-item" href="#">МЕГА-подписка</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">Выход</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                </li>
            </ul>
            <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<div class="d-flex align-items-start">
    <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
        <a class="nav-link active" id="v-pills-home-tab" data-bs-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">Home</a>
        <a class="nav-link" id="v-pills-profile-tab" data-bs-toggle="pill" href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="false">Profile</a>
        <a class="nav-link" id="v-pills-messages-tab" data-bs-toggle="pill" href="#v-pills-messages" role="tab" aria-controls="v-pills-messages" aria-selected="false">Messages</a>
        <a class="nav-link" id="v-pills-settings-tab" data-bs-toggle="pill" href="#v-pills-settings" role="tab" aria-controls="v-pills-settings" aria-selected="false">Settings</a>
    </div>
    <div class="tab-content" id="v-pills-tabContent">
        <!-- Тут отображение общих новостей -->
                        <div>
                            <img src="1.jpg" class="card-img-top" alt="${el.title}" align="middle">
                            <div class="card" style="width: 16.225pc;">
                                <div class="card-header">
                                    <a class="myclass3" href="/market/items/{${el.mark_id}}"> Посмотреть подробнее </a>
                                </div>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">${el.date_mark}</li>
                                    <li class="list-group-item">${el.author}</li>
                                    <li class="list-group-item myclass1">Рейтинг: ${el.rating}</li>
                                </ul>
                            </div>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">${el.title}</h5>
                            <p class="card-text">${el.text_mark}</p>
                        </div>
        <!-- Тут отображение общих новостей -->
    </div>
</div>
</body>
</html>
