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
@WebServlet("/LikesDAO")
public class LikesDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public LikesDAO() throws SQLException{

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
    	String sql0 = "SHOW TABLES LIKE 'Likes'"; 
    	resultSet = statement.executeQuery(sql0);
    	String gotTable = ""; 
    	if (resultSet.next()) {
             gotTable = resultSet.getString("Tables_in_testdb (Likes)");
        }
    	if( !gotTable.isEmpty()) {
    		System.out.println("Likes Table exists!");
    		String sql1 = "ALTER TABLE Likes DROP FOREIGN KEY fk_userID2"; 
    		String sql2 = "ALTER TABLE Likes DROP FOREIGN KEY fk_imgID2";
    		String sql3 = "DROP TABLE IF EXISTS Likes";
    		statement.executeUpdate(sql1);
    		statement.executeUpdate(sql2);
    		statement.executeUpdate(sql3);
    	}
    	else {
    		System.out.println("Table does not exist"); 
    	}
    	
    	
    	String sql4 = "DROP TABLE IF EXISTS Likes";
    	statement.executeUpdate(sql4);
    }
    
    public int createTable() throws SQLException{
        connect_func();
        statement = connect.createStatement();
        String sql5 = "CREATE TABLE IF NOT EXISTS Likes( " +
                  " userID Integer NOT NULL, " +
                  " imgID Integer NOT NULL, " +
                  " CONSTRAINT fk_userID2 FOREIGN KEY (userID) REFERENCES Users(userID), " +
                  " CONSTRAINT fk_imgID2 FOREIGN KEY (imgID) REFERENCES Images(imgID), " +
                  " PRIMARY KEY(userID, imgID)) ";
                  
        int rowsInserted = statement.executeUpdate(sql5);
        if (statement != null) {
               statement.close();
        }
        return rowsInserted;
    }
		
	public boolean fillTable() throws SQLException {
	    connect_func();
	    statement = connect.createStatement();
	    
	    String sql1 = "INSERT INTO Likes(userID, imgID) VALUES(1, 7)";
	    String sql2 = "INSERT INTO Likes(userID, imgID) VALUES(8, 2)";
	    String sql3 = "INSERT INTO Likes(userID, imgID) VALUES(7, 3)"; 
	    String sql4 = "INSERT INTO Likes(userID, imgID) VALUES(7, 5)";
	    String sql5 = "INSERT INTO Likes(userID, imgID) VALUES(10, 5)";
	    String sql6 = "INSERT INTO Likes(userID, imgID) VALUES(5, 9)";
	    String sql7 = "INSERT INTO Likes(userID, imgID) VALUES(3, 4)";
	    String sql8 = "INSERT INTO Likes(userID, imgID) VALUES(2, 8)";
	    String sql9 = "INSERT INTO Likes(userID, imgID) VALUES(4, 5)";
	    String sql10 = "INSERT INTO Likes(userID, imgID) VALUES(8, 10)";
	    
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
	
    }     