package io.bitryon.example.web.service.rpc;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import io.bitryon.example.web.model.User;
import io.bitryon.logger.Logger;
import io.bitryon.logger.annotation.Logging;
import io.bitryon.spring.rmi.http.prodiver.Provider;
import jakarta.annotation.Resource;

@Provider("/remote_api/") // RPC service 
@Service
@Logging
public class UserServiceImpl { // impelements UserService {
	// In general it should implement UserService, but there will be duplicated beans in the same service.
	// So use the same name UserService to mock the service provider to match the service subscriber
	// it provide a service endpoint at http://localhost/remote_api/user/get_by_id.
	@Resource
	Logger logger;
	
	//private static final Logger logger = LoggerFactory.getLogger(); // both work. recommended

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
