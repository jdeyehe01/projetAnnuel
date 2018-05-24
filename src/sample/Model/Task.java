package sample.Model;

public class Task {
	
	private int id;
	private String title;
	private float amount;
	private String duration;
	
	public Task(String title, float amount, String duration) {
		this.title = title;
		this.amount = amount;
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	

}
