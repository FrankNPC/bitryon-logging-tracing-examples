package io.bitryon.example.web.model;

import java.util.Date;
import java.util.Objects;

public class User {
	
	private long id;
	
	private String name;
	
	private Integer age;
	
	private Date birthday;
	
	private String driverLisenceId;

	public int hashCode() {
		return Objects.hash(id, name, age, birthday, driverLisenceId);
	}
	public boolean equals(Object target) {
		if (target==null || !(target instanceof User) || target.hashCode()!=this.hashCode()) {return false;}
		User object = (User) target;
		return Objects.equals(id, object.id)
				&& Objects.equals(name, object.name)
				&& Objects.equals(age, object.age)
				&& Objects.equals(birthday, object.birthday)
				&& Objects.equals(driverLisenceId, object.driverLisenceId);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDriverLisenceId() {
		return driverLisenceId;
	}

	public void setDriverLisenceId(String driverLisenceId) {
		this.driverLisenceId = driverLisenceId;
	}
	
}
