package Model;

public class Locate {
	
	private int id;
	private String name;
	private int cityCode;
	private String city;
	private String address;
	private Conference conference;
	
	

	public Locate(String name, String address, int cityCode, String city, Conference conference) {
		this.name = name;
		this.address = address;
		this.cityCode = cityCode;
		this.city = city;
		this.conference = conference;
	}

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Locate{" +
				"id=" + id +
				", name='" + name + '\'' +
				", cityCode=" + cityCode +
				", city='" + city + '\'' +
				", address='" + address + '\'' +
				", conference=" + conference +
				'}';
	}
}
