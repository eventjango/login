<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2018-02-01
  Time: 오후 1:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>


<c:if test="${client != null}">

  인증 가능한 클라이언트 : ${client.copName}

</c:if>


</body>
</html>
