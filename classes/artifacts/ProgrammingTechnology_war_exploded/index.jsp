<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="menu.jsp"/>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <h1>Давай приниматься за работу</h1>
  <h2>Здесь потом напишем какие-нибудь приветственные слова</h2>
  ROLE=<c:out value="${role}"/>
  ID=<c:out value="${idUser}"/>
  </body>
</html>

