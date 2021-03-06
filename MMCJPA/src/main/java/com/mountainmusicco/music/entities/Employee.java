package com.mountainmusicco.music.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Employee {
	
	// F E I L D S

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	@Column(name="pay_rate")
	private double payRate;
	private String email;
	@ManyToOne
	private Address address;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	@OneToMany(mappedBy = "employee")
	private List<EmployeeNote> employeeNotes;
	@ManyToMany(mappedBy = "employees")
	private List<Event> events;
	
	// C O N S T R U C T O R S
	
	public Employee(int id, String title, double payRate, String email, Address address, User user) {
		super();
		this.id = id;
		this.title = title;
		this.payRate = payRate;
		this.email = email;
		this.address = address;
		this.user = user;
	}
	
	public Employee() {
		super();
	}
	
	// G E T T E R S  /  S E T T E R S
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPayRate() {
		return payRate;
	}
	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public List<EmployeeNote> getNotes() {
		return employeeNotes;
	}

	
	public void setNotes(List<EmployeeNote> notes) {
		this.employeeNotes = notes;
	}
	
	// H A S H  C O D E  /  E Q U A L S

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	// T O  S T R I N G
	@Override
	public String toString() {
		return user + "-" + title;
	}
	
	// M E T H O D S
	
	public Event addEvent(Event event) {
		if (events == null) {
			events = new ArrayList<Event>();
		}
		if (!events.contains(event)) {
			events.add(event);
			event.addEmployee(this);
		}
		return event;
	}

	public Event removeEvent(Event event) {
		if (events != null && events.contains(event)) {
			events.remove(event);
			event.removeEmployee(this);
		}
		
		return event;
	}
	
	
}
