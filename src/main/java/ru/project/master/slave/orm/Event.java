package ru.project.master.slave.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "EVENT")
@NamedQuery(name = "getAll", query = "SELECT event FROM Event event")
public class Event {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "id", updatable = false, nullable = false)
	private String id;

	@Column(name = "message", nullable = false, length = 2048)
	private String message;

	public Event() {
		super();
	}

	public Event(String message) {
		this.message = message;
	}

	@PrePersist
	@PreUpdate
	protected void preLoad() {
		if (message.length() > 2048)
			throw new IllegalStateException("Length of field 'message' can't be more than 2048");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
