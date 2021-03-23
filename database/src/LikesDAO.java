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
import java.time.LocalDate;
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
    	String sql4 = "DROP TABLE IF EXISTS Likes";
    	statement.executeUpdate(sql4);
    }
    
    public int createTable() throws SQLException{
        connect_func();
        statement = connect.createStatement();
        String sql5 = "CREATE TABLE IF NOT EXISTS Likes (\r\n" + 
        		"	email VARCHAR(100) NOT NULL,\r\n" + 
        		"    imgID MEDIUMINT NOT NULL, \r\n" + 
        		"    likeDate DATE,\r\n" + 
        		"    PRIMARY KEY(email, imgID),\r\n" + 
        		"    FOREIGN KEY (email) REFERENCES Users(email),\r\n" + 
        		"    FOREIGN KEY (imgID) REFERENCES Images(imageID))";     
        
                  
        int rowsInserted = statement.executeUpdate(sql5);
        if (statement != null) {
               statement.close();
        }
        return rowsInserted;
    }
		
	public boolean fillTable() throws SQLException {
	    connect_func();
	    statement = connect.createStatement();
	    
	    
	    String sql1 = "INSERT INTO Likes(email, imgID, likeDate) VALUES('jhalpert@email.com', 8,  '2007-7-11')";
	    String sql2 = "INSERT INTO Likes(email, imgID, likeDate) VALUES('cbratton@email.com', 2, '2014-7-11')";
	    String sql3 = "INSERT INTO Likes(email, imgID, likeDate) VALUES('kmalone@email.com', 3, '2013-7-05')";
	    String sql4 = "INSERT INTO Likes(email, imgID, likeDate) VALUES('kmalone@email.com', 5, '2007-7-11')";
	    String sql5 = "INSERT INTO Likes(email, imgID, likeDate) VALUES('pbeesly@email.com', 5, '2018-7-14')";
	    String sql6 = "INSERT INTO Likes(email, imgID, likeDate) VALUES('jhalpert@email.com', 9, '2020-7-27')";
	    String sql7 = "INSERT INTO Likes(email, imgID,likeDate) VALUES('dschrute@email.com', 4, '2007-2-18')";
	    String sql8 = "INSERT INTO Likes(email, imgID, likeDate) VALUES('dschrute@email.com', 8, '2007-7-3')";
	    String sql9 = "INSERT INTO Likes(email, imgID, likeDate) VALUES('jhalpert@email.com', 5, '2007-10-11')";
	    String sql10 = "INSERT INTO Likes(email, imgID, likeDate) VALUES('pbeesly@email.com', 1, '2017-7-11')";
	    

	    
	
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
	
	  public boolean insert(String email, int imgID) throws SQLException {
		  try {
				String likeDate = java.time.LocalDate.now().toString();   
				 
			  
			  	connect_func();   
				String sql = "INSERT INTO Likes(email, imgID, likeDate) VALUES(?, ?, ?)";  
				preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
				
				
				preparedStatement.setString(1, email);
		        preparedStatement.setInt(2, imgID);
		        preparedStatement.setString(3, likeDate);
				
				
		        boolean rowInserted = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
		        return rowInserted;
			  
		  }
	        catch (java.sql.SQLIntegrityConstraintViolationException e) {
	            System.out.println("the image is already liked by user.");
	            return false;
	       }

	  }  
	  
	  public boolean delete(String email, int imgID) throws SQLException {
	        String sql = "DELETE FROM Likes WHERE imgID = ? AND email = ? "; 
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setInt(1, imgID);
	        preparedStatement.setString(2, email);
	         
	        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();

	        return rowDeleted;     
	   }
	
	public int getLikesForImage(int imgID) throws SQLException {
		connect_func();
    	statement = connect.createStatement();
        String sql = "SELECT  COUNT(Likes.email) as likesCount\r\n" + 
        			"FROM Likes\r\n" + 
        			"WHERE imgID = ?";
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, imgID);
        
        ResultSet resultSet = preparedStatement.executeQuery();

        int likesCount = -1; 
        if (resultSet.next()) {
        	 
              likesCount = resultSet.getInt("likesCount");     
        }

        return likesCount;    
	}
	
     public int likeCount(int imageid) throws SQLException {
		int count = 0;
		String sql = "SELECT COUNT(likeSwitch) as total FROM Likes WHERE likeSwitch = true AND imageId = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, imageid);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			count = resultSet.getInt("total");
		}

		preparedStatement.close();

		disconnect();
		return count;
	}
     
     
     public boolean isImageLikedByUser(String email, int imgID) throws SQLException {
    	
 		connect_func();
 		
 		String sql = "SELECT  COUNT(email) as isLiked\r\n" + 
 				"FROM Likes\r\n" + 
 				"WHERE email = ? and imgID = ? ";

 		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
 		preparedStatement.setString(1, email);
 		preparedStatement.setInt(2, imgID);
 		
 		ResultSet resultSet = preparedStatement.executeQuery();
 		
 		int isLikedInt = -1; 
 		while (resultSet.next()) {
 			isLikedInt = resultSet.getInt("isLiked");
 		}
 		
 		boolean isLiked; 
 		if(isLikedInt == 0) {
 			isLiked = false; 
 		}
 		else {
 			isLiked = true; 
 		}
 		

 		preparedStatement.close();

 		disconnect();
 		return isLiked;
 	}
	
	
  } 





