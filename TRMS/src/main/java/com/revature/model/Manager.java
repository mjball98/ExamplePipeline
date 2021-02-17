package com.revature.model;

public class Manager {

	private int id;
	private String full_name;

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public Manager(int id, String full_name) {
		super();
		this.id = id;
		this.full_name = full_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((full_name == null) ? 0 : full_name.hashCode());
		result = prime * result + id;
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
		Manager other = (Manager) obj;
		if (full_name == null) {
			if (other.full_name != null)
				return false;
		} else if (!full_name.equals(other.full_name))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", full_name=" + full_name + "]";
	}

}
