<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title>SIGN UP</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <style>
        body {
            padding: 20px;
        }
    </style>
</head>
<jsp:include page="menu/header.jsp"/>
<body class="text-center">
<br/><br/>
<br/>
<div class="panel panel-default">
    <h3><fmt:message key="signup.register"/></h3>
</div>
<br/>
<div class="container justify-content-center" style="width: 380px; margin-left: auto; margin-right: auto">
    <form action="controller" method="post">

        <input type="text" name = "login" id="inputLogin" class="form-control mb-2" placeholder="<fmt:message key="signup.InputLogin"/>" required autofocus pattern="^(?=.*[A-Za-z0-9]$)[A-Za-z][\w.-]{0,19}$">
            <br/>
        <input type="email" name="email" id="inputEmail" class="form-control mb-2" placeholder="<fmt:message key="signup.InputEmail"/>" required autofocus pattern="^[\w\.]{3,13}@\w{3,10}\.\w{2,5}$">
            <br/>
        <input type="password" name="password" id="inputPassword" class="form-control mb-2" placeholder="<fmt:message key="signup.InputPassword"/>" required pattern="^[\w]{3,12}$">
            <br/>
        <input type="password" name="re_password" id="re-inputPassword" class="form-control mb-2" placeholder="<fmt:message key="signup.ReInputPassword"/>" required pattern="^[\w]{3,12}$">
        <br/><br/>
    <%--    <input type="text" name="role" id="inputRole" placeholder="<fmt:message key="signup.InputRole"/>" required pattern="^[\w]{3,12}$">--%>
        <button class="btn btn-md btn-primary btn-block" type="submit" name="command" value="add_and_login_user">
            <fmt:message key="signup.register"/>
        </button>
            <br/>
<%--        <button class="btn btn-md btn-danger btn-block" type="submit" name="command" value="to_main">--%>
<%--            <fmt:message key="signup.cancel"/>--%>
<%--        </button>--%>
        <c:if test="${errorSignInMessageKey != null}">
            <label style="color: red"><fmt:message key="${errorSignInMessageKey}"/></label>
        </c:if>
    </form>
</div>
</body>
</html>