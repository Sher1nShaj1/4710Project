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
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */


public class ControlServlet extends HttpServlet   {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO; 
    private FollowDAO followDAO; 
    private ImageDAO imageDAO;
    private CommentsDAO commentsDAO; 
    private LikesDAO likeDAO;
    private TagsDAO tagsDAO;
	private ImageTagDAO imageTagDAO;
	private String currentUser;
  
    
    public void init(){
 
        try {
			userDAO = new UserDAO();
			followDAO = new FollowDAO();
			imageDAO = new ImageDAO();
			followDAO = new FollowDAO();
			commentsDAO = new CommentsDAO(); 
			likesDAO = new LikesDAO(); 
			tagsDAO = new TagDAO();
			imageTagDAO = new ImageTagDAO();
			currentUser = "";
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
            case "/login":
            	showLoginPage(request, response);
            	break; 
            case "/loginUser":
            	loginUser(request, response); 
            	break; 
            case "/signup":
            	showSignupPage(request, response);
            	break; 
            case "/createUser":
            	createUser(request, response);
            	break;
            case "/initializeDatabase":
            	initializeDatabase(request, response); 
            	break;
            case "/community":
            	listUsers(request,response);
            	break;
            case "/feed":
            	feedPage(request,response);
            	break;
			case "/follow":
				follow(request,response);
				break;
			case "/home":
            	showHome(request, response); 
            	break; 
            case "/userProfile":
            	showUserProfile(request, response); 
            	break;
            case "/userProfile/createPost":
            	showCreatePostForm(request, response); 
            	break;
            case "/userProfile/postImage":
            	postImage(request, response); 
            	break;
            case "/userProfile/deletePost":
            	showDeletePostForm(request, response); 
            	break;
            case "/deletePost":
            	deletePost(request, response); 
            	break;
            case "/userProfile/editPost":
            	showEditPostForm(request, response); 
            	break;
            case "/editPost":
            	editPost(request, response); 
            	break; 
        	
             
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
  private void likeImage(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
    	//String url = request.getParameter("url");
    	int id = Integer.parseInt(request.getParameter("id"));
    	likesDAO.insert(new Like(currentUser,id));
    	feedPage(request,response);
    }
    
    private void dislikeImage(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
    	//String url = request.getParameter("url");
    	int id = Integer.parseInt(request.getParameter("id"));
    	likesDAO.delete(new Like(currentUser,id));
    	feedPage(request,response);
    }	
	
   private void postImage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        
        System.out.println("user in post Image: " + currentUser); 
        
		String url = request.getParameter("url");
        String tags = request.getParameter("tags");
    	String desc = request.getParameter("description");
    	

    	Image image = new Image(url, currentUser, desc);
		int imageId = -1;
		int tagId = -1;
		imageDAO.insert(image);
		imageId = imageDAO.retrieveImageId(currentUser, url);

    	System.out.println("in post Image: " + image.toString()); 
    	boolean isPosted = imageDAO.insert(currentUser.email, image);

		String tagsInput = request.getParameter("tags");
		if(!tagsInput.isEmpty()) {
			String[] tags = tagsInput.split(",");
			for(String s: tags) {
				tagId = tagsDAO.checkExists(new Tag(s));
				System.out.println(imageId + " " + tagId + " " + s);
				imageTagDAO.insert(new ImageTag(imageId, tagId));
			}
		}
		feedPage(request, response);
		
    	if(isPosted) {
    		image.imgID = imageDAO.getLastestPostImageID(currentUser.email);
    		String[] tagList = tags.split("#");
        	boolean tagsInserted = tagsDAO.insertTagList(tagList, image.imgID);
    	}
    	
    	
    	response.sendRedirect("../userProfile");  
	}

	private void showCreatePostForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("CreatePost.jsp");       
        dispatcher.forward(request, response);
	}
	
   private void showDeletePostForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
		String imgIDString = request.getParameter("imgID"); 
		int imgID = Integer.parseInt(imgIDString);
		 
