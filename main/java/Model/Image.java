package Model;

import java.util.Date;

public class Image {

	private Conference conference;
	private String name;
	private String url;
	private Date date;

	public Date getDate() {
		return this.date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

    public Image(Conference conference, String name, String url, Date date) {
        this.conference = conference;
        this.name = name;
        this.url = url;
        this.date = date;
    }

    public String getUrl() {
		return this.url;
	}

	/**
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}


}