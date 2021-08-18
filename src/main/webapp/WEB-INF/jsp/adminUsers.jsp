<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title>main</title>
</head>
<body>
<jsp:include page="menu/header.jsp"/>
<jsp:include page="menu/headerAdmin.jsp"/>
<div class="container">
    <table class="table table-condensed table-hover ">
        <thead>
            <tr>
                <th><fmt:message key="usersTable.login"/></th>
                <th><fmt:message key="usersTable.email"/></th>
                <th><fmt:message key="usersTable.password"/></th>
                <th><fmt:message key="usersTable.createTime"/></th>
                <th><fmt:message key="usersTable.role"/></th>
            </tr>
        </thead>
        <tbody>


        <c:forEach items="${userList}" var="userList" varStatus="loop">
            <tr>
                <td><c:out value="${userList.getLogin()}"/> </td>
                <td><c:out value="${userList.getEmail()}"/> </td>
                <td><c:out value="${userList.getPassword()}"/> </td>
                <td><c:out value="${userList.getCreateTime()}"/> </td>
                <td><c:out value="${userList.getRole().getRole()}"/> </td>
                <button>Button</button>
            </tr>

        </c:forEach>


        </tbody>
    </table>
</div>
</body>
</html>