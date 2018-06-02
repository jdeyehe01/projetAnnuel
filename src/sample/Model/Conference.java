package sample.Model;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.util.ArrayList;


public class Conference {

	ArrayList<Budget> hisBudgets;
	ArrayList<Guest> guests;
	ArrayList<Image> pictures;

	private String name;
	private ObjectProperty<Callback<DatePicker, DateCell>> dateBegin;
	private String timeBegin;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObjectProperty<Callback<DatePicker, DateCell>> getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(ObjectProperty<Callback<DatePicker, DateCell>> dateBegin) {
		this.dateBegin = dateBegin;
	}

	public String getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(String timeBegin) {
		this.timeBegin = timeBegin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Conference(String name, ObjectProperty<Callback<DatePicker, DateCell>> dateBegin, String timeBegin, String description) {
		this.name = name;
		this.dateBegin = dateBegin;
		this.timeBegin = timeBegin;
		this.description = description;
	}

	public Conference(ArrayList<Budget> hisBudgets, ArrayList<Guest> guests, ArrayList<Image> pictures,
					  String name, ObjectProperty<Callback<DatePicker, DateCell>> dateBegin, String timeBegin, String description) {
		this.hisBudgets = hisBudgets;
		this.guests = guests;
		this.pictures = pictures;
		this.name = name;
		this.dateBegin = dateBegin;
		this.timeBegin = timeBegin;
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
				", dateBegin=" + dateBegin +
				", timeBegin='" + timeBegin + '\'' +
				", description='" + description + '\'' +
				'}';
	}

	public Conference getConferenceByName(){
		return this;
	}
}