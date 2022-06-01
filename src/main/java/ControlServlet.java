import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsersDAO usersDAO;
 
    public void init() {
        usersDAO = new UsersDAO(); 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
            case "/initialize":
            	initDB(request, response);
                break;
            case "/register":
                showRegForm(request, response);
                break;
            case "/insert":
            	insertUser(request, response);
                break;
            case "/verify":
            	loginUser(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void initDB(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	usersDAO.initDB();
    	System.out.println("Database initialized. Showing login form");
    	RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
        dispatcher.forward(request, response);
    }
    
    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	String email = request.getParameter("email");
        String password = request.getParameter("password");
         
            Users user = usersDAO.loginUser(email, password);
            String destPage = "LoginForm.jsp";
             
            if (user != null) {
                destPage = "Home.jsp";
                System.out.println("Login successful");
            } 
            else {
                String message = "Invalid email or password - try again.";
                System.out.println("Unsuccessful login");
                request.setAttribute("message", message);
            }
             
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
    }
    
    private void showRegForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterForm.jsp");
        dispatcher.forward(request, response);
    }
  
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String prefName = request.getParameter("prefName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Users user = new Users(prefName, password, email);
        usersDAO.insert(user);
        response.sendRedirect("LoginForm.jsp");
    }

}