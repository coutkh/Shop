<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title>ADMIN</title>
    <link rel="stylesheet"  type="text/css" href="css/my.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>

</head>
<body>
<jsp:include page="menu/header.jsp"/>
<br/>

<div class="container">
    <table class="table table-condensed table-hover ">
        <%--        <thead>--%>
        <%--        <tr>--%>
        <%--            <th><fmt:message key="usersTable.login"/></th>--%>
        <%--            <th><fmt:message key="usersTable.email"/></th>--%>
        <%--            <th><fmt:message key="usersTable.password"/></th>--%>
        <%--            <th><fmt:message key="usersTable.createTime"/></th>--%>
        <%--            <th><fmt:message key="usersTable.role"/></th>--%>
        <%--        </tr>--%>
        <%--        </thead>--%>

        <tbody>

        <c:forEach items="${receiptList}" var="receiptList" varStatus="loop">
<%--            <c:if test="${receiptList.getUser().getId() == currentUser.getId()}">--%>
                <tr>
                <td><c:out value="Заказ от: ${receiptList.getCreateDate()}"/></td>
                <td><c:out value="${receiptList.getUser().getLogin()}"/></td>
                <td><c:out value="${receiptList.getTotal()}"/> </td>
                <td>
                    <c:choose>
                        <c:when test="${receiptList.getStatus().getId() == 1}">
                            Открыт
                        </c:when>
                        <c:when test="${receiptList.getStatus().getId() == 2}">
                            в процессе обработки
                        </c:when>
                        <c:when test="${receiptList.getStatus().getId() == 3}">
                            Закрыт
                        </c:when>
                        <c:when test="${receiptList.getStatus().getId() == 4}">
                            Оплачен
                        </c:when>
                    </c:choose>
                        <%--                        <c:out value="${receiptList.getStatus().getStatus_name()}"/>--%>
                </td>
                <td>
                    <div class="btn-group">
<%--                        <button type="button" class="btn btn-default">change status</button>--%>
<%--                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"--%>
<%--                                aria-expanded="false">--%>
<%--                            <span class="caret"></span>--%>
<%--                            <span class="sr-only">Toggle Dropdown</span>--%>
<%--                        </button>--%>
                        <button type="button" data-toggle="dropdown" class="btn btn-info dropdown-toggle">
                            change status
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a href="controller?command=change_status&receiptId=${receiptList.getId()}&statusId=1">Открыт</a></li>
                            <li><a href="controller?command=change_status&receiptId=${receiptList.getId()}&statusId=2">в процессе</a></li>
                            <li><a href="controller?command=change_status&receiptId=${receiptList.getId()}&statusId=3">Закрыт</a></li>
                            <li><a href="controller?command=change_status&receiptId=${receiptList.getId()}&statusId=4">Оплачен</a></li>
                        </ul>
                    </div>
                </td>
                    <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="id" value="${receiptList.getId()}">
                        <input type="hidden" name="page" value="orders">
                        <button class="btn btn-default" type="submit" name="command" value="delete_receipt">
                                <%--<fmt:message key="usersTable.delete"/>--%> !!!Удалить заказ
                        </button>

                    </form>
                </td>
                </tr>
<%--            </c:if>--%>
        </c:forEach>
        </tbody>
    </table>


</div>
</body>
</html>
