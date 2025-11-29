package io.bitryon.example.java.service;

import java.util.List;
import java.util.stream.Collectors;


import io.bitryon.example.java.dao.MedicDAO;
import io.bitryon.example.java.model.MedicCondition;
import io.bitryon.example.java.model.MedicConditionDO;
import io.bitryon.example.java.model.Page;
import io.bitryon.example.java.model.User;
import io.bitryon.example.java.service.rpc.UserServiceImpl;
import io.bitryon.example.java.test.ForStaticMethod;
import io.bitryon.logger.annotation.Logging;

@Logging
public class MedicService {

	UserServiceImpl userService = new UserServiceImpl();

	MedicDAO medicDAO = new MedicDAO();
	
	public List<MedicConditionDO> query(String sessionId, Page page){
		User getUserRet = userService.getBySessionId(sessionId);
		if (getUserRet == null) {
			return null;
		}
		List<MedicCondition> medicList = medicDAO.query(getUserRet.getId(), page);
//		List<MedicCondition medicList = medicDAO.query(0L, page);
		this.callSelfInvocation(sessionId);
		if (medicList!=null) {
			return medicList.stream().map(medic -> {
				User userRet = userService.getById(medic.getUserId());
				MedicConditionDO medicConditionDO = new MedicConditionDO();
				medicConditionDO.setMedicConditionDO(medic);
				medicConditionDO.setUser(userRet);
				return medicConditionDO;
			}).collect(Collectors.toList());
		}
		return null;
	}
	
	public MedicConditionDO save(MedicCondition condition){
		MedicCondition medicCondition = medicDAO.save(condition);
		if (medicCondition == null) {
			return null;
		}
		
		User userRet = userService.getById(medicCondition.getUserId());
		MedicConditionDO medicConditionDO = new MedicConditionDO();
		medicConditionDO.setMedicConditionDO(medicCondition);
		medicConditionDO.setUser(userRet);
		return medicConditionDO;
	}
	
	public User saveUser(User user) {// UserService is a RPC service.
		ForStaticMethod.runStatic(user.getId());
		User savedUser = userService.save(user);
		ForStaticMethod.runStatic(savedUser.getId());
		return savedUser;
	}

	public String callSelfInvocation(String testString) {
		callSelfInvocation_protected(testString);
		return Long.toString(System.currentTimeMillis());
	}

	protected String callSelfInvocation_protected(String testString) {
		callSelfInvocation_friendly(testString);
		return Long.toString(System.currentTimeMillis());
	}

	String callSelfInvocation_friendly(String testString) {
		callSelfInvocation_private(testString);
		return Long.toString(System.currentTimeMillis());
	}
	
	private String callSelfInvocation_private(String testString) {
		callSelfInvocation_final(testString);
		return Long.toString(System.currentTimeMillis());
	}
	
	final String callSelfInvocation_final(String testString) {
		return Long.toString(System.currentTimeMillis());
	}
	
}
