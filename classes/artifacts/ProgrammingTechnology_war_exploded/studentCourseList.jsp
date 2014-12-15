<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="menu.jsp"/>
<html>
<head>
    <title></title>
</head>
<body>

<button type="button" class="btn btn-primary"><a href="/servlet?method=studentEditCourses&student_course_param=create">Создать</a></button>
<button type="button" class="btn btn-primary"><a href="/servlet?method=studentEditCourses&student_course_param=edit">Изменить</a></button>
<button type="button" class="btn btn-primary"><a href="/servlet?method=studentEditCourses&student_course_param=delete">Удалить</a></button>


<h1> Список основных курсов</h1>
<table class="table table-hover" style="width: 80%; margin-left: 10%; margin-right: 10%;">
    <thead>
    <tr  class="warning">
        <th>Название курса</th>
    </tr>
    </thead>
    <!-- Table Header -->
    <c:forEach var="element" items="${student.generalCourses}">

        <tr>
            <td>${element.course.title}</td>
        </tr>
    </c:forEach>

</table>

<h1> Список альтернативных курсов</h1>
<table class="table table-hover" style="width: 80%; margin-left: 10%; margin-right: 10%;">
    <thead>
    <tr  class="warning">
        <th>Название курса</th>
    </tr>
    </thead>
    <!-- Table Header -->
    <c:forEach var="element" items="${student.additionalCourseList}">

        <tr>
            <td>${element.title}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
