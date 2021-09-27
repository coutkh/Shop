<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="basketPage.basket"/></title>
    <link rel="stylesheet"  type="text/css" href="css/my.css">
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
<%--    <!-- Latest compiled and minified CSS -->--%>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">--%>
<%--    <!-- Optional theme -->--%>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">--%>


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
            <c:if test="${receiptList.getUser().getId() == currentUser.getId()}">
                <tr>
                    <td><c:out value="Заказ от: ${receiptList.getCreateDate()}"/></td>
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
                                Отменен
                            </c:when>
                        </c:choose>
<%--                        <c:out value="${receiptList.getStatus().getStatus_name()}"/>--%>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="id" value="${receiptList.getId()}">
                            <input type="hidden" name="page" value="basket">
                            <button class="btn btn-default" type="submit" name="command" value="confirm_receipt">
                                <fmt:message key="basketPage.confirmTheOrder"/>
                            </button>
                            <button class="btn btn-default" type="submit" name="command" value="delete_receipt">
                                <fmt:message key="basketPage.deleteOrder"/>
                            </button>

                        </form>
                    </td>
                <c:forEach items="${receiptHasProductList}" var="receiptHasProductList" varStatus="loop">
                    <c:if test="${receiptList.getId() == receiptHasProductList.getReceipt().getId()}">
                    <tr>
                        <td><c:out value="${receiptHasProductList.getProduct().getName()}"/></td>
                        <td><c:out value="${receiptHasProductList.getCount()}"/> </td>
                        <td><c:out value="${receiptHasProductList.getPrice()}"/> </td>
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="receiptHasProdId" value="${receiptHasProductList.getId()}">
                                <input type="hidden" name="productId" value="${receiptHasProductList.getProduct().getId()}">
                                <button class="btn btn-default" type="submit" name="command" value="delete_product_from_receipt">
                                        <fmt:message key="basketPage.deleteProd"/>
                                </button>

                            </form>
                        </td>
                    </tr>
                    </c:if>
                </c:forEach>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>


</div>
</body>
</html>
