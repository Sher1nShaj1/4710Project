import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
import java.util.TreeMap;

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
	private LikesDAO likesDAO; 
  
    
    public void init(){
 
        try {
			userDAO = new UserDAO();
			followDAO = new FollowDAO();
			imageDAO = new ImageDAO();
			followDAO = new FollowDAO();
			commentsDAO = new CommentsDAO(); 
			likesDAO = new LikesDAO(); 
			tagsDAO = new TagsDAO();
			
			
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
            case "/community":
            	listUsers(request,response);
            	break;
            case "/search":
            	search(request,response);
            	break;
            case "/feed":
            	feedPage(request,response);
            	break;
            case "/likePost":
            	likePost(request,response);
            	break;
            case "/dislikePost":
            	dislikePost(request,response);
            	break;
			case "/follow":
				follow(request,response);
				break;
			case "/unfollow":
				unfollow(request,response);
				break;
			case "/admin":
				showAdminPage(request, response); 
				break; 
			case "/admin/coolImages":
				showCoolImages(request, response); 
				break; 
			case "/admin/newImages":
				showNewImages(request, response); 
				break;
			case "/admin/viralImages":
				showViralImages(request, response); 
				break;
			case "/admin/topUsers":
				showTopUsers(request, response); 
				break;
			case "/admin/popularUsers":
				showPopularUsers(request, response); 
				break;
				case "/admin/commonUsers":
					commonUsers(request, response);
					break;
				case "/admin/topTags":
					topTags(request, response);
					break;
				case "/admin/positiveUsers":
					positiveUsers(request, response);
					break;
				case "/admin/poorImages":
					poorImages(request, response);
					break;
				case "/admin/inactiveUsers":
					inactiveUsers(request, response);
					break;
			case "/test":
				test(request, response); 
				break; 
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    

    private void showPopularUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	  List<User> userList = userDAO.getPopularUsers();
   	   String userListTitle = "Popular Users"; 
   	   RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp"); 
   	    request.setAttribute("userList", userList);
   	    request.setAttribute("userListTitle", userListTitle);
          dispatcher.forward(request, response);
		
	}

	private void showTopUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	 List<User> userList = userDAO.getTopUsers();
	  	   String userListTitle = "Top Users"; 
	  	   RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp"); 
	  	    request.setAttribute("userList", userList);
	  	    request.setAttribute("userListTitle", userListTitle);
         dispatcher.forward(request, response);
	}

	private void test(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//	   List<Image> imageList = imageDAO.getViralImages();
//	   for(Image image: imageList) {
//		   System.out.println(image.toString()); 
//	   }
//	   
	   List<User> userList = userDAO.getPopularUsers();
	   String userListTitle = "Popular Users"; 
	   RequestDispatcher dispatcher = request.getRequestDispatcher("admin/UserList.jsp"); 
	    request.setAttribute("userList", userList);
	    request.setAttribute("userListTitle", userListTitle);
       dispatcher.forward(request, response);
	}
    
   private void showCoolImages(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	   List<Image> imageList = imageDAO.getCoolImages();
	   String imageListTitle = "Cool Images"; 
	   RequestDispatcher dispatcher = request.getRequestDispatcher("ImageList.jsp"); 
	    request.setAttribute("imageList", imageList);
	    request.setAttribute("imageListTitle", imageListTitle);
       dispatcher.forward(request, response);
	}
   
   private void showNewImages(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	   List<Image> imageList = imageDAO.getNewImages();
	   String imageListTitle = "New Images"; 
	   RequestDispatcher dispatcher = request.getRequestDispatcher("ImageList.jsp"); 
	    request.setAttribute("imageList", imageList);
	    request.setAttribute("imageListTitle", imageListTitle);
       dispatcher.forward(request, response);
	}
   
   private void showViralImages(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	   List<Image> imageList = imageDAO.getViralImages();
	   String imageListTitle = "Viral Images"; 
	   RequestDispatcher dispatcher = request.getRequestDispatcher("ImageList.jsp"); 
	    request.setAttribute("imageList", imageList);
	    request.setAttribute("imageListTitle", imageListTitle);
       dispatcher.forward(request, response);
	}

	/*private void getUserList(HttpServletRequest request, HttpServletResponse response, String view) throws SQLException, ServletException, IOException {
		if (view.equals("commonUsers")) {
			String userOne = request.getParameter("userOne");
			String userTwo = request.getParameter("userTwo");
			List<String> users = followDAO.commonUsers(userOne, userTwo);
			request.setAttribute("userList", users);
		} else {
			List<User> user = UserDAO.getUserView(view);
			request.setAttribute("userList", user);
		}
		request.getRequestDispatcher("userList.jsp").forward(request, response);
	}*/

	/*public void getTopTags(HttpServletRequest request, HttpServletResponse response) throws SQLException,ServletException, IOException {
		List<Tag> tags = tagsDAO.getView();
		request.setAttribute("tagList", tags);
		request.getRequestDispatcher("ImageList.jsp").forward(request, response);
	}*/

	/*private void showPoorImages(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
		List<Image> images = imageDAO.getRootView(view);
		request.setAttribute("listImages", images);
		request.setAttribute("fullUserList", userDAO.getAllUsers());
		request.getRequestDispatcher("ImageList.jsp").forward(request, response);
	}*/

	private void commonUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<User> userList = userDAO.commonUsers(request.getParameter("user1"), request.getParameter("user2"));
		String userListTitle = "Common Users";
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
		request.setAttribute("userList", userList);
		request.setAttribute("userListTitle", userListTitle);
		dispatcher.forward(request, response);
	}

	private void topTags(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<String> imageList = imageDAO.topTags();
		String imageListTitle = "Top Tag";
		RequestDispatcher dispatcher = request.getRequestDispatcher("ImageList.jsp");
		request.setAttribute("imageList", imageList);
		request.setAttribute("imageListTitle", imageListTitle);
		dispatcher.forward(request, response);
	}

	private void positiveUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<User> userList = userDAO.positiveUsers();
		String userListTitle = "Positive Users";
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
		request.setAttribute("userList", userList );
		request.setAttribute("userListTitle", userListTitle);
		dispatcher.forward(request, response);
	}

	private void poorImages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<Image> imageList = imageDAO.poorImages();
		String imageListTitle = "Poor Images";
		TreeMap<Image, String> imageAndTag = new TreeMap<Image, String>(Collections.reverseOrder());
		for(int i = 0; i < imageList.size(); i++) {
			List<String> existingTagsList = imageDAO.getTagsFromSingleImage(imageList.get(i).imgID);
			StringBuilder existingTags = new StringBuilder();
			for(String tag : existingTagsList) {
				existingTags.append(tag);
				existingTags.append(",");
			}
			String existingTagsString = existingTags.length() > 0 ? existingTags.substring(0, existingTags.length() - 1) : "";
			imageAndTag.put(imageList.get(i), existingTagsString);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("ImageList.jsp");
		request.setAttribute("imageList", imageList);
		request.setAttribute("treemapImageAndTag", imageAndTag);
		request.setAttribute("imageListTitle", imageListTitle);
		dispatcher.forward(request, response);
	}

	private void inactiveUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<User> userList = userDAO.inactiveUsers();
		String userListTitle = "Positive Users";
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
		request.setAttribute("userList", userList);
		request.setAttribute("userListTitle", userListTitle);
		dispatcher.forward(request, response);
	}

