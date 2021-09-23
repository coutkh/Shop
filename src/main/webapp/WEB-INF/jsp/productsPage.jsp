<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<!DOCTYPE html>
<html leng="en">
<head>

    <title>Products</title>
    <meta charset="utf-8">
    <link rel="stylesheet"  type="text/css" href="../css/my.css">
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <!-- Latest compiled and minified CSS -->
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">--%>
    <!-- Optional theme -->
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">--%>
    <style>
        #select-modal {
            width: 570px; /* Ширина списка в пикселах */
        }
    </style>
    <style>
        #select-cat {
            width: 200px; /* Ширина списка в пикселах */
        }
    </style>
</head>
<jsp:include page="menu/header.jsp"/>

<body>
<%--<br/>--%>
<%--<br/>--%>

<c:if test = "${currentUser.getRole().getRole().equals('admin')}">
    <button type="button" class="btn btn-lg btn-primary btn-block" data-toggle="modal" data-target="#addModal">
        <fmt:message key="productsTable.addProduct"/>
    </button>
</c:if>

<form action="controller" method="post">
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                            <input type="text" class="form-control" name="name" placeholder="наименование/name">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="count" placeholder="количество/amount">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="price" placeholder="цена/price">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="color" placeholder="цвет/color">
                        </div>
                        <div class="form-group">
                            <p><select name="category" id="select-modal">
                                <option disabled>Выберите из списка</option>
                                <c:forEach items="${categoryList}" var="categoryList" varStatus="loop">
                                    <option value=${categoryList.getName()}>
                                            ${categoryList.getName()}
                                    </option>
                                </c:forEach>
                            </select></p>
<%--                            <p>--%>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                    </button>
                    <button type="submit" class="btn btn-primary" name="command" value="add_product"> <fmt:message key="productsTable.add"/> </button>
                </div>
            </div>
        </div>
    </div>
</form>


<div class="container-fluid">
    <div class="row">
        <div class="col-lg-2">

            <form action="controller" method="POST">
                <p>фильтр по категории</p>
                <c:forEach items="${categoryList}" var="categoryList" varStatus="loop">
                        <input type="checkbox" name="categoryName" value="${categoryList.getName()}" checked />${categoryList.getName()}<br/>
                </c:forEach>
                <br/>
                <p>сортировать по цене</p>
                <div class="form-group">
                    <p><select name="sort" id="select-cat">
                        <option <%--disabled--%>>Выберите из списка</option>
                        <option value="from_cheap">от дешевых к дорогим</option>
                        <option value="from_expensive">от дорогих к дешевым</option>
                        <option value="color">по цвету</option>
                        <option value="from_lot">в наличии от меньшего</option>
                        <option value="from_top">в наличии от большего</option>
                    </select></p>
                </div>

                <input type="submit" name="command" value="filter_and_sort" />
            </form>
        </div>
        <div class="col-lg-10">
<%--            <h2>Список товаров</h2>--%>


            <table class="table table-condensed table-hover ">
                <thead>
                <tr>
                    <th><fmt:message key="productsTable.name"/></th>
                    <th><fmt:message key="productsTable.amount"/></th>
                    <th><fmt:message key="productsTable.price"/></th>
                    <th><fmt:message key="productsTable.color"/></th>
                    <th><fmt:message key="productsTable.categoryName"/></th>
                    <c:if test = "${currentUser.getRole().getRole().equals('admin')}">
                        <th><fmt:message key="productsTable.createDate"/></th>
                        <th><fmt:message key="productsTable.lastUpdate"/></th>
                    </c:if>
                </tr>
                </thead>

                <tbody>

                <c:forEach items="${productList}" var="productList" varStatus="loop">
                    <tr>
                        <td><c:out value="${productList.getName()}"/></td>
                        <td><c:out value="${productList.getCount()}"/> </td>
                        <td><c:out value="${productList.getPrice()}"/> </td>
                        <td><c:out value="${productList.getColor()}"/> </td>
                        <td><c:out value="${productList.getCategory().getName()}"/> </td>
                        <c:if test = "${currentUser.getRole().getRole().equals('admin')}">
                            <td><c:out value="${productList.getCreateDate()}"/> </td>
                            <td><c:out value="${productList.getLastUpdate()}"/> </td>
                        </c:if>
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="id" value="${productList.getId()}">
                                <input type="hidden" name="name" value="${productList.getName()}">
                                <input type="hidden" name="count" value="${productList.getCount()}">
                                <input type="hidden" name="price" value="${productList.getPrice()}">
                                <input type="hidden" name="color" value="${productList.getColor()}">
                                <input type="hidden" name="category" value="${productList.getCategory().getName()}">
                                <button class="btn btn-success btn-xs" type="submit" name="command" value="to_confirm_selection_page">
                                    <fmt:message key="productsTable.toBasket"/>
                                </button>
                                <c:if test = "${currentUser.getRole().getRole().equals('admin')}">
                                    <button class="btn btn-default btn-xs" type="submit" name="command" value="delete_product">
                                        <fmt:message key="usersTable.delete"/>
                                    </button>
                                    <button class="btn btn-default btn-xs" type="submit" name="command" value="to_edit_product_page">
                                        <fmt:message key="productsTable.edit"/>
                                    </button>
                                </c:if>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>