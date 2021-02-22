package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursement_table")
public class Reimbursement {
	
	@Id
	@Column(name = "reimbursement_id")
	@GeneratedValue(generator = "reimbursement_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "reimbursement_id_seq", sequenceName = "reimbursement_id_seq")
	private int id;
	@Column
	private float amount;
	@Column
	private int employee_id;
	@Column
	private String status;
	// need some different type for reciept??
	@Column
	private String reciept;
	@Column
	private int resolving_manager_id;

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int id, float amount, int employee_id, String status, String reciept,
			int resolving_manager_id) {
		super();
		this.id = id;
		this.amount = amount;
		this.employee_id = employee_id;
		this.status = status;
		this.reciept = reciept;
		this.resolving_manager_id = resolving_manager_id;
	}

	public int getResolving_manager_id() {
		return resolving_manager_id;
	}

	public void setResolving_manager_id(int resolving_manager_id) {
		this.resolving_manager_id = resolving_manager_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReciept() {
		return reciept;
	}

	public void setReciept(String reciept) {
		this.reciept = reciept;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + employee_id;
		result = prime * result + id;
		result = prime * result + ((reciept == null) ? 0 : reciept.hashCode());
		result = prime * result + resolving_manager_id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (employee_id != other.employee_id)
			return false;
		if (id != other.id)
			return false;
		if (reciept == null) {
			if (other.reciept != null)
				return false;
		} else if (!reciept.equals(other.reciept))
			return false;
		if (resolving_manager_id != other.resolving_manager_id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", employee_id=" + employee_id + ", status=" + status
				+ ", resolving_manager_id=" + resolving_manager_id + ", reciept=" + reciept + "]";
	}

}
