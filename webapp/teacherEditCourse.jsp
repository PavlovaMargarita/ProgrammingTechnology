<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="menu.jsp"/>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet?method=teacherSaveCourse" method="post">

    <div>
        <div class="department-information" style="width: 40%; margin-right: 30%; margin-left: 30%;">
            <input type="hidden" name="teacher_course_param" value="${teacher_course_param}">

            <div class="input-group">
                <span class="input-group-addon">Курс</span>
                <select name="course" id="course" class="form-control">
                    <c:forEach var="element" items="${courseList}">
                        <%--<c:choose>--%>
                            <option value="${element.id}" selected>${element.title}</option>
                        <%--</c:choose>--%>
                    </c:forEach>
                </select>
            </div>


            <c:choose>
                <c:when test="${teacher_course_param != 'delete'}">
                    <button type="submit" class="btn btn-primary" style="margin-left: 48%;">Сохранить</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" class="btn btn-primary" style="margin-left: 48%;">Удалить</button>
                </c:otherwise>

            </c:choose>
        </div>
    </div>
</form>
</body>
</html>
