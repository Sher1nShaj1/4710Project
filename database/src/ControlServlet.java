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
    private PeopleDAO peopleDAO;
    private UserDAO userDAO; 
    private FollowDAO followDAO; 
 
    public void init(){
        peopleDAO = new PeopleDAO(); 
 
        try {
			userDAO = new UserDAO();
			followDAO = new FollowDAO();
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
            case "/getUser":
            	getUser(request, response);
            	break; 
            case "/initializeDatabase":
            	initializeDatabase(request, response); 
            	break;
            case "/home":
            	showHome(request, response); 
            	break; 
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
            	insertPeople(request, response);
                break;
            case "/delete":
            	deletePeople(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
            	updatePeople(request, response);
                break;
            default:          	
            	listPeople(request, response);           	
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    

	private void getUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String username = userDAO.getUser("kkapoor@email.com");
		System.out.println("username: " + username); 
		
	}

	private void showHome(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");       
        dispatcher.forward(request, response);
		
	}

	private void initializeDatabase(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		 followDAO.dropTable();
		 userDAO.dropTable();
		 int rowsCreated = userDAO.createTable();
		 int rowsCreatedFollow = followDAO.createTable();
		 userDAO.fillTable(); 
		 followDAO.fillTable(); 
		  
		 System.out.println("rows created: " + rowsCreated);
		 System.out.println("rows created: " + rowsCreatedFollow);
		 response.sendRedirect("home"); 
		
	}

	private void showLoginPage(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException{
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");       
        dispatcher.forward(request, response);
	}
    
    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String usernameInput = request.getParameter("username");
        String passwordInput = request.getParameter("password");
        
        // check if user exists in DB
        
        User user = userDAO.getUserByUsername(usernameInput); 
         
        if( user == null) { // invalid user
        	System.out.print("null");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            request.setAttribute("loginError", "Invalid username or password. Try again.");	
            dispatcher.forward(request, response);
        }
        else if (!passwordInput.equals(user.password)) {
        	System.out.println("pw");
        	System.out.println("password typed:  " + passwordInput); 
        	System.out.println( user.toString() );
        	RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            request.setAttribute("loginError", "Invalid username or password. Try again.");
            dispatcher.forward(request, response);
        }
        else {
        	System.out.println(user.username + " " + passwordInput); 
        	System.out.println( user.toString() );
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
    	String username = request.getParameter("username");
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
            request.setAttribute("username", username);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("gender", gender);
            request.setAttribute("birthday", birthday);
            dispatcher.forward(request, response); 
            
        }
        else if ( userDAO.getUserByUsername(username) == null ){ 
        	
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
        	User user = new User(username, password, firstName, lastName, gender, birthday); 
        	userDAO.insert(user); 
    	    System.out.println(user.toString()); 
            response.sendRedirect("home"); 
        }
        
     
	}

    
	private void listPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<People> listPeople = peopleDAO.listAllPeople();
        request.setAttribute("listPeople", listPeople);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleList.jsp");       
        dispatcher.forward(request, response);
    }
 
    // to insert a people
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("InsertPeopleForm.jsp");
        dispatcher.forward(request, response);
    }
 
    // to present an update form to update an  existing Student
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        People existingPeople = peopleDAO.getPeople(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditPeopleForm.jsp");
        request.setAttribute("people", existingPeople);
        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
 
    }
 
    // after the data of a people are inserted, this method will be called to insert the new people into the DB
    // 
    private void insertPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String status = request.getParameter("status");
        People newPeople = new People(name, address, status);
        peopleDAO.insert(newPeople);
        response.sendRedirect("list");  // The sendRedirect() method works at client side and sends a new request
    }
 
    private void updatePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        System.out.println(id);
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String status = request.getParameter("status");
        
        System.out.println(name);
        
        People people = new People(id,name, address, status);
        peopleDAO.update(people);
        response.sendRedirect("list");
    }
 
    private void deletePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        //People people = new People(id);
        peopleDAO.delete(id);
        response.sendRedirect("list"); 
    }

}