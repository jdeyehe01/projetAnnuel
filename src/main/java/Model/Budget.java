package Model;


public class Budget {

	private Conference conference;
	private String title;
	private Float amount;

	public String gettitle() {
		return this.title;
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
				'}';
	}
}