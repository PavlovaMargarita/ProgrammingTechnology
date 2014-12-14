<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="menu.jsp"/>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet?method=employeeCreateForm" method="post">
    <div>
        <div class="department-information" style="width: 40%; margin-right: 30%; margin-left: 30%;">
            <input type="hidden" id="param" name="param" value="${param}">

            <div class="input-group">
                <span class="input-group-addon">Курс 1</span>
                <select name="department" class="form-control">
                    <c:forEach var="element" items="${fullCourseList}">
                        <c:choose>
                            <c:when test="${student.generalCourses[0].key.id == element.id}">
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
                <span class="input-group-addon">Курс 1</span>
                <select name="department" class="form-control">
                    <c:forEach var="element" items="${fullCourseList}">
                        <c:choose>
                            <c:when test="${student.generalCourses[1].key.id == element.id}">
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
                <span class="input-group-addon">Курс 1</span>
                <select name="department" class="form-control">
                    <c:forEach var="element" items="${fullCourseList}">
                        <c:choose>
                            <c:when test="${student.generalCourses[2].key.id == element.id}">
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
                <span class="input-group-addon">Курс 1</span>
                <select name="department" class="form-control">
                    <c:forEach var="element" items="${fullCourseList}">
                        <c:choose>
                            <c:when test="${student.generalCourses[3].key.id == element.id}">
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
                <span class="input-group-addon">Курс 1</span>
                <select name="department" class="form-control">
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
                <span class="input-group-addon">Курс 1</span>
                <select name="department" class="form-control">
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
</body>
</html>
