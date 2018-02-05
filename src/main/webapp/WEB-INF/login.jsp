<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body>

login page.
</br>
</br>


<button name="click" onclick="search()">클라이언트 검색</button>
<br/>

<c:if test="${client != null}">

  인증 가능한 클라이언트 : ${client.copName}

</c:if>



</br>
</br>
</br>




<label for="id"></label><input type="text" id="id" class="input" name="id" placeholder="클라이언트 아이디 입력">&nbsp<button name="click" onclick="submit()">클릭</button>

</body>

<script>


  var login = "http://localhost:8080/oauth-api/login/";
  var client = "http://localhost:8080/oauth-api/search";

  function submit() {

      var id = $("input[name='id']").val();

      if(id == null || id == '') return false;

      location.href = login + id;
  }
  
  
  function search() {

      location.href = client;
  }

</script>