<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-theme.min.css" />
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-9">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="" class="dropdown-toggle" data-toggle="dropdown">Студент<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/?method=departmentList">Список курсов</a></li>
                        <li><a href="/?method=studentGetCourses">Посмотреть оценки</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="" class="dropdown-toggle" data-toggle="dropdown">Преподаватель <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/?method=teacherGetCourse">Список курсов</a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>

</body>
</html>
