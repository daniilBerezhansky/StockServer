<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MyPC
  Date: 04.04.2018
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<c:set var="contextPath" value="${pageContext.request.contextPath}"/>--%>

<html>
<head>
    <title>User profile</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/user.css">
</head>
<body>
<c:set var="id" value="${userprof.id}"/>
<div class="navigation">
    <ul class="nav">
        <li>username: ${pageContext.request.userPrincipal.name}</li>
        <li>email: <c:out value="${userprof.email}"/></li>
    </ul>
</div>
<div class="content">
    <form:form  method="post" modelAttribute="categoryForm">

        <spring:bind path="food">
            <form:checkbox path="food" ></form:checkbox>Food</br>
        </spring:bind>

        <spring:bind path="householdItems">
            <form:checkbox path="householdItems" ></form:checkbox> Household items</br>
        </spring:bind>

        <spring:bind path="clothes">
            <form:checkbox path="clothes"></form:checkbox>Clothes</br>

        </spring:bind>

        <spring:bind path="appliances">
            <form:checkbox path="appliances"></form:checkbox>Appliances</br>
        </spring:bind>

        <spring:bind path="userId">
            <form:hidden path="userId" value = "${id}"/>

        </spring:bind>

        <button type="submit">Submit</button>

    </form:form>
</div>
<p>
</p>
</body>
</html>
