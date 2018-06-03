package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class Conference {

	ArrayList<Budget> hisBudgets;
	ArrayList<Guest> guests;
	ArrayList<Image> pictures;

	private String name;
	private LocalDate date;
	private String time;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getdate() {
		return date;
	}

	public void setdate(LocalDate date) {
		this.date = date;
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

	public Conference(String name, LocalDate date, String time, String description) {
		this.name = name;
		this.date = date;
		this.time = time;
		this.description = description;
	}

	public ArrayList<Budget> getHisBudgets() {
		return hisBudgets;
	}

	public void setHisBudgets(ArrayList<Budget> hisBudgets) {
		this.hisBudgets = hisBudgets;
	}

	public ArrayList<Guest> getGuests() {
		return guests;
	}

	public void setGuests(ArrayList<Guest> guests) {
		this.guests = guests;
	}

	public ArrayList<Image> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<Image> pictures) {
		this.pictures = pictures;
	}

	@Override
	public String toString() {
		return "Conference{" +
				"hisBudgets=" + hisBudgets +
				", guests=" + guests +
				", pictures=" + pictures +
				", name='" + name + '\'' +
				", date=" + date +
				", time='" + time + '\'' +
				", description='" + description + '\'' +
				'}';
	}

	public Conference getConferenceByName(){
		return this;
	}
}