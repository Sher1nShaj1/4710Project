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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO; 
    private FollowDAO followDAO; 
    private ImageDAO imageDAO;
    private CommentsDAO commentsDAO; 
    private LikesDAO likesDAO; 
    private TagsDAO tagsDAO; 
    private User currentUser; 
    
    public void init(){
 
        try {
			userDAO = new UserDAO();
			followDAO = new FollowDAO();
			imageDAO = new ImageDAO(); 
			commentsDAO = new CommentsDAO(); 
			likesDAO = new LikesDAO(); 
			tagsDAO = new TagsDAO(); 
			currentUser = null; 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
            case "/home":
            	showHome(request, response); 
            	break; 
            case "/userProfile":
            	showUserProfile(request, response); 
            	break; 
           
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    

	private void showUserProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		HttpSession session = request.getSession();
        currentUser = (User) session.getAttribute("currentUser");
        System.out.println(currentUser); 
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");       
        dispatcher.forward(request, response);
	}

	private void initializeDatabase(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
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
		
	 
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
	     dispatcher.forward(request, response);
		
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
         
        System.out.println("eamil: " + emailInput + " password: " + passwordInput); 
        
        currentUser = userDAO.getUserByEmail(emailInput); 
        
         
        if( currentUser == null || !passwordInput.equals(currentUser.password) ) { // user not found
        	System.out.print("null");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            request.setAttribute("loginError", "Invalid username or password. Try again.");
            dispatcher.forward(request, response);
        }
        else {
        	HttpSession session = request.getSession();
            session.setAttribute("currentUser", currentUser);
        	System.out.println(currentUser.getEmail() + " " + passwordInput); 
        	System.out.println( currentUser.toString() );
        	RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
        	dispatcher.forward(request, response); 
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
        	
        	currentUser = new User(email, password, firstName, lastName, gender, birthday);
        	HttpSession session = request.getSession();
            session.setAttribute("currentUser", currentUser);
        	userDAO.insert(currentUser); 
    	    System.out.println(currentUser.toString()); 
            response.sendRedirect("home"); 
        }
        
     
	}

}