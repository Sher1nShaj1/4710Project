

public class User {
	private int id;
    private String username;
    private String password;
    private String passwordConfirmed;
    private String firstname;
    private String lastname;
    private int age;
 
    public User() {
    }
 
    public User(int id) {
        this.id=id;
    }
 
    public User(int id, String username, String password, String passwordConfirmed, String firstname, String lastname, int age ) {
    	this(username,password,passwordConfirmed,firstname,lastname,age);
    	this.id=id;
    }

    public User(String username, String password, String passwordConfirmed, String firstname, String lastname,
			int age) {
    	this.setUsername(username);
    	this.setPassword(password);
    	this.setPasswordConfirmed(passwordConfirmed);
    	this.setFirstname(firstname);
    	this.setLastname(lastname);
    	this.setAge(age);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmed() {
		return passwordConfirmed;
	}

	public void setPasswordConfirmed(String passwordConfirmed) {
		this.passwordConfirmed = passwordConfirmed;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
