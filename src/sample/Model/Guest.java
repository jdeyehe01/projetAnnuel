package sample.Model;

public class Guest {

	private String firstName;

	private String lastName;

	private String age;

	private String email;

	public Guest(String firstName, String lastName, String age, String email, String adressePostal, int codePostal, String ville) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
	
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
}