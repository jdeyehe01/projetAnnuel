package Model;

public class Task {
	
	private int id;
	private String title;

	private String duration;
	private Conference conference;

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	public Task(String title, String duration, Conference conference) {
		this.title = title;
		this.duration = duration;
		this.conference = conference;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}


	public int getId() {
		return id;
	}
}
