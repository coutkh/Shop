<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title>ADMIN</title>
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
    <button class="btn btn-lg btn-primary btn-block" type="submit" name="command" value="to_add_user_page">
        <fmt:message key="adminCategoryPage.insertCategory"/>
    </button>
</form>
<div class="container">
    <table class="table table-condensed table-hover ">
        <thead>
            <tr>
                <th><fmt:message key="adminCategoryPage.idCategory"/></th>
                <th><fmt:message key="adminCategoryPage.nameCategory"/></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${categoryList}" var="categoryList">
            <tr>
                <td><c:out value="${categoryList.getId()}"/></td>
                <td><c:out value="${categoryList.getName()}"/> </td>
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="id" value="${categoryList.getId()}">
                        <input type="hidden" name="name" value="${categoryList.getName()}">
                        <button class="btn btn-default " type="submit" name="command" value="delete_category">
                            <fmt:message key="usersTable.delete"/>
                        </button>
                        <button class="btn btn-default" type="submit" name="command" value="to_edit_category_page">
                            <fmt:message key="usersTable.edit"/>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>