private void showAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");       
       dispatcher.forward(request, response);
		
	}

private void likePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		  HttpSession session = request.getSession();
	      User currentUser = (User) session.getAttribute("currentUser");
      
	    int imgID = Integer.parseInt(request.getParameter("imgID"));
    	likesDAO.insert(currentUser.email, imgID);
    	response.sendRedirect("feed");  
    }
    
    private void dislikePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
    	 HttpSession session = request.getSession();
         User currentUser = (User) session.getAttribute("currentUser");
         
        int imgID = Integer.parseInt(request.getParameter("imgID"));
    	likesDAO.delete( currentUser.email, imgID);
    	response.sendRedirect("feed");  
    }	
	
   private void postImage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	   HttpSession session = request.getSession();
       User currentUser = (User) session.getAttribute("currentUser");
       
       System.out.println("user in post Image: " + currentUser); 
       
		String url = request.getParameter("url");
       String tags = request.getParameter("tags");
	   	String description = request.getParameter("description");
	   	
	
	   	Image image = new Image(url, description, currentUser); 
	   	System.out.println("in post Image: " + image.toString()); 
	   	boolean isPosted = imageDAO.insert(currentUser.email, image); 
	   	
	   	
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
	   HttpSession session = request.getSession();
       User currentUser = (User) session.getAttribute("currentUser");
	   String imgIDString = request.getParameter("imgID"); 
	   int imgID = Integer.parseInt(imgIDString);
	   
	   boolean areTagsDeleted = tagsDAO.delete(imgID); 
	   boolean areCommentsDeleted = commentsDAO.delete(imgID);  
	   boolean areLikesDeleted = likesDAO.delete(currentUser.email, imgID); 
	   	
  
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
	
	   private void feedPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	HttpSession session = request.getSession();

	        User currentUser = (User) session.getAttribute("currentUser");
	        
	        
	    	List<Image> feedImages = imageDAO.getFeed(currentUser.email);
	    	int likeCount = 0;
			String tags = ""; 
			
			for(Image image: feedImages) { 
				likeCount = likesDAO.getLikesForImage(image.imgID);
				tags = tagsDAO.getTagsForImage(image.imgID); 
				image.setLikesCount(likeCount);
				image.setTags(tags);
				image.setLiked(likesDAO.isImageLikedByUser(currentUser.email, image.imgID)); 
			}
			
			request.setAttribute("feedImages", feedImages);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("FeedPage.jsp");       
	        dispatcher.forward(request, response);
		}
	   
	   private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	HttpSession session = request.getSession();
	        User currentUser = (User) session.getAttribute("currentUser");
	    	
	    	String searchInput = request.getParameter("searchInput");
	    	List<User> allUsers = new ArrayList<User>(); 
	    	System.out.println("\n\nout searchInput: " + searchInput);
    		
    		int spaceIndex = searchInput.indexOf(' '); 
    		if(spaceIndex == -1) {
    			allUsers = userDAO.searchUser(searchInput, "");
    		}
    		else {
    			String firstName = searchInput.substring(0, searchInput.indexOf(' '));
	    		String lastName = searchInput.substring(1, searchInput.indexOf(' '));
	    		allUsers = userDAO.searchUser(firstName, lastName); 
    		}
	    
	    	
	    	List<Follow> followList =  new ArrayList<Follow>();
	    	System.out.println("found users: "); 
	    	for(User otherUser: allUsers) {
	    		System.out.println(otherUser.toString()); 
	    		boolean isFollowedByCurrentUser = followDAO.isUserFollowedByCurrentUser(currentUser.email, otherUser.email); 
	    		Follow follow = new Follow(otherUser.email, isFollowedByCurrentUser); 
	    		followList.add(follow); 
	    	}
			
			request.setAttribute("followList", followList);
	    	request.setAttribute("allUsers", allUsers);
			request.getRequestDispatcher("CommunityPage.jsp").forward(request, response);
		}


	    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	HttpSession session = request.getSession();
	        User currentUser = (User) session.getAttribute("currentUser");
	    	
	    	String searchInput = request.getParameter("searchInput");
	    	List<User> allUsers = new ArrayList<User>(); 
	    	System.out.println("\n\nout searchInput: " + searchInput);
	    	
	    	if(searchInput != null) {
	    		
	    		System.out.println("in searchInput: " + searchInput);
	    		
	    		int spaceIndex = searchInput.indexOf(' '); 
	    		if(spaceIndex == -1) {
	    			allUsers = userDAO.searchUser(searchInput, " ");
	    		}
	    		else {
	    			String firstName = searchInput.substring(0, searchInput.indexOf(' '));
		    		String lastName = searchInput.substring(1, searchInput.indexOf(' '));
		    		allUsers = userDAO.searchUser(firstName, lastName); 
	    		}
	    	}
	    	else {
	    		allUsers = userDAO.getAllUsers();
	    	}
	    	
	    	List<Follow> followList =  new ArrayList<Follow>();
	    	for(User otherUser: allUsers) {
	    		boolean isFollowedByCurrentUser = followDAO.isUserFollowedByCurrentUser(currentUser.email, otherUser.email); 
	    		Follow follow = new Follow(otherUser.email, isFollowedByCurrentUser); 
	    		followList.add(follow); 
	    	}
			
			request.setAttribute("followList", followList);
	    	request.setAttribute("allUsers", allUsers);
			request.getRequestDispatcher("CommunityPage.jsp").forward(request, response);
		}


	    private void follow(HttpServletRequest request, HttpServletResponse response) throws SQLException,ServletException, IOException{
	    	HttpSession session = request.getSession();
	        User currentUser = (User) session.getAttribute("currentUser");
	        
	        String followUserEmail = request.getParameter("email"); 
	    	followDAO.insert(currentUser.email, followUserEmail);
	    	
	    	// update current user 
	    	currentUser.numOfFollowings++; 
	    	boolean updatedFollowing =userDAO.updateNumFollowings(currentUser.email, currentUser.numOfFollowings);
	    	
	    	
	    	// update the user that is being followed by current user
	    	int numFollowers = followDAO.getFollowerCount(followUserEmail); 
	    	boolean isUpdated = userDAO.updateNumFollowers(followUserEmail, numFollowers);
	    	
			response.sendRedirect("community");
		}
	    
	    private void unfollow(HttpServletRequest request, HttpServletResponse response) throws SQLException,ServletException, IOException{
	    	HttpSession session = request.getSession();
	        User currentUser = (User) session.getAttribute("currentUser");
	        
	        String followUserEmail = request.getParameter("email"); 
	    	followDAO.delete(currentUser.email, followUserEmail);
	    	
	    	// update current user 
	    	currentUser.numOfFollowings--; 
	    	boolean updatedFollowing =userDAO.updateNumFollowings(currentUser.email, currentUser.numOfFollowings);
	    	
	    	
	    	// update the user that is being unfollowed by current user
	    	int numFollowers = followDAO.getFollowerCount(followUserEmail); 
	    	boolean isUpdated = userDAO.updateNumFollowers(followUserEmail, numFollowers);
	    	

			response.sendRedirect("community");
		}
	    
	    



	private void initializeDatabase(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
//		HttpSession session = request.getSession();  
//        User currentUser = (User) session.getAttribute("currentUser");
		
//        if(currentUser.email.equals("root")){
    		
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
//        }  
        
        
        
//        response.sendRedirect("feed");
        
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
            
            currentUser.numOfFollowers = followDAO.getFollowerCount(currentUser.email);
            currentUser.numOfFollowings = followDAO.getFollowingCount(currentUser.email);
            response.sendRedirect("feed");  
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
            response.sendRedirect("feed");      
        }
        
    	}
 


}
    
 
