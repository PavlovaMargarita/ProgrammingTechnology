<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <h1>Давай приниматься за работу</h1>
  <c:choose>
      <c:when test="${role =='teacher'}">
          This is teacher!
      </c:when>
      <c:otherwise>
          OOO.... not work.
      </c:otherwise>
  </c:choose>

  ROLE=<c:out value="${role}"/>
  ID=<c:out value="${idUser}"/>
  </body>
</html>
