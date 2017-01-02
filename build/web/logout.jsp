<%--
 logout.jsp
 =================
 A log out page once user clicks logout button.

 AUTHOR: Myungsuk Choi (myusuch@gmail.com, 991 388 329)
 CREATED: 2016-07-29
 UPDATED: 2016-07-30
--%>

<%@ page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Logout</title>
    </head>
    <body>
        <p>Goodbye, you successfully logged out.</p>

        <a href="index.html">Go to Home page.</a>
        <%-- close the session --%>
        <% session.invalidate(); %>

    </body>
</html>
