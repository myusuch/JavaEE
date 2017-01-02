<%--
 register.jsp
 =================
 A register page to join JavaClub.

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
        <title>Java Club Registration Page</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="main.css" />" />
    </head>
    <body>
        <h1>Java Club Registration Form</h1>

        <p>Please fill out the form below... </p>

        <c:if test="${not empty signupMessage}">
            <p id="errorMessge">
                ${signupMessage} </p>
        </c:if>

        <form action="Register" method="post" >

            <div class="fieldcontainer">
                <div class="label">Choose user ID :</div>
                <div class="field"><input type="text" name="id"> </div>
            </div>

            <div class="fieldcontainer">
                <div class="label">Choose your password : </div>
                <div class="field"><input type="password" name="password"> </div>
            </div>


            <div class="fieldcontainer">
                <div class="label">Re-enter password : </div>
                <div class="field"><input type="password" name="passwordCnf"></div>
            </div>

            <div class="fieldcontainer">
                <div class="label">Enter your first name : </div>
                <div class="field"><input type="text" name="firstName"> </div>
            </div>

            <div class="fieldcontainer">
                <div class="label">Enter your last name : </div>
                <div class="field"><input type="text" name="lastName"> </div>
            </div>
            <div class="fieldcontainer">
                <div class="label">Enter your email address : </div>
                <div class="field"><input type="text" name="email"> </div>
            </div>
            <div class="fieldcontainer">
                <div class="label">&nbsp;</div>
                <div class="field"><input type="submit" value="Signup" id="button"></div>
            </div>
        </form>

    </body>
</html>
