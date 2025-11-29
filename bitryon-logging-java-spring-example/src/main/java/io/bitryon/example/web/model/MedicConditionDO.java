package io.bitryon.example.web.model;

import java.util.Objects;

public class MedicConditionDO {
	
	private MedicCondition medicConditionDO;
	
	private User user;

	public int hashCode() {
		return Objects.hash(medicConditionDO, user);
	}
	public boolean equals(Object target) {
		if (target==null || !(target instanceof MedicConditionDO) || target.hashCode()!=this.hashCode()) {return false;}
		MedicConditionDO object = (MedicConditionDO) target;
		return Objects.equals(medicConditionDO, object.medicConditionDO)
				&& Objects.equals(user, object.user);
	}
	
	public MedicCondition getMedicConditionDO() {
		return medicConditionDO;
	}

	public void setMedicConditionDO(MedicCondition medicConditionDO) {
		this.medicConditionDO = medicConditionDO;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
