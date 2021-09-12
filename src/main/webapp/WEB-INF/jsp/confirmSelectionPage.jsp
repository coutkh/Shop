<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title>ADD USER</title>
    <link rel="stylesheet" href="my.css" type="text/css">
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
            <p>${product.getName()}</p>
            <p><button type="button" onclick="this.nextElementSibling.stepDown()">-</button>
                <input type="number" min="0" max=${product.getCount()} value="1" readonly class="mnp">
                <button type="button" onclick="this.previousElementSibling.stepUp()">+</button></p>
            <p>${product.getPrice()}</p>
            <p>${product.getColor()}</p>
        </div>
    </div>
        <button class="btn btn-md btn-primary btn-block" type="submit" name="command" value="edit_user">
    <%--        <fmt:message key="adminEditUser.edit"/>--%>
            Подтвердить и продолжить покупки
        </button>
        <button class="btn btn-md btn-primary btn-block" type="submit" name="command" value="to_admin_users">
    <%--        <fmt:message key="adminEditUser.cancel"/>--%>
            Подтвердить и перейти в корзину
        </button>
    </form>
</div>

<form action="controller" method="post">
    <input type="hidden" name="login" id="login" value="<c:out value="${user.login}"/>">
    <input type="email" name="email" id="inputEmail" value="<c:out value="${user.email}"/>" required autofocus pattern="^[\w\.]{3,13}@\w{3,10}\.\w{2,5}$">
    <input type="password" name="password" id="inputPassword" value="<c:out value="${user.password}"/>" required pattern="^[\w]{3,12}$">
    <input type="text" name="role" id="inputRole" value="<c:out value="${user.role.role}"/>" required pattern="^[\w]{3,12}$">

    <button class="btn btn-md btn-primary <%--btn-block--%>" type="submit" name="command" value="edit_user">
        <fmt:message key="adminEditUser.edit"/>
    </button>
    <button class="btn btn-md btn-danger <%--btn-block--%>" type="submit" name="command" value="to_admin_users">
        <fmt:message key="adminEditUser.cancel"/>
    </button>
    <c:if test="${errorSignInMessageKey != null}">
        <label style="color: red"><fmt:message key="${errorSignInMessageKey}"/></label>
    </c:if>
</form>
</body>
</html>