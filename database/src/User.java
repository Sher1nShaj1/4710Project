

public class User {
	protected String email;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String gender;
	protected String birthday;
	protected int numOfFollowers;
	protected int numOfFollowings;
	
	
/*
 * 
 * email VARCHAR(100) NOT NULL,
	password VARCHAR(20),
	firstName VARCHAR(20),
	lastName VARCHAR(20),
	gender CHAR(1),
	birthday DATE ,
	numOfFollowers INT,
	numOfFollowings INT,	
 */
    
 
    public User() {
		this.email = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.gender = "";
		this.birthday = "";
		this.numOfFollowers = 0;
		this.numOfFollowings = 0;
    }
 
    

	public User(String email, String password, String firstName, String lastName, String gender, String birthday,
		int numOfFollowers, int numOfFollowings) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
		this.numOfFollowers = numOfFollowers;
		this.numOfFollowings = numOfFollowings;
	}
	


	public User(String email, String password, String firstName, String lastName, String gender, String birthday) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
		this.numOfFollowers = 0;
		this.numOfFollowings = 0; 
	}



	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}



	public int getNumOfFollowers() {
		return numOfFollowers;
	}



	public void setNumOfFollowers(int numOfFollowers) {
		this.numOfFollowers = numOfFollowers;
	}



	public int getNumOfFollowings() {
		return numOfFollowings;
	}



	public void setNumOfFollowings(int numOfFollowings) {
		this.numOfFollowings = numOfFollowings;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstname) {
		this.firstName = firstname;
	}

	public String getlastName() {
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
		return "User [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", birthday=" + birthday + ", numOfFollowers=" + numOfFollowers
				+ ", numOfFollowings=" + numOfFollowings + "]";
	}
	
	

	
	

}