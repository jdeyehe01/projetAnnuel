package Model;


import java.util.ArrayList;

public class User {

	private int id;
	private String email;
	private String password;
	private boolean isConnect;

	public User( String email, String password) {
		this.email = email;
		this.password = password;
		this.isConnect = false;
	}


	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isConnect() {
		return isConnect;
	}
	public void setConnect(boolean isConnect) {
		this.isConnect = isConnect;
	}


	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}