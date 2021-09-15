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
            <tr>
                <td><c:out value="Заказ от: ${receiptList.getCreateDate()}"/></td>
                <td><c:out value="${receiptList.getTotal()}"/> </td>
                <td><c:out value="${receiptList.getStatus().getStatus_name()}"/> </td>
            </tr>
            <label class="link" for="hider" id="clickme">Нажми на меня!</label>
            <input type="checkbox" id="hider">
            <div class="content">Привет! Здесь содержание.</div>
        </c:forEach>
        </tbody>
    </table>

    <div class="spoiler">
        <input type="checkbox" >
        <div class="box">
            Текст сообщения в спойлере.
        </div>
    </div>


    <img src="http://javascript.ru/forum/images/ca_serenity/misc/logo.gif" alt="">
    <input name="" type="checkbox">
        <div>Есть картинка. Нужно чтобы при нажатии на нее внизу(или задаваемо) появлялся текст. Важно чтобы это было максимально коротко. Очень нужно именно в CSS. И нужно появление плавное. Бть может это можно задать transmision. Помогите)
    </div>



</div>
</body>
</html>
<%--                <td>--%>
<%--                    <form action="controller" method="post">--%>
<%--                        <input type="hidden" name="login" value="${userList.getLogin()}">--%>
<%--                        <input type="hidden" name="email" value="${userList.getEmail()}">--%>
<%--                        <input type="hidden" name="password" value="${userList.getPassword()}">--%>
<%--                        <input type="hidden" name="role" value="${userList.getRole().getRole()}">--%>
<%--                        <button class="btn btn-default" type="submit" name="command" value="delete_user">--%>
<%--                            <fmt:message key="usersTable.delete"/>--%>
<%--                        </button>--%>
<%--                        <button class="btn btn-default" type="submit" name="command" value="to_edit_user_page">--%>
<%--                            <fmt:message key="usersTable.edit"/>--%>
<%--                        </button>--%>
<%--                    </form>--%>
<%--                </td>--%>
