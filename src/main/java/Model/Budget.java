package Model;


public class Budget {

	private Conference conference;
	private String title;
	private Float amount;
	private String id;

	public String gettitle() {
		return this.title;
	}

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @param title
	 */

	public void settitle(String title) {
		this.title = title;
	}

	public Float getMontant() {
		return this.amount;
	}

	/**
	 * 
	 * @param amount
	 */
	public void setMontant(Float amount) {
		this.amount = amount;
	}

	/**
	 * 
	 * @param title
	 * @param amount
	 */
    public Budget( String title, Float amount, Conference conference) {
        this.title = title;
        this.amount = amount;
		this.conference = conference;
    }

	@Override
	public String toString() {
		return "Budget{" +
				"title='" + title + '\'' +
				", amount=" + amount +
				", id='" + id + '\'' +
				'}';
	}
}