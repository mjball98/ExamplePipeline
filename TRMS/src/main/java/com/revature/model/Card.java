package com.revature.model;

import java.sql.Date;

public class Card {

	private int id;
	private String name;
	private int type_id;
	private boolean isFaceup;
	private Date date;

	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Card(int id, String name, int type_id, boolean isFaceup, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.type_id = type_id;
		this.isFaceup = isFaceup;
		this.date = date;
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

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public boolean isFaceup() {
		return isFaceup;
	}

	public void setFaceup(boolean isFaceup) {
		this.isFaceup = isFaceup;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + (isFaceup ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + type_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (isFaceup != other.isFaceup)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type_id != other.type_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", name=" + name + ", type_id=" + type_id + ", isFaceup=" + isFaceup + ", date="
				+ date + "]";
	}
}
