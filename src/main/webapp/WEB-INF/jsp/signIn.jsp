<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<!DOCTYPE html>
<html leng="en">
<head>
  <title><fmt:message key="header.sign_in"/> </title>
  <meta charset="utf-8">
</head>
<jsp:include page="menu/header.jsp"/>
<body class="text-center">

<br/>
<br/>
<form <%--class="form-signin"--%> action="controller" method="post">
  <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="header.sign_in"/></h1><br/><br/>
  <input type="text" name = "login" id="inputLogin" <%--class="form-control mb-2"--%> placeholder="<fmt:message key="signup.InputLogin"/>" required autofocus pattern="^(?=.*[A-Za-z0-9]$)[A-Za-z][\w.-]{0,19}$">
  <input type="password" name="password" id="inputPassword" <%--class="form-control mb-3"--%> placeholder="<fmt:message key="signup.InputPassword"/>" required pattern="^[\w]{3,12}$">
  <c:if test="${errorSignInMessageKey != null}">
    <label style="color: red"><fmt:message key="${errorSignInMessageKey}"/></label>
  </c:if>
  <c:if test="${errorLocked != null}">
    <label style="color: red"><fmt:message key="${errorLocked}"/></label>
  </c:if>
  <button class="btn btn-lg btn-primary btn-block" type="submit" name="command" value="sign_in">
    <fmt:message key="header.sign_in"/>
  </button>

</form>
<form class="form-signin" action="controller" method="post">
  <p class="mt-4"><fmt:message key="signup.newHere"/></p>
  <button class="btn btn-lg btn-outline-primary btn-block" type="submit" name="command" value="to_sign_up">
    <fmt:message key="header.sign_up"/>
  </button>
</form>
</body>
</html>