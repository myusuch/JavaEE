// MainPage.java
// =================
// servlet (Controller) to render the member’s main page.
//
// AUTHOR: Myungsuk Choi (myusuch@gmail.com, 991 388 329)
// CREATED: 2016-07-29
// UPDATED: 2016-07-29
package javaclub;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MainPage")
public class MainPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get session object first
        HttpSession session = request.getSession(false);

        if (session == null) {
            // not login yet, redirect to login page
            response.sendRedirect("login.jsp");

        } else {
            // NOTE: you need more test here!
            // login success, disable browser cache first
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setHeader("Expires", "0"); // Proxies.

            // forward to mainPage.jsp
            RequestDispatcher rd = request.getRequestDispatcher("mainPage.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
