

public class User {
	protected int id;
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String gender;
	protected String birthday; 
    
 
    public User() {
    }
 
    public User(int id) {
        this.id=id;
    }
    
    

	public User(String username, String password, String firstName, String lastName, String gender, String birthday) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
	}

	public User(int id, String username, String password, String firstname, String lastname,
			String gender, String birthday) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastname;
		this.gender = gender;
		this.birthday = birthday;
	}
	


	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastname) {
		this.lastName = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender + ", birthday=" + birthday + "]";
	}

	

}