		Image image = imageDAO.getImageByID(imgID, currentUser); 
		image.tags = tagsDAO.getTagsForImage(imgID); 
	   
	    RequestDispatcher dispatcher = request.getRequestDispatcher("DeletePost.jsp"); 
	    request.setAttribute("image", image);
        dispatcher.forward(request, response);
	}
   
   private void deletePost(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
	   String imgIDString = request.getParameter("imgID"); 
	   int imgID = Integer.parseInt(imgIDString);
	   
	   boolean areTagsDeleted = tagsDAO.delete(imgID); 
	   boolean areCommentsDeleted = commentsDAO.delete(imgID);  
	   boolean areLikesDeleted = likesDAO.delete(imgID); 
	   	
  
	   boolean isImgDeleted = imageDAO.delete(imgID); 
	   
	   response.sendRedirect("userProfile"); 
	}

	
   
	private void showEditPostForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, SQLException {
		HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
		String imgIDString = request.getParameter("imgID"); 
		int imgID = Integer.parseInt(imgIDString);
		 
		Image image = imageDAO.getImageByID(imgID, currentUser); 
		image.tags = tagsDAO.getTagsForImage(imgID); 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditPost.jsp");  
		request.setAttribute("image", image);
        dispatcher.forward(request, response);
	}
	
	private void editPost(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		String imgIDString = request.getParameter("imgID");
		int imgID = Integer.parseInt(imgIDString); 
		String url = request.getParameter("url");
        String tags = request.getParameter("tags");
    	String description = request.getParameter("description");
    	
    	Image image = new Image(imgID, url, description); 
    	imageDAO.update(image); 
    	
    	tagsDAO.delete(imgID);
    	String[] tagList = tags.split("#");
    	boolean tagsInserted = tagsDAO.insertTagList(tagList, imgID); 
    	
    	
    	response.sendRedirect("userProfile"); 
	}

	private void showUserProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		HttpSession session = request.getSession(); 
        User currentUser = (User) session.getAttribute("currentUser"); 
        
        
		List<Image> postedImages = imageDAO.getImagesPostedByUser(currentUser);
		int likeCount = 0;
		String tags = ""; 
		
		for(Image image: postedImages) { 
			likeCount = likesDAO.getLikesForImage(image.imgID);
			tags = tagsDAO.getTagsForImage(image.imgID); 
			image.setLikesCount(likeCount);
			image.setTags(tags);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserProfile.jsp"); 
		request.setAttribute("postedImages", postedImages);
        dispatcher.forward(request, response);
		
	}


	private void showHome(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		HttpSession session = request.getSession();
         
        User currentUser = (User) session.getAttribute("currentUser");
    
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");       
        dispatcher.forward(request, response);
	}

	private void initializeDatabase(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
		HttpSession session = request.getSession();  
        User currentUser = (User) session.getAttribute("currentUser");
		
        if(currentUser.email.equals("root")){
    		
	   		 tagsDAO.dropTable();
	   		 commentsDAO.dropTable();  
	   		 likesDAO.dropTable();
	   		 followDAO.dropTable();
	   		 imageDAO.dropTable();
	   		 userDAO.dropTable();
	   		  
	   		 
	   		 
	   		 int rowsCreatedUsers = userDAO.createTable();
	   		 int rowsCreatedImages = imageDAO.createTable();
	   		 int rowsCreatedTags = tagsDAO.createTable(); 
	   		 int rowsCreatedComments = commentsDAO.createTable();
	   		 int rowsCreatedLikes = likesDAO.createTable();
	   		 int rowsCreatedFollow = followDAO.createTable();
	   	 
	   
	   		 
	   		 
	   		 userDAO.fillTable(); 
	   		 imageDAO.fillTable();
	   		 tagsDAO.fillTable(); 
	   		 commentsDAO.fillTable();
	   		 likesDAO.fillTable(); 
	   		 followDAO.fillTable(); 
   		
        }  
        
        
        
        response.sendRedirect("home");
        
	}

	private void showLoginPage(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException{
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");       
        dispatcher.forward(request, response);
	}
    
    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
    	
        String emailInput = request.getParameter("email");
        String passwordInput = request.getParameter("password");
        
        User currentUser = userDAO.getUserByEmail(emailInput); 
        
         
        if( currentUser == null || !passwordInput.equals(currentUser.password) ) { // user not found
        	RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            request.setAttribute("loginError", "Invalid username or password. Try again.");
            dispatcher.forward(request, response);
            
        }
        else {
        	HttpSession session = request.getSession();
            session.setAttribute("currentUser", currentUser);  
            System.out.println("Session ID: " + session.getId());
            System.out.println("Creation Time: " + new Date(session.getCreationTime()));
            System.out.println("Last Accessed Time: " + new Date(session.getLastAccessedTime()));
            response.sendRedirect("home");  
        }  
    }

    private void showSignupPage(HttpServletRequest request, HttpServletResponse response) 
    		 throws SQLException, IOException, ServletException{
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Signup.jsp");       
        dispatcher.forward(request, response);
	}
    
    private void createUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException{

    	String email = request.getParameter("email");
        String password = request.getParameter("password");
    	String confirmPassword = request.getParameter("confirmPassword");
        String firstName = request.getParameter("firstName");
    	String lastName = request.getParameter("lastName");
    	String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        System.out.println("password: " + password + ", confirmPass: " + confirmPassword);
        
        
        if( !password.equals(confirmPassword)){ 
        	RequestDispatcher dispatcher = request.getRequestDispatcher("Signup.jsp");
            request.setAttribute("passwordError", "Passwords did not match. Try again.");
            request.setAttribute("email", email);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("gender", gender);
            request.setAttribute("birthday", birthday);
            dispatcher.forward(request, response);
            
        }
        else if ( userDAO.getUserByEmail(email) != null ){ 
        	System.out.println("gender: " + gender); 
        	RequestDispatcher dispatcher = request.getRequestDispatcher("Signup.jsp");
            request.setAttribute("usernameInvalidError", "That username is taken. Try another.");
            request.setAttribute("password", password);
            request.setAttribute("confirmPassword", confirmPassword);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("gender", gender);
            request.setAttribute("birthday", birthday);
            dispatcher.forward(request, response);
            
        }
        else {
        	
        	User currentUser = new User(email, password, firstName, lastName, gender, birthday);
        	HttpSession session = request.getSession();
            session.setAttribute("currentUser", currentUser);
        	userDAO.insert(currentUser); 
    	    System.out.println(currentUser.toString()); 
            response.sendRedirect("home");      
        }
        
    	}
    private void feedPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		List<Image> images = imageDAO.getFeed(currentUser);
		for (int i = 0; i < images.size(); i = i+1) {
			Image temp = images.get(i);
			temp.setLikeCount(likesDAO.likeCount(temp.getImageId()));
			images.set(i, temp);
		}
		request.setAttribute("username", currentUser);
		request.setAttribute("listImages", images);
		request.getRequestDispatcher("feedPage.jsp").forward(request,response);
	}

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		List<Account> users = userDAO.listAllPeople();
		List<Boolean> follows = followDAO.followList(currentUser);
		request.setAttribute("userList", users);
		request.setAttribute("followList", follows);
		request.getRequestDispatcher("CommunityPage.jsp").forward(request, response);
	}


    private void follow(HttpServletRequest request, HttpServletResponse response) throws SQLException,ServletException, IOException{
		boolean status = Boolean.parseBoolean(request.getParameter("status"));

		Follow followObj = new Follow(currentUser,request.getParameter("email"));
		try {
			if(status) {
				followDAO.delete(followObj);
			}
			else {
				followDAO.insert(followObj);
			}
			listUsers(request,response);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			listUsers(request,response);
		}
	}


}
    
 
