<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title>Products</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

</head>
<jsp:include page="menu/header.jsp"/>
<%--<jsp:include page="menu/headerProducts.jsp"/>--%>
<body>
<br/>
<br/>

<div class="container">
    <div class="row">
        <div class="col-lg-3 col-left">
            <h2>Каталог</h2>
                <c:forEach items="${userList}" var="userList" varStatus="loop">
                    <td><c:out value="${userList.getLogin()}"/></td>
                </c:forEach>
        </div>
        <div class="col-lg-9">
            <h2>Список товаров</h2>
            <p>[2021-08-27 11:54:47,677] Artifact Shop:war exploded: Artifact is deployed successfully
                [2021-08-27 11:54:47,678] Artifact Shop:war exploded: Deploy took 9,138 milliseconds
                [2021-08-27 11:56:13,711] Artifact Shop:war exploded: Artifact is being deployed, please wait...
                27-Aug-2021 11:56:18.010 INFO [RMI TCP Connection(17)-127.0.0.1] org.apache.jasper.servlet.TldScanner.
                [2021-08-27 11:56:22,154] Artifact Shop:war exploded: Artifact is deployed successfully
                [2021-08-27 11:56:22,154] Artifact Shop:war exploded: Deploy took 8,443 milliseconds</p>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>