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
            width: 50%; /* Ширина */
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


        *{transition: all 0.3s linear;}

        .myclass4 {
            text-align: center;
        }

        .hidden{
            font-size: 0;
        }

        .like{
            font-family: 'Open Sans', serif;
        }

        .like-toggle{
            outline:none;
            box-shadow:none;
            border: none;
            width: 40px;
            height: 30px;
            font-size: 1.3em;
            border-radius: 100px;
        }



        .like-toggle.basic3{
            border: none;
            width: 30px;
            height: 30px;
            font-size: 1.3em;
            border-radius: 100px;
            background: #438CCA;
            color: #fff;
            margin-bottom: 10px;
        }

        .like-active.basic3{
            background: #7CC576;
            transform: rotate(-360deg);
        }

        .like-toggle.basic3:not(.like-active):hover{
            background: #F26C4F;
            transform: rotate(90deg);
        }

        .myclass5 {
            background: #F26C4F !important;
        }


    </style>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous">
    function Liked(){
        location.replace("/market/liked?newid=1")
    }
</script>
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
        <c:if test="${sortType == 'rating'}">
            <input type="button" class='nav-link btn btn-outline-primary' id="v-pills-home-tab" value="Rating" onClick='location.href="/sortByRating"' role="tab" aria-controls="v-pills-home" aria-selected="false">
        </c:if>
        <c:if test="${sortType != 'rating'}">
            <input type="button" class='nav-link btn btn-outline-secondary' id="v-pills-home-tab" value="Rating" onClick='location.href="/sortByRating"' role="tab" aria-controls="v-pills-home" aria-selected="false">
        </c:if>

        <c:if test="${sortType == 'date'}">
            <input type="button" class='nav-link btn btn-outline-primary' id="v-pills-profile-tab" value="Date" onClick='location.href="/sortByDate"' role="tab" aria-controls="v-pills-home" aria-selected="false">
        </c:if>
        <c:if test="${sortType != 'date'}">
            <input type="button" class='nav-link btn btn-outline-secondary' id="v-pills-profile-tab" value="Date" onClick='location.href="/sortByDate"' role="tab" aria-controls="v-pills-home" aria-selected="false">
        </c:if>

        <c:if test="${sortType == 'name'}">
            <input type="button" class='nav-link btn btn-outline-primary' id="v-pills-messages-tab" value="Name" onClick='location.href="/sortByName"' role="tab" aria-controls="v-pills-home" aria-selected="false">
        </c:if>
        <c:if test="${sortType != 'name'}">
            <input type="button" class='nav-link btn btn-outline-secondary' id="v-pills-messages-tab" value="Name" onClick='location.href="/sortByName"' role="tab" aria-controls="v-pills-home" aria-selected="false">
        </c:if>

        <c:if test="${isLikeButtonActive}">
            <input type="button" class='nav-link btn btn-outline-primary active' id="v-pills-settings-tab" value="Liked" onClick='location.href="/sortByLiked"' role="tab" aria-controls="v-pills-home" aria-selected="false">
        </c:if>
        <c:if test="${isLikeButtonActive == false}">
            <input type="button" class='nav-link btn btn-outline-secondary' id="v-pills-settings-tab" value="Liked" onClick='location.href="/sortByLiked"' role="tab" aria-controls="v-pills-home" aria-selected="false">
        </c:if>

        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

        <form:form method="post" modelAttribute="search" action="/market" class="d-flex">
            <form:input class="form-control me-2" placeholder="Search" aria-label="Search" path="subsequence" type="search"/> <!-- bind to search.subsequence-->
            <form:errors path="subsequence"/>
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form:form>

        <c:if test="${prevSearch != ''}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong>Успешно!</strong> Новости отсортированы по: ${prevSearch}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

    </div>
    <div class="tab-content" id="v-pills-tabContent">
        <!-- Тут отображение общих новостей -->
        <div class="row row-cols-1 row-cols-md-2 g-${marketNews.size()}">
        <c:forEach var="el" items="${marketNews}" varStatus="сounter">
                <div class="card">
                    <div>
                    <img src="${сounter.count}.jpg" class="card-img-top" alt="${el.title}" align="middle">
                        <div class="card" style="width: 16.225pc;">
                            <div class="card-header">
                                <a class="myclass3" href="/market/items/{${el.mark_id}}"> Посмотреть подробнее </a>
                            </div>
                            <ul class="list-group list-group-flush myclass4">
                                <li class="list-group-item">${el.date_mark}</li>
                                <li class="list-group-item">${el.author}</li>
                                <c:if test="${el.liked == false}">
                                    <li class="list-group-item myclass1">Рейтинг: ${el.rating}</li>
                                </c:if>
                                <c:if test="${el.liked == true}">
                                    <li class="list-group-item myclass2">Рейтинг: ${el.rating}</li>
                                </c:if>
                                <li class="list-group-item like">
                                    <c:if test="${el.liked == false}">
                                    <input type="button" class='like-toggle basic3' value="♥" onClick='location.href="/market/liked?newid=${el.mark_id}"'>
                                    Добавить в избранное
                                    </c:if>
                                    <c:if test="${el.liked == true}">
                                        <input type="button" class='like-toggle basic3 myclass5' value="♥" onClick='location.href="/market/liked?newid=${el.mark_id}"'>
                                        Удалить из избранного
                                    </c:if>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">${el.title}</h5>
                        <p class="card-text">${el.text_mark}</p>
                    </div>
                </div>
        </c:forEach>
        </div>
        </div>
        <!-- Тут отображение общих новостей -->
    </div>
</div>
</body>
</html>
