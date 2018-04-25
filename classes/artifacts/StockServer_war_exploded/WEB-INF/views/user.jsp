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
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<c:set var="id" value="${userprof.id}"/>
<header>

    <nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
        <div class="topnav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a>USERNAME: ${pageContext.request.userPrincipal.name}</a></li>
                <li class="nav-item active">
                    <a>Email: <c:out value="${userprof.email}"/></a></li>
            </ul>
        </div>
    </nav>
</header>
<div class="content">
    <form:form  method="post" modelAttribute="categoryForm">

        <spring:bind path="food">
            <form:checkbox path="food"  ></form:checkbox>Food</br>
        </spring:bind>

        <spring:bind path="householdItems">
            <form:checkbox path="householdItems" ></form:checkbox> Household items</br>
        </spring:bind>

        <spring:bind path="clothes">
            <form:checkbox path="clothes" ></form:checkbox>Clothes</br>

        </spring:bind>

        <spring:bind path="appliances">
            <form:checkbox path="appliances" ></form:checkbox>Appliances</br>
        </spring:bind>

        <spring:bind path="userId">
            <form:hidden path="userId" value = "${id}"/>

        </spring:bind>

        <input type="submit" class="btn btn-primary" value="Submit"/>
    </form:form>
</div>

</body>

</html>
