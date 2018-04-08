<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MyPC
  Date: 04.04.2018
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%--<c:set var="contextPath" value="${pageContext.request.contextPath}"/>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User profile</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/user.css">
</head>
<body>
<div class="navigation">
    <ul class="nav">
        <li>${pageContext.request.userPrincipal.name}</li>
        <li>email: <c:out value="${userprof.email}"/></li>
    </ul>
</div>
<div class="content">
    <form>
        <input type="checkbox">Food</br>
        <input type="checkbox">Household items</br>
        <input type="checkbox">Clothes</br>
        <input type="checkbox">Appliances</br>
        <button type="submit">Submit</button>
    </form>
</div>
<p>test</p>
</body>
</html>
