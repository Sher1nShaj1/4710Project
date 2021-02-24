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
@WebServlet("/ImageDAO")
public class ImageDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public ImageDAO() throws SQLException{

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
    	String sql4 = "DROP TABLE IF EXISTS Images";
    	statement.executeUpdate(sql4);
    }
    
    public int createTable() throws SQLException{
        connect_func();
        statement = connect.createStatement();
        String sql5 = "CREATE TABLE IF NOT EXISTS Images( " +
                  " imgID INT AUTO_INCREMENT UNIQUE NOT NULL, " +
                  " url VARCHAR(1000) NOT NULL, " +
                  " description VARCHAR(1000)) ";
        int rowsInserted = statement.executeUpdate(sql5);
        if (statement != null) {
               statement.close();
        }
        return rowsInserted;
    }
		
	public boolean fillTable() throws SQLException {
	    connect_func();
	    statement = connect.createStatement();
	    
	    String sql1 = "INSERT INTO Images(url, description) VALUES('https://www.everypixel.com/image-1848701391904308651','twilight')";
	    String sql2 = "INSERT INTO Images(url, description) VALUES('https://www.everypixel.com/image-4605505135290939668','dusk')";
	    String sql3 = "INSERT INTO Images(url, description) VALUES('https://www.everypixel.com/image-3903131704398778801','cloudscape')";
	    String sql4 = "INSERT INTO Images(url, description) VALUES('https://www.everypixel.com/image-610426762433763266','horizon')";
	    String sql5 = "INSERT INTO Images(url, description) VALUES('https://www.everypixel.com/image-1914246763255025051','mountains')";
	    String sql6 = "INSERT INTO Images(url, description) VALUES('https://www.everypixel.com/image-2838789978965726950','sunbeam')";
	    String sql7 = "INSERT INTO Images(url, description) VALUES('https://www.everypixel.com/image-1378098419373516446','blue reflection')";
	    String sql8 = "INSERT INTO Images(url, description) VALUES('https://www.everypixel.com/image-15481317529490480435','snowcapped mountain')";
	    String sql9 = "INSERT INTO Images(url, description) VALUES('https://www.everypixel.com/image-714408075288199227','beauty in nature')";
	    String sql10 = "INSERT INTO Images(url, description) VALUES('https://www.everypixel.com/image-10169022796335635107','beauty in nature')";
	    
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