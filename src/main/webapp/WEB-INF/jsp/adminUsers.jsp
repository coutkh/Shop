<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="adminAddUser.users"/></title>
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

<button type="button" class="btn btn-lg btn-primary btn-block" data-toggle="modal" data-target="#addModal">
    <fmt:message key="usersTable.insertUser"/>
</button>

<form action="controller" method="post">
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="usersTable.insertingUser"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <input type="text" class="form-control" name="login" placeholder=<fmt:message key="usersTable.enterLogin"/>"введите логин/name">
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control" name="email" placeholder=<fmt:message key="usersTable.entertEmail"/>"введите email/amount">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" name="password" placeholder=<fmt:message key="usersTable.enterPassword"/>"введите пароль/price">
                        </div>
                        <div class="form-group">
                            <p><select name="role">
                                <option disabled><fmt:message key="usersTable.SelectFromTheList"/></option>
                                <option value="admin">admin</option>
                                <option value="user">user</option>
                            </select></p>
                            <%--                            <p>--%>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" name="command" value="add_user"> <fmt:message key="adminAddUser.add"/> </button>
                </div>
            </div>
        </div>
    </div>
</form>


<%--<form action="controller" method="post">--%>
<%--    <button class="btn btn-lg btn-primary btn-block" type="submit" name="command" value="to_add_user_page">--%>
<%--        <fmt:message key="usersTable.insertUser"/>--%>
<%--    </button>--%>
<%--</form>--%>
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
                <td><c:out value="${userList.getLogin()}"/></td>
                <td><c:out value="${userList.getEmail()}"/> </td>
                <td><c:out value="${userList.getPassword()}"/> </td>
                <td><c:out value="${userList.getCreateTime()}"/> </td>
                <td><c:out value="${userList.getRole().getRole()}"/> </td>
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="login" value="${userList.getLogin()}">
                        <input type="hidden" name="email" value="${userList.getEmail()}">
                        <input type="hidden" name="password" value="${userList.getPassword()}">
                        <input type="hidden" name="role" value="${userList.getRole().getRole()}">
                        <button class="btn btn-default" type="submit" name="command" value="delete_user">
                            <fmt:message key="usersTable.delete"/>
                        </button>
                        <button class="btn btn-default" type="submit" name="command" value="to_edit_user_page">
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