// Login.java
// =================
// servlet (Controller) to allow user to login.
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
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parameters
        String idFromWB = request.getParameter("id");
        String passwordFromWB = request.getParameter("password");

        // if vaild, set a session attribute and store the user obj
        UserDatabase db = new UserDatabase();

        String loginMessage = null;

        if (db.isValid(idFromWB, passwordFromWB)) {
            // create session at this moment!
            HttpSession session = request.getSession(true);

            // get the user from DB and set as session attribute
            User user = db.getUser(idFromWB);

            session.setAttribute("user", user);

            // redirect to MainPage
            response.sendRedirect("MainPage");
        } // invalid login, forward to login.jsp
        else {
            // set a request attribute, “loginMessage”
            loginMessage = "You entered wrong user ID or password.";
            request.setAttribute("loginMessage", loginMessage);

            // forward to login.jsp to show login form again
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }
}
