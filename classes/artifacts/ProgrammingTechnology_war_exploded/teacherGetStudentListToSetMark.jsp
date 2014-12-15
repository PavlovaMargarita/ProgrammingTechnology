<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="menu.jsp"/>
<html>
<head>
    <title></title>
    <style>
        .linkStyle {
            font: bold 17px Arial;

        }
    </style>
</head>

<body>

<h1> Список студентов </h1>
<table class="table table-hover" style="width: 80%; margin-left: 10%; margin-right: 10%;">
    <thead>
    <tr  class="warning">
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Оценка</th>
    </tr>
    </thead>
    <!-- Table Header -->

    <form action="${pageContext.request.contextPath}/servlet?method=saveMarks&StL=${studentList}" method="post">
        <c:forEach var="element" items="${studentList}">

            <tr>
                <td>${element.firstName}</td>
                <td>${element.lastName}</td>
                <td>${element.patronymic}</td>

                <c:forEach var="el" items="${element.generalCourses}">
                    <td>
                        <c:choose>
                            <c:when test="${el.mark==0}">
                                <input type="text" name="${element.id}" value="">
                            </c:when>
                            <c:otherwise>
                                <input type="text" name="${element.id}"  value="${el.mark}">
                            </c:otherwise>
                        </c:choose>

                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
        <div style="margin-bottom: 1%">
            <input type="hidden" name="courseId" value="${courseId}"/>
            <button type="submit" class="btn btn-primary" >Сохранить</button>
            <a class="linkStyle btn btn-primary"  href="index.jsp">Отмена</a>
        </div>
        </form>

</table>

</body>
</html>
