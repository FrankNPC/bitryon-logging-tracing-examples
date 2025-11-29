package io.bitryon.example.java.service.rpc;

import io.bitryon.example.java.model.User;

public interface UserService {
	
	public User getById(Long userId);

	public User getBySessionId(String sessionId);
	
	public User save(User user);
	
}
