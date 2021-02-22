package com.revature.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * Hibernate allows us to use an annotation-driven approach to mapping our 
 * Java models to the entities within our database.
 * 
 * Note that the annotations that we use for mapping our models come from
 * the Java Persistence API - NOT Hibernate as Hibernate's version of these
 * annotations are deprecated.
 */

/*
 * @Entity marks this class as an entity, meaning that we intend to map this
 * class to a table in our database.
 */
@Entity

/*
 * The @Table annotation allows us to specify information about the table we
 * want associated with our model. You can specify, for instance, the table's
 * name (in the database). That said, you don't have to specify the table name
 * because Hibernate will use the model's name as the table name if no name is
 * specified here.
 */
@Table(name = "card_table")
public class Card {

	/*
	 * The @Id annotation denotes that we wish to use this field as a primary key on
	 * this table.
	 */
	@Id
	/*
	 * The @Column annotation denotes that we want this field to represent a column
	 * on our table.
	 */
	@Column(name = "card_id")
	/*
	 * The @GeneratedValue annotation specifies that the value of the data in this
	 * column is generated by a sequence. For example, the serial data type in
	 * Postgres has an underlying sequence which is used to generate the next value
	 * which should be inserted on our table in this column.
	 */
	@GeneratedValue(generator = "card_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "card_id_seq", sequenceName = "card_id_seq")
	private int id;
	@Column
	private String name;
	@Column
	private boolean face_up;
	@Column
	private Date date_created;

	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Card(int id, String name, boolean face_up, Date date_created) {
		super();
		this.id = id;
		this.name = name;
		this.face_up = face_up;
		this.date_created = date_created;
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

	public boolean isFace_up() {
		return face_up;
	}

	public void setFace_up(boolean face_up) {
		this.face_up = face_up;
	}

	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date_created == null) ? 0 : date_created.hashCode());
		result = prime * result + (face_up ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (date_created == null) {
			if (other.date_created != null)
				return false;
		} else if (!date_created.equals(other.date_created))
			return false;
		if (face_up != other.face_up)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", name=" + name + ", face_up=" + face_up + ", date_created=" + date_created + "]";
	}
}
