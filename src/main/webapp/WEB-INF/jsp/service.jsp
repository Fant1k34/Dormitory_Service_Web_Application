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
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong>Информация!</strong>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>


    </div>
    <div>
        <div class="alert alert-success" role="alert">
            <h4 class="alert-heading">Ссылки!</h4>
            <p>Размещены ссылки для вызова плотника, сантехника</p>
            <hr>
            <p class="mb-0"> <a href="https://docs.google.com/forms/d/e/1FAIpQLSdI-iUJz8YNLX_Zyy3ZOIgrbX0slnlNXD6Niq7lywmgynWM9w/viewform">Вызвать</a> </p>
        </div>

        <div class="alert alert-warning" role="alert">
            <h4 class="alert-heading">Администрация!</h4>
            <p>
                🔥Заведующий - Полозов Роман Олегович <br>
                🔥Комендант - Похлестова Ирина Владимировна <br>
                🔥Паспортистка - Любарец Ольга Михайловна <br>
                🔥Кастелянша - Салина Ольга Леонидовна
            </p>
            <hr>
            <p class="mb-0">
                Время работы паспортистки: <br>
                Пн - 16:00 - 18:00 <br>
                Вт - 15:00 - 17:00 <br>
                Ср - 15:00 - 17:00 <br>
                Чт - 16:00-18:00 <br>

                ВРЕМЯ РАБОТЫ АДМИНИСТРАЦИИ <br>
                Пн - 9:00-18:00 <br>
                Вт - 9:00-18:00 <br>
                Ср - 9:00-18:00 <br>
                Чт - 9:00-18:00 <br>
                Пт - 9:00-17:00 <br>
                Перерыв на обед 13:00-14:00
            </p>
        </div>

        <div class="alert alert-success" role="alert">
            <h4 class="alert-heading">Контакты!</h4>
            <p>Телефон администрации: +7(812)524-04-43 <br>
                Почта общежития: belorusskaya_6@mail.ru</p>
        </div>
    </div>

</div>
</body>
</html>

