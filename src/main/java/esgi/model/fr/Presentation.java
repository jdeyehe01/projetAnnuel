package esgi.model.fr;


public class Presentation {
	
	private int id;
	private String title;
	private float amount;
	private String description;
	private Conference conference;
	
	public Presentation(String title, float amount, String description , Conference conference) {
		this.title = title;
		this.amount = amount;
		this.description = description;
		this.conference = conference;
	}



    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	

}
