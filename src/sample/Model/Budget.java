package sample.Model;


public class Budget {

	private Conference yourConference;
	private String name;
	private Float amount;

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "Budget{" +
				"yourConference=" + yourConference +
				", name='" + name + '\'' +
				", amount=" + amount +
				'}';
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @param name
	 * @param amount
	 */
    public Budget(Conference yourConference, String name, Float amount) {
        this.yourConference = yourConference;
        this.name = name;
        this.amount = amount;
    }
}