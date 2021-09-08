<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>main</title>
    <style>
        body {
            padding: 20px;
        }
    </style>
</head>
<jsp:include page="menu/header.jsp"/>
<body class="text-center">
<br/><br/>
<div class="container">
    <div class="panel panel-default">
        <h2><fmt:message key="main.welcome"/></h2>
        <h3><fmt:message key="main.inProgress"/></h3>
        <h3><fmt:message key="main.inProgressLimit"/></h3>
    </div>
    <img src="images/in_progress.jpg" class="img-fluid" alt="Responsive image" width="900" height="600">
</div>
</body>
</html>