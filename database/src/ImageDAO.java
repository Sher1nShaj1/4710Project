import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
import java.sql.Timestamp;
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
        String sql5 = "CREATE TABLE IF NOT EXISTS Images (\r\n" + 
        		"			imageID MEDIUMINT AUTO_INCREMENT NOT NULL,\r\n" + 
        		"        	url VARCHAR(2083) NOT NULL,\r\n" + 
        		"        	description VARCHAR(100) NOT NULL,\r\n" + 
        		"            postUser VARCHAR(100) NOT NULL, \r\n" + 
        		"            postDate DATE,\r\n" + 
        		"            postTime DATETIME,\r\n" + 
        		"            PRIMARY KEY( imageID),\r\n" + 
        		"            FOREIGN KEY (postUser) references Users(email))" ;
        
        int rowsInserted = statement.executeUpdate(sql5);
        if (statement != null) {
               statement.close();
        }
        return rowsInserted;
    }
		
	public boolean fillTable() throws SQLException {
	    connect_func();
	    statement = connect.createStatement();
	    
	    String sql0 = "INSERT INTO Images(url, description, postUser, postDate, postTime) VALUES('https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/sunset-quotes-21-1586531574.jpg','twilight', 'jhalpert@email.com', '2004-11-11' , '2004-11-11 04:23:44')"; 
	    String sql1 = "INSERT INTO Images(url, description, postUser, postDate, postTime) VALUES('https://ogden_images.s3.amazonaws.com/www.observertoday.com/images/2020/08/29003327/SUNSET-scaled.jpg','dusk', 'mscott@email.com', '2012-5-11' , '2012-5-11 05:23:44')";
		String sql2 = "INSERT INTO Images(url, description, postUser, postDate, postTime) VALUES('https://upload.wikimedia.org/wikipedia/commons/a/a4/Anatomy_of_a_Sunset-2.jpg','cloudscape', 'dschrute@email.com', '2004-5-11' , '2004-5-11 04:24:44')"; 
		String sql3 = "INSERT INTO Images(url, description, postUser, postDate, postTime) VALUES('https://i.pinimg.com/originals/66/05/a1/6605a1be5ff2358644d11520ae4b77f8.jpg','horizon', 'pbeesly@email.com', '2003-8-11' , '2003-8-11 08:55:44')";
		String sql4 = "INSERT INTO Images(url, description, postUser, postDate, postTime) VALUES('https://png.pngtree.com/thumb_back/fh260/background/20191026/pngtree-palm-beach-sunset-image_320421.jpg','sunset', 'cbratton@email.com', '2006-11-11' , '2006-11-11 23:23:35')";
		String sql5 = "INSERT INTO Images(url, description, postUser, postDate, postTime) VALUES('https://earthsky.org/upl/2013/09/sunrise-red-sea-Graham-Telford-e1489764712368.jpg','sunbeam', 'kmalone@email.com', '2007-7-11' , '2007-7-11 12:21:44')";
		String sql6 = "INSERT INTO Images(url, description, postUser, postDate, postTime)  VALUES('https://www.visitaparadise.com/wp-content/themes/yootheme/cache/sunset-d863fdd4.jpeg','blue reflection', 'amartin@email.com', '1999-1-6' , '1999-1-6 04:03:53')";
		String sql7 = "INSERT INTO Images(url, description, postUser, postDate, postTime) VALUES('https://llandscapes-10674.kxcdn.com/wp-content/uploads/2015/01/6198521760_aa86027669_z.jpg','snowcapped mountain', 'amartin@email.com', '2020-11-23' , '2020-11-23 11:32:34')";
		String sql8 = "INSERT INTO Images(url, description, postUser, postDate, postTime) VALUES('https://www.araioflight.com/wp-content/uploads/2019/12/Wild-Beautiful-sunset-in-Africa-with-animals-safari.jpg', 'safari', 'abernard@email.com', '2008-11-11' , '2008-11-11 04:23:24')"; 
		String sql9 = "INSERT INTO Images(url, description, postUser, postDate, postTime)  VALUES('https://cdn3.dpmag.com/2019/10/shutterstock_1239834655.jpg','beauty in nature', 'abernard@email.com', '2019-11-02' , '2019-11-02 14:35:09')";
		String sql10 = "INSERT INTO Images(url, description, postUser, postDate, postTime) \r\n" + 
				" 	VALUES('https://i.pinimg.com/originals/66/05/a1/6605a1be5ff2358644d11520ae4b77f8.jpg','awesome',\r\n" + 
				"			'jhalpert@email.com', CURDATE() , NOW())"; 
			  
	    
		boolean rowInserted0 =statement.executeUpdate(sql0) > 0;
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
    			 && rowInserted6 && rowInserted7 && rowInserted8 && rowInserted9 && rowInserted0 && rowInserted10); 
	}
	
	public List<Image> getImagesPostedByUser(User user) throws SQLException {
		List<Image> imageList = new ArrayList<Image>();        
        String sql = String.format( "SELECT imageID, url, description, postTime\r\n" + 
        							"FROM Images, Users\r\n" + 
        							"WHERE Users.email = ? && postUser = Users.email  ORDER BY postTime DESC");
        
         
        connect_func();      
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, user.email);
        
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {

        	
            int imageID = resultSet.getInt("imageID");
            String url = resultSet.getString("url");
            String description = resultSet.getString("description");
            String postTime = resultSet.getString("postTime");
             
            Image image = new Image(imageID, url, description, user, postTime); 
            imageList.add(image);
        }      
        disconnect();        
        return imageList;
		
	}
	
	public boolean insert(String email, Image image) throws SQLException {
		
    	connect_func(); 
    	image.postDate = java.time.LocalDate.now().toString();
    	image.postTime = java.time.LocalDateTime.now().toString();
    	
    	
    	String sql = "INSERT INTO Images(url, description, postUser, postDate, postTime) VALUES(?, ?, ?, ?, ?)"; 
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, image.url);
		preparedStatement.setString(2, image.description);
		preparedStatement.setString(3, email);
		preparedStatement.setString(4, image.postDate);
		preparedStatement.setString(5, image.postTime);
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        
        
        preparedStatement.close();
        return rowInserted;		
	}
	
	 public boolean update(Image image) throws SQLException {
	        String sql = "UPDATE Images\r\n" + 
		        		"SET url = ? ,\r\n" + 
		        		"	description = ? \r\n" + 
		        		"WHERE imageID = ? ";
	        connect_func();
	        
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1, image.url);
	        preparedStatement.setString(2, image.description);
	        preparedStatement.setInt(3, image.imgID);
	        
	         
	        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();

	        return rowUpdated;     
	 }
	 
    public boolean delete(int imgID) throws SQLException {
        String sql = "DELETE FROM Images WHERE imageID =  ? "; 
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, imgID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();

        return rowDeleted;     
    }
	
	
	public int getLastestPostImageID(String email) throws SQLException{
		
		connect_func(); 
    	
    	String sql = "SELECT imageID " + 
		    			"FROM images " + 
		    			"WHERE postUser = ? " + 
		    			"ORDER BY posttime DESC " + 
		    			"LIMIT 1"; 
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);

		ResultSet resultSet = preparedStatement.executeQuery();
		
		int imageID = -1; 
        while (resultSet.next()) {
            imageID = resultSet.getInt("imageID");
        }      
        disconnect();
        preparedStatement.close();
        return imageID;	
	}
	
	
	
	public Image getImageByID(int imgID, User user) throws SQLException{
		
		connect_func(); 
    	
    	String sql = "SELECT * FROM Images WHERE imageID = ? AND postUser = ?"; 
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, imgID);
		preparedStatement.setString(2, user.email);

		ResultSet resultSet = preparedStatement.executeQuery();
		
		Image image = null; 
        while (resultSet.next()) {
             String url = resultSet.getString("url");
             String description = resultSet.getString("description");
             String postTime = resultSet.getString("postTime");
              
             image = new Image(imgID, url, description, user, postTime); 
        }      
        disconnect();
        preparedStatement.close();
        return image;	
	}

	/*public List<Image> getRootView(String view) throws SQLException{
		List<Image> images = new ArrayList<Image>();
		connect_func();

		if(view.isEmpty()) {
			view = "image";
		}

		String sql = "SELECT *, COUNT(L.imageID) as likesCount from " + view + " I " +
				"left join Likes L on I.imageID = L.imageID " +
				"GROUP by I.imageID " +
				"ORDER by ts DESC;";

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();

		while(resultSet.next()) {
			int imageID = resultSet.getInt("imageID");
			String url = resultSet.getString("url");
			String description = resultSet.getString("description");
			String postUser = resultSet.getString("postUser");
			String postTime = resultSet.getString("postTime");
			int likesCount = resultSet.getInt("likesCount");

			Image image = new Image(imageID, url, description, postUser, postTime);
			images.add(image);
		}
		disconnect();
		return images;

	}*/
	
	public List<Image> getFeed(String email) throws SQLException{
		List<Image> images = new ArrayList<Image>();
		
		connect_func();
		
		String sql = "SELECT *\r\n" + 
					"FROM Images I\r\n" + 
					"WHERE I.postUser = ? or postUser in (\r\n" + 
					"	SELECT followerEmail \r\n" + 
					"	FROM Follows\r\n" + 
					"	WHERE followingEmail = ?\r\n" + 
					")\r\n" + 
					"ORDER BY I.postTime DESC"; 

		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, email);
		
		ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {

            int imageID = resultSet.getInt("imageID");
            String url = resultSet.getString("url");
            String description = resultSet.getString("description");
            String postUser = resultSet.getString("postUser"); 
            String postTime = resultSet.getString("postTime");
             
            Image image = new Image(imageID, url, description, postUser, postTime); 
            images.add(image);
        }      
        
		disconnect();
		return images;
	}
	/*
	 * Cool Images: the images that are liked by at least 5 users. 
	 */
	
	public List<Image> getCoolImages() throws SQLException{
		List<Image> images = new ArrayList<Image>();
		
		connect_func();
		
		String sql = "select * \r\n" + 
				"from images\r\n" + 
				"where imageID in (\r\n" + 
				"	select imgID\r\n" + 
				"	from likes\r\n" + 
				"	group by imgID\r\n" + 
				"	having count(*) >=5\r\n" + 
				")"; 

		preparedStatement = connect.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {

            int imageID = resultSet.getInt("imageID");
            String url = resultSet.getString("url");
            String description = resultSet.getString("description");
            String postUser = resultSet.getString("postUser"); 
            String postTime = resultSet.getString("postTime");
             
            Image image = new Image(imageID, url, description, postUser, postTime); 
            images.add(image);
        }      
        
		disconnect();
		return images;
	}
	
	/*
	 * New Images: those images that are just posted TODAY. 
	 */
	
	public List<Image> getNewImages() throws SQLException{
		List<Image> images = new ArrayList<Image>();
		
		connect_func();
		
		String sql = "select * \r\n" + 
				"from images\r\n" + 
				"where postDate = CURDATE()";  

		preparedStatement = connect.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {

            int imageID = resultSet.getInt("imageID");
            String url = resultSet.getString("url");
            String description = resultSet.getString("description");
            String postUser = resultSet.getString("postUser"); 
            String postTime = resultSet.getString("postTime");
             
            Image image = new Image(imageID, url, description, postUser, postTime); 
            images.add(image);
        }      
        
		disconnect();
		return images;
	}
	
	
	/*
	 *  Viral Images: top 3 images who received the most number of likes, from the most to the least. 
	 */
	public List<Image> getViralImages() throws SQLException{
		List<Image> images = new ArrayList<Image>();
		
		connect_func();
		
		String sql = "select I.*\r\n" + 
				"from likes L, images I\r\n" + 
				"where L.imgID = I.imageID\r\n" + 
				"group by L.imgID \r\n" + 
				"order by count(*) desc\r\n" + 
				"limit 3";  

		preparedStatement = connect.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {

            int imageID = resultSet.getInt("imageID");
            String url = resultSet.getString("url");
            String description = resultSet.getString("description");
            String postUser = resultSet.getString("postUser"); 
            String postTime = resultSet.getString("postTime");
             
            Image image = new Image(imageID, url, description, postUser, postTime); 
            images.add(image);
        }      
        
		disconnect();
		return images;
	}

	public List<Image> poorImages() throws SQLException {
		System.out.println("Inside poorImages() in UserDAO");
		List<Image> listImage = new ArrayList<Image>();
		connect_func();
		String sql1 = "SELECT * FROM images I "
				+ "WHERE I.imageID NOT IN ("
				+ "(SELECT L.imageID "
				+ "FROM likes L, images I "
				+ "WHERE L.imageID = I.imageID) "
				+ "UNION "
				+ "(SELECT C.imageID "
				+ "FROM comments C, images I "
				+ "WHERE C.imageID = I.imageID))";
		statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql1);

		while(resultSet.next()) {
			int imageID = resultSet.getInt("imageID");
			String url = resultSet.getString("url");
			String description = resultSet.getString("description");
			String postUser = resultSet.getString("postUser");
			String postTime = resultSet.getString("postTime");
			Image newImage = new Image(imageID, url, description, postUser, postTime);
			listImage.add(newImage);
			System.out.println("Added an image to listImage in poorImages()");
		}
		System.out.println("End poorImages() in UserDAO");
		return listImage;
	}

	public List<String> getTagsFromSingleImage(int imageid) throws SQLException {
		System.out.println("Inside getTagsFromSingleImage");
		List<String> listTags = new ArrayList<String>();
		connect_func();
		String sql1 = "SELECT tag FROM tags WHERE imageID = ?";
		preparedStatement = connect.prepareStatement(sql1);
		preparedStatement.setInt(1, imageid);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			System.out.println("Got tag, adding to list");
			listTags.add(resultSet.getString("tag"));
		}
		return listTags;
	}

	public List<String> topTags() throws SQLException {
		List<String> listTags = new ArrayList<String>();
		String sql = "SELECT tag FROM tags GROUP BY tag HAVING count(imageID) >= 3";
		connect_func();
		statement =  (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			listTags.add(resultSet.getString("tag"));
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listTags;
	}
	
	
	
		
} 





 
 
        
        




