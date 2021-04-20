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
@WebServlet("/TagsDAO")
public class TagsDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public TagsDAO() throws SQLException{

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
    	
    	String sql4 = "DROP TABLE IF EXISTS Tags";
    	statement.executeUpdate(sql4);
    }
    
    public int createTable() throws SQLException{
        connect_func();
        statement = connect.createStatement();
        String sql5 = "CREATE TABLE IF NOT EXISTS Tags (\r\n" + 
        		"	imgID MEDIUMINT NOT NULL,\r\n" + 
        		"    tag VARCHAR(20) NOT NULL,\r\n" + 
        		"    PRIMARY KEY (imgID, tag),\r\n" + 
        		"    FOREIGN KEY(imgID) REFERENCES Images(imageID)\r\n" + 
        		"); "; 

        int rowsInserted = statement.executeUpdate(sql5);
        if (statement != null) {
               statement.close();
        }
        return rowsInserted;
    }
		
	public boolean fillTable() throws SQLException {
	    connect_func();
	    statement = connect.createStatement();
	    
	    
	    
	    String sql1 = "INSERT INTO Tags(Tag, imgID) VALUES('Sunset', 3)";
	    String sql2 = "INSERT INTO Tags(Tag, imgID) VALUES('Twilight', 5)";
	    String sql3 = "INSERT INTO Tags(Tag, imgID) VALUES('Dawn', 4)";
	    String sql4 = "INSERT INTO Tags(Tag, imgID) VALUES('Dusk', 5)";
	    String sql5 = "INSERT INTO Tags(Tag, imgID) VALUES('Sunrise', 1)";
	    String sql6 = "INSERT INTO Tags(Tag, imgID) VALUES('Nature', 2)";
	    String sql7 = "INSERT INTO Tags(Tag, imgID) VALUES('Mountains', 6)";
	    String sql8 = "INSERT INTO Tags(Tag, imgID) VALUES('Sky', 2)";
	    String sql9 = "INSERT INTO Tags(Tag, imgID) VALUES('Beautiful', 3)";
	    String sql10 = "INSERT INTO Tags(Tag, imgID) VALUES('Panoramic',9)";
	    
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
	
	public String getTagsForImage(int imgID) throws SQLException {
		connect_func();
        String sql = "SELECT tag \r\n" + 
        			"FROM Tags\r\n" + 
        			"WHERE imgID = ?;";
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, imgID);
        
        ResultSet resultSet = preparedStatement.executeQuery();

        String tags = ""; 
        while (resultSet.next()) { 
              tags += "#" + resultSet.getString("tag") + " ";     
        }

        return tags;    
	}
	
	 public boolean insert(String tag, int imgID) throws SQLException {
		
		 try {		  
			  	connect_func();   
				String sql = "INSERT INTO Tags(Tag, imgID) VALUES(?, ?);";  
				preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
				
				
				preparedStatement.setString(1, tag);
		        preparedStatement.setInt(2, imgID);
				
				
		        boolean rowInserted = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
		        return rowInserted;
			  
		  }
	        catch (java.sql.SQLIntegrityConstraintViolationException e) {
	            System.out.println("the image already has that tag.");
	            return false;
	       }
		 
	 }
	 
	 
	 public boolean insertTagList(String[] tagList, int imgID) throws SQLException {
		
		 for(String tag: tagList) {
	    		if(!tag.isEmpty()) {
	    			 boolean rowInserted = this.insert(tag, imgID);
	    			 if(!rowInserted) {
	    				 return false; 
	    			 }
	    		}
	    	}
		return true;
	 }
	 
	  public boolean delete(int imgID) throws SQLException {
	        String sql = "DELETE FROM Tags WHERE imgID = ?"; 
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setInt(1, imgID);
	         
	        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();

	        return rowDeleted;     
	   }

	/*public List<Tag> getView() throws SQLException{
		List<Tag> listTag = new ArrayList<Tag>();
		String sql = "SELECT * FROM topTags";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String tagName = resultSet.getString("tag");

			Tag tag = new Tag(tagName);
			listTag.add(tag);
		}

		resultSet.close();
		disconnect();
		return listTag;
	}*/



}