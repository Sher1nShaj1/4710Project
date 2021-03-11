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
@WebServlet("/FollowDAO")
public class FollowDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public FollowDAO() throws SQLException {
		
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
//    	String sql0 = "SHOW TABLES LIKE 'Follows'"; 
//    	resultSet = statement.executeQuery(sql0);
//    	String gotTable = ""; 
//    	if (resultSet.next()) {
//             gotTable = resultSet.getString("Tables_in_testdb (Follows)");
//        }
//    	if( !gotTable.isEmpty()) {
//    		String sql1 = "ALTER TABLE Follows DROP FOREIGN KEY userID"; 
//    		String sql2 = "ALTER TABLE Follows DROP FOREIGN KEY followerID";
//    		String sql3 = "DROP TABLE IF EXISTS Follows";
//    		statement.executeUpdate(sql1);
//    		statement.executeUpdate(sql2);
//    		statement.executeUpdate(sql3);
//    	}
    	
    	
    	String sql4 = "DROP TABLE IF EXISTS Follows";
    	statement.executeUpdate(sql4);
    }
         
    public int createTable() throws SQLException {
    	connect_func();   
    	statement = connect.createStatement();
    	String sql5 = "CREATE TABLE IF NOT EXISTS Follows (\r\n" + 
    			"	followingEmail VARCHAR(100) NOT NULL,\r\n" + 
    			"    followerEmail VARCHAR(100) NOT NULL,\r\n" + 
    			"    PRIMARY KEY(followingEmail, followerEmail),\r\n" + 
    			"    FOREIGN KEY(followingEmail) REFERENCES Users(email),\r\n" + 
    			"    FOREIGN KEY (followerEmail) REFERENCES Users (email))"; 
    	
    	
    	
    	int rowsInserted = statement.executeUpdate(sql5);
    	 if (statement != null) {
    	        statement.close();
    	 }
        return rowsInserted;
    }
    
    public boolean fillTable() throws SQLException {
    	connect_func();  
    	statement = connect.createStatement();
    	

    	String sql1 = "INSERT INTO Follows(followingEmail, followerEmail) VALUES('jhalpert@email.com', 'cbratton@email.com')";
    	String sql2 = "INSERT INTO Follows(followingEmail, followerEmail)  VALUES('pbeesly@email.com', 'kmalone@email.com')";
    	String sql3 = "INSERT INTO Follows(followingEmail, followerEmail)  VALUES('shudson@email.com', 'abernard@email.com')";
    	String sql4 = "INSERT INTO Follows(followingEmail, followerEmail)  VALUES('kkapoor@email.com', 'amartin@email.com')";
    	String sql5 = "INSERT INTO Follows(followingEmail, followerEmail)  VALUES('kkapoor@email.com', 'abernard@email.com')";
    	String sql6 = "INSERT INTO Follows(followingEmail, followerEmail)  VALUES('shudson@email.com', 'pbeesly@email.com')";
    	String sql7 = "INSERT INTO Follows(followingEmail, followerEmail)  VALUES('amartin@email.com','mscott@email.com' )";
    	String sql8 = "INSERT INTO Follows(followingEmail, followerEmail)  VALUES('mscott@email.com', 'kmalone@email.com')";
    	String sql9 = "INSERT INTO Follows(followingEmail, followerEmail)  VALUES('cbratton@email.com', 'mscott@email.com')";
    	String sql10 = "INSERT INTO Follows(followingEmail, followerEmail)  VALUES('kkapoor@email.com', 'cbratton@email.com')"; 
    	 
    	
    	
    		
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
    	
    	 return ( rowInserted1 && rowInserted2 && rowInserted3 && rowInserted4 && rowInserted5 
    			 && rowInserted6 && rowInserted7 && rowInserted8 && rowInserted9 && rowInserted10 ); 
    }
    
   
    /*
	
	resultSet = SHOW TABLES LIKE 'Users';
	if ( resultset != null)
		- table exists
		 - drop foreign key constraints 
		 - drop table 
		 
	- create table 
*/
     
          

}
