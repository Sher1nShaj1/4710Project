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
@WebServlet("/UserDAO")
public class UserDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public UserDAO() throws SQLException {
		
    }
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
  			          + "useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
    }
    
 
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    public void dropTable() throws SQLException {
    	connect_func();   
    	statement = connect.createStatement();
    	
    	String sql1 = "DROP TABLE IF EXISTS Users"; 
    	statement.executeUpdate(sql1);
    	 if (statement != null) {
 	        statement.close();
 	     }
    	
    }
    public int createTable() throws SQLException {
    	connect_func();   
    	statement = connect.createStatement();
    	String sql2 = "CREATE TABLE IF NOT EXISTS Users(" + 
    			"	email VARCHAR(100) NOT NULL," + 
    			"	password VARCHAR(20)," + 
    			"	firstName VARCHAR(20),\r\n" + 
    			"	lastName VARCHAR(20),\r\n" + 
    			"	gender CHAR(1),\r\n" + 
    			"	birthday DATE ,\r\n" + 
    			"	numOfFollowers INT,\r\n" + 
    			"	numOfFollowings INT,\r\n" + 
    			"	PRIMARY KEY(email))" ;
    	
    	 int rowsInserted = statement.executeUpdate(sql2);
    	 if (statement != null) {
    	        statement.close();
    	 }
        return rowsInserted;
    }
    
    public boolean fillTable() throws SQLException {
    	connect_func();  
    	statement = connect.createStatement();
    	
    	
    	String sql0 = "INSERT INTO Users(email, password) VALUES('root', 'pass1234')"; 
    	String sql1 = "INSERT INTO Users(email, password, firstName, lastName, gender, birthday, numOfFollowers, numOfFollowings) VALUES('jhalpert@email.com', 'pass',  'Jim', 'Halpert' , 'M', '1980-1-11', 446, 345)";  
    	String sql2 = "INSERT INTO Users(email, password, firstName, lastName, gender, birthday, numOfFollowers, numOfFollowings) VALUES('mscott@email.com', 'pass',  'Michael', 'Scott' , 'M', '1970-2-10' , 234, 23456)"; 
    	String sql3 = "INSERT INTO Users(email, password, firstName, lastName, gender, birthday, numOfFollowers, numOfFollowings) VALUES('dschrute@email.com', 'pass',  'Dwight', 'Schrute' , 'M', '1967-5-19', 56, 1)";
    	String sql4 = "INSERT INTO Users(email, password, firstName, lastName, gender, birthday, numOfFollowers, numOfFollowings) VALUES('pbeesly@email.com', 'pass',  'Pam', 'Beesly' , 'F', '1979-11-11', 84, 346)";
    	String sql5 = "INSERT INTO Users(email, password, firstName, lastName, gender, birthday, numOfFollowers, numOfFollowings) VALUES('cbratton@email.com', 'pass',  'Creed', 'Bratton' , 'M', '2008-11-17', 2436, 234)"; 
    	String sql6 = "INSERT INTO Users(email, password, firstName, lastName, gender, birthday, numOfFollowers, numOfFollowings) VALUES('kmalone@email.com', 'pass',  'Kevin', 'Malone' , 'M', '2008-6-11', 2467, 4765)";
    	String sql7 = "INSERT INTO Users(email, password, firstName, lastName, gender, birthday, numOfFollowers, numOfFollowings) VALUES('amartin@email.com', 'pass',  'Angela', 'Martin' , 'F', '1967-8-6', 245, 753)";
    	String sql8 = "INSERT INTO Users(email, password, firstName, lastName, gender, birthday, numOfFollowers, numOfFollowings) VALUES('abernard@email.com', 'pass',  'Andy', 'Bernard' , 'M', '2008-11-2', 433, 345)"; 
    	String sql9 = "INSERT INTO Users(email, password, firstName, lastName, gender, birthday, numOfFollowers, numOfFollowings) VALUES('shudson@email.com', 'pass',  'Stanley', 'Hudson' , 'M', '1979-5-11', 77, 45)";
    	String sql10 = "INSERT INTO Users(email, password, firstName, lastName, gender, birthday, numOfFollowers, numOfFollowings) VALUES('kkapoor@email.com', 'pass',  'Kelly', 'Kapoor' , 'M', '1967-4-9', 958, 646)";

    	
    	boolean rowInserted0 = statement.executeUpdate(sql0) > 0;
    	boolean rowInserted1 = statement.executeUpdate(sql1) > 0;
    	boolean rowInserted2 =statement.executeUpdate(sql2) > 0;
    	boolean rowInserted3 =statement.executeUpdate(sql3) > 0;
    	boolean rowInserted4 =statement.executeUpdate(sql4) > 0;
    	boolean rowInserted5 =statement.executeUpdate(sql5) > 0;
    	boolean rowInserted6 =statement.executeUpdate(sql6) > 0;
    	boolean rowInserted7 =statement.executeUpdate(sql7) > 0;
    	boolean rowInserted8 =statement.executeUpdate(sql8) > 0;
    	boolean rowInserted9 =statement.executeUpdate(sql9) > 0;
    	boolean rowInserted10 =statement.executeUpdate(sql10) > 0;
    	
    	 return ( rowInserted0 && rowInserted1 && rowInserted2 && rowInserted3 && rowInserted4 && rowInserted5 
    			 && rowInserted6 && rowInserted7 && rowInserted8 && rowInserted9 && rowInserted10 ); 
    }
    
    public boolean insert(User user) throws SQLException {
    	
    	connect_func();         
		String sql = "INSERT INTO Users(username, password, firstName, lastName, gender, birthday) VALUES(?, ?, ?, ?, ?, ?)"; 
    	
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, user.username);
		preparedStatement.setString(2, user.password);
		preparedStatement.setString(3, user.firstName);
		preparedStatement.setString(4, user.lastName);
		preparedStatement.setString(5, user.gender);
		preparedStatement.setString(6, user.birthday);
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowInserted;
    }  
    
    public User getUserByUsername(String inputUsername) throws SQLException {
    	connect_func();
    	statement = connect.createStatement();
        String sql = "SELECT * FROM Users WHERE username = ?";
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, inputUsername);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null; 
        if (resultSet.next()) {
        	
        	  int id = resultSet.getInt("userID");	 
              String username = resultSet.getString("username");
              String password = resultSet.getString("password");
              String firstName = resultSet.getString("firstName"); 
              String lastName = resultSet.getString("lastName");
              String gender = resultSet.getString("gender");
              String birthday = resultSet.getString("birthday"); 
              user = new User(id, username, password, firstName, lastName, gender, birthday);    
        }

        return user; 

    }       

}




