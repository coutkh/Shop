<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title>ADD USER</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

</head>
<body>
<jsp:include page="menu/header.jsp"/>
<jsp:include page="menu/headerAdmin.jsp"/>
<br/>
<form action="controller" method="post">
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="header.sign_in"/></h1><br/><br/>
    <input type="text" name = "login" id="inputLogin" placeholder="<fmt:message key="signup.InputLogin"/>" required autofocus pattern="^(?=.*[A-Za-z0-9]$)[A-Za-z][\w.-]{0,19}$">
    <input type="email" name="email" id="inputEmail" placeholder="<fmt:message key="signup.InputEmail"/>" required autofocus pattern="^[\w\.]{3,13}@\w{3,10}\.\w{2,5}$">
    <input type="password" name="password" id="inputPassword" placeholder="<fmt:message key="signup.InputPassword"/>" required pattern="^[\w]{3,12}$">

    <c:if test="${errorSignInMessageKey != null}">
        <label style="color: red"><fmt:message key="${errorSignInMessageKey}"/></label>
    </c:if>
    <button class="btn btn-lg btn-primary btn-block" type="submit" name="command" value="sign_in">
        <fmt:message key="header.sign_in"/>
    </button>

</form>
</body>
</html>