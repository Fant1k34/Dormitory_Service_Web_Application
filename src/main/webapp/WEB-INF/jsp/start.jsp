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
        <c:if test="${toShow == 'News'}">
            <input type="button" class='nav-link btn btn-outline-primary' id="v-pills-home-tab" value="Новости" onClick='location.href="/showсommon"' role="tab" aria-controls="v-pills-home" aria-selected="false">
        </c:if>
        <c:if test="${toShow != 'News'}">
            <input type="button" class='nav-link btn btn-outline-secondary' id="v-pills-home-tab" value="Новости" onClick='location.href="/showсommon"' role="tab" aria-controls="v-pills-home" aria-selected="false">
        </c:if>

        <c:if test="${toShow == 'Adding'}">
            <input type="button" class='nav-link btn btn-outline-primary' id="v-pills-profile-tab" value="Добавить новость" onClick='location.href="/addcommon"' role="tab" aria-controls="v-pills-home" aria-selected="false">
        </c:if>
        <c:if test="${toShow != 'Adding'}">
            <input type="button" class='nav-link btn btn-outline-secondary' id="v-pills-profile-tab" value="Добавить новость" onClick='location.href="/addcommon"' role="tab" aria-controls="v-pills-home" aria-selected="false">
        </c:if>

        <c:if test="${toShow == 'Editing'}">
            <input type="button" class='nav-link btn btn-outline-primary' id="v-pills-messages-tab" value="Удалить/изменить новость" onClick='location.href="/editcommon"' role="tab" aria-controls="v-pills-home" aria-selected="false">
        </c:if>
        <c:if test="${toShow != 'Editing'}">
            <input type="button" class='nav-link btn btn-outline-secondary' id="v-pills-messages-tab" value="Удалить/изменить новость" onClick='location.href="/editcommon"' role="tab" aria-controls="v-pills-home" aria-selected="false">
        </c:if>

        <c:if test="${exception != null}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong>Ошибка!</strong> ${exception}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        </div>
    <c:if test="${toShow == 'News'}">
    <div class="tab-content" id="v-pills-tabContent">
        <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">
                <!-- Тут отображение общих новостей -->
                <div class="accordion" id="accordionExample">
                    <c:forEach var="el" items="${commonNews}">
                        <div class="accordion-item">
                                        <h2 class="accordion-header" id="${el.fid}" >
                                           <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${el.id}" aria-expanded="false" aria-controls="collapse${el.id}">
                                               <span class="toTheLeft"> ${el.header} </span>
                                            <c:forEach var="tag" items="${el.tags}">
                            &#160; <span class="badge rounded-pill ${tag.tagColor} ${tag.tagTextColor}"> ${tag.tagText} </span>
                                            </c:forEach>
                                            </button>
                                        </h2>
                                        <div id="collapse${el.id}" class="accordion-collapse collapse" aria-labelledby="heading${el.id}" data-bs-parent="#accordionExample">
                                            <div class="accordion-body">
                                                    <strong> ${el.strongText} </strong> <br> ${el.usualText}
                                                </div>
                                        </div>
                                    </div>
                    </c:forEach>
                </div>
                <!-- Тут отображение общих новостей -->
        </div>
        <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
        </div>
        <div class="tab-pane fade" id="v-pills-messages" role="tabpanel" aria-labelledby="v-pills-messages-tab">...</div>
        <div class="tab-pane fade" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab">...</div>
    </div>
    </c:if>
    <c:if test="${toShow == 'Adding'}">

        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <form:form method="post" modelAttribute="addingAtt" action="/addcommon">

            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">Заголовок объявления</span>
                <form:input path="header" type="text" class="form-control" placeholder="А я заголовок. Не забудь про меня" aria-label="Username" aria-describedby="basic-addon1"/> <!-- bind to user.name-->
                <form:errors path="header"/>
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text">Текст объявления</span>
                <form:textarea path="usualText" class="form-control" placeholder="Это текст объявления, я очень важен! Необходимо меня прочитать всем!" aria-label="With textarea"/> <!-- bind to user.name-->
                <form:errors path="usualText"/>
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon2">Выбор даты создания объявления</span>
                <form:input path="strongText" type="date" class="form-control"  aria-label="Recipient's username" aria-describedby="basic-addon2"/> <!-- bind to user.name-->
                <form:errors path="strongText"/>

            </div>


                <form:select path="tagName" class="form-select form-select-sm" aria-label=".form-select-sm example">
                    <form:option value="Важно" itemLabel="name" itemValue="customerID"/>
                    <form:option value="Стандартное" itemLabel="name" itemValue="customerID"/>
                </form:select>
                <form:errors path="tagName"/>

            <c:if test="${exception != null}">
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong>Ошибка!</strong> - ${exception}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>



