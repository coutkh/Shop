<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="main.administration"/></title>Administration
</head>
<body>
<jsp:include page="menu/header.jsp"/>
<jsp:include page="menu/headerAdmin.jsp"/>
<div class="container">
    <div class="row">
        <div class="col">
            <img src="images/in_progress.jpg" class="img-fluid" alt="Responsive image" width="900" height="600">
        </div>
        <div class="col-6">

        </div>
    </div>
</div>
</body>
</html>