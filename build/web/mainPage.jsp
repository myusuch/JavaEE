<%--
 mainPage.jsp
 =================
 A main page once user successes login.

 AUTHOR: Myungsuk Choi (myusuch@gmail.com, 991 388 329)
 CREATED: 2016-07-29
 UPDATED: 2016-07-30
--%>

<%@ page contentType="text/html" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Main Page</title>
    </head>
    <body>
        <h3> Hello ${user.firstName},<br>
            you log in JavaClub successfully.</h3>
        Your Profile: <br>
        Name: ${user.firstName} ${user.lastName}<br>
        Email: ${user.email} <br><br>

        <a href="logout.jsp">Log out</a>
    </body>
</html>