<%--            <div class="input-group date" data-provide="datepicker">--%>
<%--                <input type="text" class="form-control">--%>
<%--                <form:textarea path="usualText" class="form-control" placeholder="Это текст объявления, я очень важен! Необходимо меня прочитать всем!" aria-label="With textarea"/> <!-- bind to user.name-->--%>
<%--                <form:errors path="usualText"/>--%>
<%--                <div class="input-group-addon">--%>
<%--                    <span class="glyphicon glyphicon-th"></span>--%>
<%--                </div>--%>
<%--            </div>--%>

            <input type="submit" src="/addcommon" class="btn btn-primary" value="Добавить объявление">



        </form:form>
    </c:if>
<c:if test="${toShow == 'Editing'}">
    <div class="alert alert-light" role="alert">
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <form:form method="post" modelAttribute="editingAtt" action="/editcommon">


        <form:select path="header2" class="form-select form-select-sm" aria-label=".form-select-sm example">
            <c:forEach var="el" items="${allHeaders}">
                <form:option value="${el}" itemLabel="name" itemValue="customerID"/>
            </c:forEach>
        </form:select>

        <input type="submit" src="/editcommon" class="btn btn-primary" value="Выбрать">
        </form:form>

    </div>
    <div class="alert alert-light" role="alert">
    <form:form method="post" modelAttribute="editingAtt" action="/changecommon">
        <c:if test="${chosen != null}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                ${chosen.header}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
    <div class="input-group mb-3">
        <span class="input-group-text" id="basic-addon1">Заголовок объявления</span>
        <form:input path="header3" type="text" class="form-control" placeholder="А я заголовок. Не забудь про меня" aria-label="Username" aria-describedby="basic-addon1"/> <!-- bind to user.name-->
        <form:errors path="header3"/>
    </div>

        <c:if test="${chosen != null}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    ${chosen.usualText}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
    <div class="input-group mb-3">
        <span class="input-group-text">Текст объявления</span>
        <form:textarea path="usualText2" class="form-control" placeholder="Это текст объявления, я очень важен! Необходимо меня прочитать всем!" aria-label="With textarea"/> <!-- bind to user.name-->
        <form:errors path="usualText2"/>
    </div>

        <c:if test="${chosen != null}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    ${chosen.strongText}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
    <div class="input-group mb-3">
        <span class="input-group-text" id="basic-addon2">Выбор даты создания объявления</span>
        <form:input path="strongText2" type="date" class="form-control"  aria-label="Recipient's username" aria-describedby="basic-addon2"/> <!-- bind to user.name-->
        <form:errors path="strongText2"/>

    </div>

        <c:if test="${chosen != null}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    ${chosen.tagName}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
    <form:select path="tagName2" class="form-select form-select-sm" aria-label=".form-select-sm example">
        <form:option value="Важно" itemLabel="name" itemValue="customerID"/>
        <form:option value="Стандартное" itemLabel="name" itemValue="customerID"/>
    </form:select>
    <form:errors path="tagName2"/>

    <c:if test="${exception != null}">
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
            <strong>Ошибка!</strong> - ${exception}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

        <input type="submit" src="/changecommon" class="btn btn-primary" value="Изменить">
        </form:form>
        <input type="submit" src="/changecommon" class="btn btn-primary" onClick='location.href="/deletecommon/${chosen.header}"' value="Удалить">
    </div>
</c:if>
</div>
</body>
</html>
