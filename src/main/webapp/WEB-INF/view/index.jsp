<%--
  Created by IntelliJ IDEA.
  User: taumal
  Date: 11/13/17
  Time: 7:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${home}" var="home" varStatus="dd">
    <h1>${home.greetings} From Spring 5</h1>
</c:forEach>
</body>
</html>
