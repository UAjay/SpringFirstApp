package com.interview.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="EMPLOYEE")
public class Employee {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String email;
	
	private String country;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	 @Override
	  public int hashCode() {
	    int hash = 1;
	    hash = hash * 17 + this.firstName.hashCode();
	    hash = hash * 31 + this.id;
	    return hash;
	  }
	
	 @Override
	 public boolean equals(Object obj) {
	   if (obj == this) {
	     return true;
	   }
	   if (!(obj instanceof Employee)) {
	     return false;
	   }
	   Employee empObj = (Employee) obj;
	   return this.id == empObj.id;
	 }

	@Override
	public String toString(){
		return "id="+id+", name="+lastName +" "+firstName +", country="+country;
	}
}
