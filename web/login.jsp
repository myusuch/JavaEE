<%--
 login.jsp
 =================
 A login page once user fails to log in.

 AUTHOR: Myungsuk Choi (myusuch@gmail.com, 991 388 329)
 CREATED: 2016-07-29
 UPDATED: 2016-07-30
--%>

<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Login Page</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="main.css" />" />
    </head>
    <body>
        <p id="errorMessge">
            <c:if test="${not empty loginMessage}">
                ${loginMessage} <br>
            </c:if>
        </p>

        <form action="Login" method="post">
            <div class="fieldcontainer">
                <div class="label">User ID : </div>
                <div class="field"><input type="text" name="id"></div>
            </div>
            <div class="fieldcontainer">
                <div class="label"> Password : </div>
                <div class="field"><input type="password" name="password"></div>
            </div>
            <div class="fieldcontainer">
                <div class="label">&nbsp;</div>
                <div class="field"><input type="submit" value="Login" id="button"></div>
            </div>
        </form>

        <div class="message"> Not register yet? <a href="register.jsp">Sign up here.</a></div>
    </body>
</html>
