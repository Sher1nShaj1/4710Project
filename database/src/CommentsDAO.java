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
@WebServlet("/CommentsDAO")
public class CommentsDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public CommentsDAO() throws SQLException{

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
//        String sql0 = "SHOW TABLES LIKE 'Comments'"; 
//        resultSet = statement.executeQuery(sql0);
//        String gotTable = ""; 
//        if (resultSet.next()) {
//            gotTable = resultSet.getString("Tables_in_testdb (Comments)");
//        }
//        if( !gotTable.isEmpty()) {
//    		    String sql1 = "ALTER TABLE Comments DROP FOREIGN KEY fk_userID1"; 
//    		    String sql2 = "ALTER TABLE Comments DROP FOREIGN KEY fk_imgID";
//    		    String sql3 = "DROP TABLE IF EXISTS Comments";
//    		    statement.executeUpdate(sql1);
//    		    statement.executeUpdate(sql2);
//    		    statement.executeUpdate(sql3);
//        }
      
    	
    	
    String sql4 = "DROP TABLE IF EXISTS Comments";
	statement.executeUpdate(sql4);
    }
    
    public int createTable() throws SQLException{
        connect_func();
        statement = connect.createStatement();
        String sql5 = "CREATE TABLE IF NOT EXISTS Comments (\r\n" + 
        		"	email VARCHAR(100) NOT NULL,\r\n" + 
        		"    imgID MEDIUMINT NOT NULL,\r\n" + 
        		"    description VARCHAR(500),\r\n" + 
        		"    PRIMARY KEY(email, imgID),\r\n" + 
        		"    FOREIGN KEY(email) REFERENCES Users(email),\r\n" + 
        		"    FOREIGN KEY(imgID) REFERENCES Images(imageID))";
        
                  
        int rowsInserted = statement.executeUpdate(sql5);
        if (statement != null) {
               statement.close();
        }
        return rowsInserted;
    }
		
	public boolean fillTable() throws SQLException {
	    connect_func();
	    statement = connect.createStatement();
	    

		String sql1 = "INSERT INTO Comments(email, imgID, Description) VALUES('jhalpert@email.com', 2, 'Wow')"; 
		String sql2 = "INSERT INTO Comments(email, imgID, Description) VALUES('mscott@email.com', 3, 'Beautiful')";
		String sql3 = "INSERT INTO Comments(email, imgID, Description) VALUES('amartin@email.com', 7, 'Is this real')";
		String sql4 = "INSERT INTO Comments(email, imgID, Description) VALUES('mscott@email.com', 6, 'stunning')";
		String sql5 = "INSERT INTO Comments(email, imgID, Description) VALUES('jhalpert@email.com', 7, 'amazing')";
		String sql6 = "INSERT INTO Comments(email, imgID, Description) VALUES('amartin@email.com', 6, 'breathtaking')";
		String sql7 = "INSERT INTO Comments(email, imgID, Description) VALUES('mscott@email.com', 7,  'cannot believe my eyes')";
		String sql8 = "INSERT INTO Comments(email, imgID, Description) VALUES('jhalpert@email.com', 3, 'where is this')";
		String sql9 = "INSERT INTO Comments(email, imgID, Description) VALUES( 'abernard@email.com', 1, 'Did you take this')";
		String sql10 = "INSERT INTO Comments(email, imgID, Description) VALUES( 'abernard@email.com', 6, 'no way')"; 
			    
	 
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