package io.bitryon.example.java.test;

import java.util.UUID;

import io.bitryon.example.java.model.Page;
import io.bitryon.logger.annotation.Logging;

@Logging
public class ForStaticMethod {
	
	public String query(long userId, Page page){
		return UUID.randomUUID().toString();
	}

	public static String runStatic(long userId){
		return "e1d2fb44-c29c-4925-93cc-99081578eaf8";
	}
	
}
