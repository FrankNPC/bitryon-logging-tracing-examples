package io.bitryon.example.web.model;

import java.util.Objects;

public class MedicCondition {
	
	private long id;
	
	private long timeCreated;
	
	private String deceases;
	
	private long userId;

	public int hashCode() {
		return Objects.hash(id, timeCreated, deceases, userId);
	}
	public boolean equals(Object target) {
		if (target==null || !(target instanceof MedicCondition) || target.hashCode()!=this.hashCode()) {return false;}
		MedicCondition object = (MedicCondition) target;
		return Objects.equals(id, object.id)
				&& Objects.equals(timeCreated, object.timeCreated)
				&& Objects.equals(deceases, object.deceases)
				&& Objects.equals(userId, object.userId);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(long timeCreated) {
		this.timeCreated = timeCreated;
	}

	public String getDeceases() {
		return deceases;
	}

	public void setDeceases(String deceases) {
		this.deceases = deceases;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
