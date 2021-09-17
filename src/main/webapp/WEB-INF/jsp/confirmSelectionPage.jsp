<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title>ADD USER</title>
    <link rel="stylesheet" href="css/my.css" type="text/css">
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
<%--    <!-- Latest compiled and minified CSS -->--%>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">--%>
<%--    <!-- Optional theme -->--%>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">--%>

</head>
<body >
<jsp:include page="menu/header.jsp"/>
<%--<jsp:include page="menu/headerAdmin.jsp"/>--%>
<br/>
<%--<h3><fmt:message key="adminEditUser.editingUser"/><c:out value=" ${user.login}"/> </h3>--%>
<h3 class="text-center"><fmt:message key="confirmPage.confirmText"/></h3>
<br/>
<div class="container-fluid">
    <form action="controller" method="post">
        <div class="row">
            <div class="col-lg-4"></div>
            <div class="col-lg-2">
                <p>Наименование товара</p>
                <p>Количество, доступно на складе:${product.getCount()}</p>
                <p>Цена</p>
                <p>Цвет</p>
            </div>
            <div class="col-lg-6">

                <input type="hidden" name="login" value="${userList.getLogin()}">

                <p>${product.getName()}</p>
                <p><button type="button" onclick="this.nextElementSibling.stepDown()">-</button>
                    <input type="number" name="count" min="0" max=${product.getCount()} value="1" readonly class="mnp">
                    <button type="button" onclick="this.previousElementSibling.stepUp()">+</button></p>
                <p>${product.getPrice()}</p>
                <p>${product.getColor()}</p>
                <input type="hidden" name="id" value="${product.getId()}">
                <input type="hidden" name="price" value="${product.getPrice()}">
            </div>
        </div>
            <button class="btn btn-md btn-primary btn-block" type="submit" name="command" value="confirm_and_back">
        <%--        <fmt:message key="adminEditUser.edit"/>--%>
                Подтвердить и продолжить покупки
            </button>
            <button class="btn btn-md btn-primary btn-block" type="submit" name="command" value="confirm_go_to_basket">
        <%--        <fmt:message key="adminEditUser.cancel"/>--%>
                Подтвердить и перейти в корзину
            </button>
    </form>
</div>

</body>
</html>