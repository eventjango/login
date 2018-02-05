<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2018-01-31
  Time: 오후 5:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>

회원 아이디 : ${user.userId} </br>
회원 이름 : ${user.username} </br>
회원 등록날짜 : ${user.regDate} </br>
회원 수정날짜 : ${user.udtDate} </br>

회원인증여부 :

<c:choose>

  <c:when test="${user.isAuth eq true}">
    인증됨
  </c:when>

  <c:otherwise>
    인증되지 않음
  </c:otherwise>
</c:choose>



</br>
</br>
</br>



</body>
</html>
