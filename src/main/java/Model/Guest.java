package Model;

public class Guest {

	private String fname;

	private String lname;

	private String email;

	private Conference conf;

	public Guest(String fname, String lname, String email) {
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}

	public Guest(String fname, String lname, String email,Conference conf) {
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.conf = conf;
	}

	public Conference getConf() {
		return conf;
	}

	public void setConf(Conference conf) {
		this.conf = conf;
	}

	public String getfname() {
		return fname;
	}

	public void setfname(String fname) {
		this.fname = fname;
	}

	public String getlname() {
		return lname;
	}

	public void setlname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
}