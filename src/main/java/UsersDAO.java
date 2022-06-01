import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/UsersDAO")
public class UsersDAO extends HttpServlet {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public UsersDAO() {

    }
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } 
            catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/moderate?"
  			          + "user=john&password=pass1234");
            System.out.println(connect);
            System.out.println("Connected to moderate users database");
        }
    }
    
    public void initDB() throws SQLException {
    	String sql1 = "DROP TABLE IF EXISTS users";
    	String sql2 = "CREATE TABLE IF NOT EXISTS users " +
        	    "(prefName VARCHAR(50) not NULL, " +
        	    " password VARCHAR(20), " + 
        	    " email VARCHAR(50), " + 
        	    " PRIMARY KEY ( email ))";
		connect_func();
    	statement = connect.createStatement();
	    statement.executeUpdate(sql1);
	    System.out.println("Tables and data cleared from adoption database");
	    statement.executeUpdate(sql2);
	    System.out.println("Schema created for users table");
      }
    
    public Users loginUser(String email, String password) throws SQLException {
    	Users user = null;
    	String sql = "SELECT * FROM users WHERE email = ? and password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, email);
    	preparedStatement.setString(2, password);

    	ResultSet resultSet = preparedStatement.executeQuery();
    	
    	 while (resultSet.next()) {
             String prefName = resultSet.getString("prefName");
              
             user = new Users(prefName, password, email);
    	 }
    	
        resultSet.close();
        statement.close();         
        disconnect(); 
    	return user;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
         
    public boolean insert(Users user) throws SQLException {
    	connect_func();         
		String sql = "insert into users(prefName, password, email) values (?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, user.prefName);
		preparedStatement.setString(2, user.password);
		preparedStatement.setString(3, user.email);
		
//		preparedStatement.executeUpdate();
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowInserted;
    }     
     
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
