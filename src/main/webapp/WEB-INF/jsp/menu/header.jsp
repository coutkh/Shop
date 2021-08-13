<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>

<!DOCTYPE html>

<html leng = "en">
<head>
    <%--<meta charset="utf-8">
    &lt;%&ndash;<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />&ndash;%&gt;
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>--%>
        <meta charset="utf-8">
        <title>Bootstrap уроки</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

</head>
<body>
<%--<nav class="navbar navbar-expand-lg navbar-light bg-light">--%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div  class="navbar-brand">
            <a href="controller?command=to_main"><fmt:message key="header.brand"/></a>
        </div>
        <div>
            <ul class = "nav navbar-nav">
                <li class="active"><a href="controller?command=to_main"><fmt:message key="header.main"/></a></li>
                <li class="active"><a href="controller?command=to_main"><fmt:message key="header.products"/></a></li>
                <li class="active"><a href="controller?command=to_main"><fmt:message key="header.sign_in"/></a></li>
                <li class="active"><a href="controller?command=to_main"><fmt:message key="header.sign_up"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="header.language"/><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><fmt:message key="header.language_en"/></a></li>
                        <li><a href="#"><fmt:message key="header.language_ru"/></a></li>
                        <li><a href="#" class="language" rel="it-IT"><img src="assets/img/it_IT.png" alt="Italiano" /></a></li>
                        <li><a href="#" class="language" rel="en-US"><img src="assets/img/en_US.png" alt="English" /></a></li>
                    </ul>
                </li>
            </ul>


            <%--<form action="controller" method="post">
                <div class="dropdown-menu" aria-labelledby="localeDropdown">
                    <button class="dropdown-item" type="submit" name="newLocale" value="en_US">
                        <fmt:message key="header.language_en"/></button>
                    <button class="dropdown-item" type="submit" name="newLocale" value="ru_RU">
                        <fmt:message key="header.language_ru"/></button>
                    <input type="hidden" name="command" value="change_locale">
                </div>
            </form>--%>

        </div>

    </div>
</nav>



<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>


<%--<div class="btn-group">
            <button class="btn btn-link dropdown-toggle" type="button" id="localeDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class='bx bx-world'></i>
            </button>
            <form action="controller" method="post">
                <div class="dropdown-menu" aria-labelledby="localeDropdown">
                    <button class="dropdown-item" type="submit" name="newLocale" value="en_US">
                        <fmt:message key="header.language_en"/></button>
                    <button class="dropdown-item" type="submit" name="newLocale" value="ru_RU">
                        <fmt:message key="header.language_ru"/></button>
                    <input type="hidden" name="command" value="change_locale">
                </div>
            </form>
        </div>--%>