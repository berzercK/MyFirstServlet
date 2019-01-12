<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<% String loginName = (String) session.getAttribute("attemptLoginName"); %>--%>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Change password</title>
</head>
<body>

<div>
    <pre>    You input incorrect password more than 3 times!</pre>
    <pre>    Create new password for: <c:out value="${sessionScope.attemptLoginName}"/></pre>
    <form action="${pageContext.request.contextPath}/tasks/task2/change_password" method="GET">

        <pre> Login:              <input type="text" contenteditable="false" name="login" value=<c:out value="${sessionScope.attemptLoginName}"/> ></pre>
        <pre> New password:       <input type="password" name="password1"/></pre>
        <pre> New password again: <input type="password" name="password2"/></pre>
        <pre>           <input type="submit" value="Изменить пароль"></pre>
    </form>
</div>


</body>
</html>