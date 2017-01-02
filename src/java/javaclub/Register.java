// Register.java
// =================
// servlet (Controller) for the registration form page.
//
// AUTHOR: Myungsuk Choi (myusuch@gmail.com, 991 388 329)
// CREATED: 2016-07-29
// UPDATED: 2016-07-30
package javaclub;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get 6 input parameters from request
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String passwordCnf = request.getParameter("passwordCnf");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        // if all inputs are null, then it is the first request
        if (id == null
                && password == null
                && passwordCnf == null
                && firstName == null
                && lastName == null
                && email == null) {
            // simply forward to register.jsp and exit this method
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
            return;
        }

        // instance of UserDatabase to access DB
        UserDatabase db = new UserDatabase();

        // request attribute to store the current status (error/result)
        String signupMessage = null;

        // subsequent request, validate all input parameters
        // Check ID
        if (id == null || id.isEmpty()) {
            signupMessage = "User ID cannot be empty.";
        } else if (!db.isUnique(id)) {
            signupMessage = "User ID, " + id + " already exists. Choose a different ID";
        } // Check Password
        else if (password == null || password.isEmpty()) {
            signupMessage = "Password cannot be empty.";
        } // Check Confirm Password
        else if (passwordCnf == null || passwordCnf.isEmpty()) {
            signupMessage = "Re-enter password cannot be empty.";
        } // Check LastName
        else if (!password.equals(passwordCnf)) {
            signupMessage = "Password is not matched";
        } // Check FirstName
        else if (firstName == null || firstName.isEmpty()) {
            signupMessage = "First Name cannot be empty.";
        } // Check LastName
        else if (lastName == null || lastName.isEmpty()) {
            signupMessage = "Last Name cannot be empty.";
        } // Check Email
        else if (email == null || email.isEmpty()) {
            signupMessage = "Email cannot be empty.";
        }

        // passed validation, forward to registerResult.jsp
        if (signupMessage == null) {
            // add the user to DB and check it is successful
            int result = db.addUser(id, password, firstName, lastName, email);

            // update signupMessage and set as request attribute
            // NOTE: addUser() may fail. If so, set “Try again...”
            if (result == 1) {
                signupMessage = "Sign up successfully. Thank you for joining JavaClub";

            } else {
                signupMessage = "Sign up failed. Try again Sing up";
            }

            request.setAttribute("signupMessage", signupMessage);
            // forward
            RequestDispatcher rd = request.getRequestDispatcher("registerResult.jsp");
            rd.forward(request, response);

        } // failed validation, forward to register.jsp
        else {
            // update signupMessage and set as request attribute
            request.setAttribute("signupMessage", signupMessage);
            // forward
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        }

    }

}
