package io.bitryon.example.java.service.rpc;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import io.bitryon.example.java.model.User;
import io.bitryon.logger.Logger;
import io.bitryon.logger.annotation.Logging;
import io.bitryon.logger.provider.LoggerFactory;

@Logging
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger();
	
	public User getById(Long userId){
		if (userId==null) {
			return null;
		}

		logger.text("This should be remote service, will be with trace for user {}", userId);
		User user = new User();
		user.setId(123L);
		try {
			SimpleDateFormat simpleDateFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
			user.setBirthday(simpleDateFormater.parse("2025-08-10T15:42:17.123+02"));
		} catch (ParseException e) {
			logger.exception(e);
		}
		user.setDriverLisenceId("a number that you can't know");
		user.setName("randome guy probably");
		return user;
	}

	public User getBySessionId(String sessionId){
		if (sessionId==null) {
			return null;
		}
		User user = new User();
		user.setId(456L);
		try {
			SimpleDateFormat simpleDateFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
			user.setBirthday(simpleDateFormater.parse("2025-08-10T15:42:17.123+02"));
		} catch (ParseException e) {
			logger.exception(e);
		}
		user.setDriverLisenceId("a number that you can't know");
		user.setName("randome guy probably");
		return user;
	}

	public User save(User user){
		if (user==null) {
			return null;
		}

		user.setId(789L);
		try {
			SimpleDateFormat simpleDateFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
			user.setBirthday(simpleDateFormater.parse("2025-08-10T15:42:17.123+02"));
		} catch (ParseException e) {
			logger.exception(e);
		}
//		user.setDriverLisenceId("new DL Id");
//		user.setName("john");
		return user;
	}
}
