<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="menu.jsp"/>
<html>
<head>
    <title></title>
</head>
<body>

<h1> Список студентов </h1>
<table class="table table-hover" style="width: 80%; margin-left: 10%; margin-right: 10%;">
    <thead>
    <tr  class="warning">
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
    </tr>
    </thead>
    <!-- Table Header -->
    <c:forEach var="element" items="${studentList}">

        <tr>
            <td>${element.firstName}</td>
            <td>${element.lastName}</td>
            <td>${element.patronymic}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
