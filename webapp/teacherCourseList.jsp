<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="menu.jsp"/>
<html>
<head>
    <title></title>
</head>
<body>
<h1> Список курсов </h1>
<table class="table table-hover" style="width: 80%; margin-left: 10%; margin-right: 10%;">
    <thead>
    <tr  class="warning">
        <th>Предмет </th>
    </tr>
    </thead>
    <!-- Table Header -->
    <c:forEach var="element" items="${courseList}">

        <tr>
            <td>${element.title}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
