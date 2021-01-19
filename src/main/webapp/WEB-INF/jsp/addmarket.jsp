<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 08.01.2021
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Управление</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <style>
        body{
            padding: 3pc 0.2pc 0.2pc 25pc;
        }
        .card{
            width: 50pc;
        }
        .card-header{
            background-color: #98ff98;
        }
    </style>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<div class="card">
    <div class="card-header mb-3">
        Добавление новости
    </div>
    <form:form method="post" modelAttribute="makeMarketNew" action="/addMarketNews">

        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Заголовок объявления</span>
            <form:input path="titleForMarketSimpleFromFormGetter" type="text" class="form-control" placeholder="А я заголовок. Не забудь про меня" aria-label="Username" aria-describedby="basic-addon1"/> <!-- bind to user.name-->
            <form:errors path="titleForMarketSimpleFromFormGetter"/>
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text">Текст объявления</span>
            <form:textarea path="textForMarketSimpleFromFormGetter" class="form-control" placeholder="Это текст объявления, я очень важен! По мне можно понять, что ты продаёшь!" aria-label="With textarea"/> <!-- bind to user.name-->
            <form:errors path="textForMarketSimpleFromFormGetter"/>
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon2">ID изображения</span>
            <form:input path="imageNameForMarketSimpleFromFormGetter" type="text" class="form-control" placeholder="1610138996" aria-label="Recipient's username" aria-describedby="basic-addon2"/> <!-- bind to user.name-->
            <form:errors path="imageNameForMarketSimpleFromFormGetter"/>

        </div>

        <div class="input-group mb-3">
            <span class="input-group-text">Персональная информация: ссылка на соц. сеть</span>
            <form:input path="contactInfoForMarketSimpleFromFormGetter" type="text" class="form-control" placeholder="https://vk.com/id442545" aria-label="Amount (to the nearest dollar)"/> <!-- bind to user.name-->
            <form:errors path="contactInfoForMarketSimpleFromFormGetter"/>
        </div>


        <input type="submit" class="btn btn-primary" value="Добавить объявление">



    </form:form>
</div>

<div class="card">
    <div class="card-header">
        Загрузка изображения
    </div>


    <form method="post" enctype="multipart/form-data" action="/uploadImage">
        <input type="file" class="btn btn-outline-secondary" name="imageFile">
        <input type="submit" class="btn btn-primary" value="Загрузить в систему"/>
    </form>

    <c:if test="${pictureId != ''}">
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
            ID загруженной картинки - <strong>${pictureId}</strong>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

    <br>

    <c:if test="${!(exception == null || exception == '')}">
        <div class="alert alert-warning alert-dismissible fade show" id="fullInfo" role="alert">
            <strong>${exception}</strong> <br>
        </div>
    </c:if>


</div>







</body>
</html>
