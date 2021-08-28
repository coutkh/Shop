<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<!DOCTYPE>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>ADMIN</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <style>
        #inputCategory {
            width: 900px;
        }
    </style>
</head>
<body>


<jsp:include page="menu/header.jsp"/>
<jsp:include page="menu/headerAdmin.jsp"/>
<br/>
<form action="controller" method="post">
    <button class="btn btn-lg btn-primary btn-block" type="submit" name="command" value="to_add_category_page">
        <fmt:message key="adminCategoryPage.insertCategory"/>
    </button>
</form>
<div class="container">
    <form action="controller" method="post">
        <input type="text" name = "category" id="inputCategory" placeholder="<fmt:message key="adminCategoryPage.insertCategory"/>">

        <button class="btn btn-md btn-primary <%--btn-block--%>" type="submit" name="command" value="add_category">
            <fmt:message key="adminCategoryPage.add"/>
        </button>
        <button class="btn btn-md btn-danger <%--btn-block--%>" type="submit" name="command" value="to_categories">
            <fmt:message key="adminCategoryPage.cancel"/>
        </button>
    <%--    <c:if test="${errorSignInMessageKey != null}">--%>
    <%--        <label style="color: red"><fmt:message key="${errorSignInMessageKey}"/></label>--%>
    <%--    </c:if>--%>
    </form>
</div>
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
<%--                        <button class="btn btn-default" type="submit" name="command" value="to_edit_category_page">--%>
<%--                            <fmt:message key="usersTable.edit"/>--%>
<%--                        </button>--%>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                            <fmt:message key="usersTable.edit"/>
                        </button>

                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form>
                                            <div class="form-group">
<%--                                                <input type="text" class="form-control" id="recipient-name" value="<c:out value="${categoryList.getName()}"/>">--%>
                                                <input type="text" class="form-control" id="recipient-name" value="${}">
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary">Send message</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>