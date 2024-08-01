<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/movies" method="post">
        <c:forEach items="${movies}" var="movie">
                <img src="${movie.imgUrl}" width="300px">
                <input type="checkbox" name="check" value="${movie.mtitle}">${movie.mtitle}
        </c:forEach>
        <button type="submit">BUY</button>
    </form>
</body>
</html>
