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
    <style>
        input {
            width: 550px; /* Ширина списка в пикселах */
        }
    </style>
    <style>
        select {
            width: 550px; /* Ширина списка в пикселах */
        }
    </style>

</head>
<body>
<jsp:include page="menu/header.jsp"/>
<jsp:include page="menu/headerAdmin.jsp"/>
<br/>
<h3><fmt:message key="productsTable.editingProduct"/><c:out value=" ${product.name}"/> </h3>
<br/>
<form action="controller" method="post">
    <input type="hidden" name="id" value="<c:out value="${product.id}"/>">
    <label> Наименование товара</label><p>
    <input type="text" name="name" value="<c:out value="${product.name}"/>"><p>
    <label> Количество</label><p>
    <input type="text" name="count" value="<c:out value="${product.count}"/>" required pattern="^\d+$"><p>
    <label> Цена</label><p>
    <input type="text" name="price" value="<c:out value="${product.price}"/>" required pattern="^[0-9]*[.,]?[0-9]+$"><p>
    <label> Цвет</label><p>
    <input type="text" name="color" value="<c:out value="${product.color}"/>"><p>
    <label> Категория товара</label><p>
    <div class="form-group" >
        <p><select name="category">
            <option disabled>Выберите из списка</option>
            <c:forEach items="${categoryList}" var="categoryList" varStatus="loop">
                <option value=${categoryList.getName()}>
                        ${categoryList.getName()}
                </option>
            </c:forEach>
        </select></p>
    </div>

    <button class="btn btn-md btn-primary <%--btn-block--%>" type="submit" name="command" value="edit_product">
        <fmt:message key="productsTable.edit"/>
    </button>
<%--    <button class="btn btn-md btn-danger &lt;%&ndash;btn-block&ndash;%&gt;" type="submit" name="command" value="to_admin_users">--%>
<%--        <fmt:message key="adminEditUser.cancel"/>--%>
<%--    </button>--%>
<%--    <c:if test="${errorSignInMessageKey != null}">--%>
<%--        <label style="color: red"><fmt:message key="${errorSignInMessageKey}"/></label>--%>
<%--    </c:if>--%>
</form>
</body>
</html>
