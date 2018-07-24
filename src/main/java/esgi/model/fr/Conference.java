package esgi.model.fr;

import java.util.ArrayList;


public class Conference {

	private String name;
	private String date;
	private String time;
	private String description;
	private String id;
	private User user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String gettime() {
		return time;
	}

	public void settime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Conference(String name, String date, String time, String description,User user) {
		this.name = name;
		this.date = date;
		this.time = time;
		this.description = description;
		this.user = user;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getId() {

		return id;
	}

	@Override
	public String toString() {
		return "Conference{" +
				", name='" + name + '\'' +
				", date=" + date +
				", time='" + time + '\'' +
				", description='" + description + '\'' +
				", id='" + id + '\'' +
				'}';
	}

	public Conference getConferenceByName(){
		return this;
	}
}