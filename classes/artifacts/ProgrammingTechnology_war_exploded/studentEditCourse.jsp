<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="menu.jsp"/>
<html>
<head>
    <title></title>
</head>
<body>
<c:choose>
    <c:when test="${student_course_param != 'delete'}">
        <form action="${pageContext.request.contextPath}/servlet?method=studentSaveCourse" method="post">

            <div>
                <div class="department-information" style="width: 40%; margin-right: 30%; margin-left: 30%;">
                    <input type="hidden" name="student_course_param" value="${student_course_param}">

                    <div class="input-group">
                        <span class="input-group-addon">Курс 1</span>
                        <select name="mainCourse1" id="mainCourse1" class="form-control">
                            <c:forEach var="element" items="${fullCourseList}">
                                <c:choose>
                                    <c:when test="${student.generalCourses[0].course.id == element.id}">
                                        <option value="${element.id}" selected>${element.title}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value=${element.id}>${element.title}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">Курс 2</span>
                        <select name="mainCourse2" id="mainCourse2" class="form-control">
                            <c:forEach var="element" items="${fullCourseList}">
                                <c:choose>
                                    <c:when test="${student.generalCourses[1].course.id == element.id}">
                                        <option value="${element.id}" selected>${element.title}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value=${element.id}>${element.title}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">Курс 3</span>
                        <select name="mainCourse3" id="mainCourse3" class="form-control">
                            <c:forEach var="element" items="${fullCourseList}">
                                <c:choose>
                                    <c:when test="${student.generalCourses[2].course.id == element.id}">
                                        <option value="${element.id}" selected>${element.title}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value=${element.id}>${element.title}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">Курс 4</span>
                        <select name="mainCourse4" id="mainCourse4" class="form-control">
                            <c:forEach var="element" items="${fullCourseList}">
                                <c:choose>
                                    <c:when test="${student.generalCourses[3].course.id == element.id}">
                                        <option value="${element.id}" selected>${element.title}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value=${element.id}>${element.title}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">Альтернативный курс 1</span>
                        <select name="addCourse1" id="addCourse1" class="form-control">
                            <c:forEach var="element" items="${fullCourseList}">
                                <c:choose>
                                    <c:when test="${student.additionalCourseList[0].id == element.id}">
                                        <option value="${element.id}" selected>${element.title}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value=${element.id}>${element.title}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">Альтернативный курс 2</span>
                        <select name="addCourse2" id="addCourse2" class="form-control">
                            <c:forEach var="element" items="${fullCourseList}">
                                <c:choose>
                                    <c:when test="${student.additionalCourseList[1].id == element.id}">
                                        <option value="${element.id}" selected>${element.title}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value=${element.id}>${element.title}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <br/>
                    <button type="submit" class="btn btn-primary" style="margin-left: 48%;">Сохранить</button>
                </div>
            </div>


        </form>
    </c:when>
    <c:otherwise>
        <form action="${pageContext.request.contextPath}/servlet?method=studentSaveCourse" method="post">
        <input type="hidden" name="student_course_param" value="${student_course_param}">

        Подтвердите удаление:
        <button type="submit" class="btn btn-primary" style="margin-left: 48%;">Удалить</button>
        <button type="button" class="btn btn-primary" style="margin-left: 48%;"><a
                href="/servlet?method=studentGetCourses">Отмена</a></button>
        </form>
    </c:otherwise>

</c:choose>
</body>
</html>
