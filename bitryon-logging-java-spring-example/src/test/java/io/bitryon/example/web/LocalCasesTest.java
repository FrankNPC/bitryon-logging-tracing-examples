package io.bitryon.example.web;


import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.bitryon.BitryonWebExampleBootApplication;
import io.bitryon.example.web.dao.MedicDAO;
import io.bitryon.example.web.service.MedicService;
import io.bitryon.example.web.service.rpc.UserServiceImpl;
import io.bitryon.example.web.test.ForStaticMethod;
import io.bitryon.logger.Logger;
import io.bitryon.logger.PreDefinition;
import io.bitryon.logger.boostrap.LoggingMethodIntercepter;
import io.bitryon.logger.model.LogLineInvoke;
import io.bitryon.logger.spring.LogLineInvokerHelper;

import io.bitryon.logger.helper.LogLineHelper;
import jakarta.annotation.Resource;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = BitryonWebExampleBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public final class LocalCasesTest {

	@Resource
	Logger logger;

	@Resource 
	LoggingMethodIntercepter loggingMethodIntercepter;
	
	
	@Resource
	UserServiceImpl userServiceImpl;// It should be the interface but userService is announced as a provider
	
	@Resource
	MedicService medicService;

	@Resource
	MedicDAO medicDAO;
	
	private Map<Method, Object> methodToBeans;
	@BeforeAll
	public void init() throws SecurityException, Exception {
		methodToBeans = LogLineInvokerHelper.loadMethods(userServiceImpl, medicService, medicDAO, ForStaticMethod.class);
	}
	
	@Test
	public void test_case1() throws Exception {
		String fileName = "sample/test_case1.log";
		LogLineInvoke logLineInvoke = LogLineHelper.readLogLineFile(PreDefinition.LoggerSchemaVersion.Version_1_0, fileName);
		Assertions.assertNotNull(logLineInvoke);
		Assertions.assertNotNull(logLineInvoke.getParameterSample());
		Assertions.assertNotNull(logLineInvoke.getReturnSample());
		
		LogLineInvokerHelper.invokeLogLineInvokers(loggingMethodIntercepter, methodToBeans, logLineInvoke);

		Assertions.assertNotNull(logLineInvoke.getMethod());
		Assertions.assertNotNull(logLineInvoke.getTargetObject());
		Assertions.assertNotNull(logLineInvoke.getArgsForMethod());
		Assertions.assertNotNull(logLineInvoke.getReturnAndParameter());
		
		Object[] retVals = LogLineInvokerHelper.parseReturnObject(logLineInvoke.getMethod(), logLineInvoke.getReturnSample().getPayload());
		Assertions.assertNotNull(retVals);
		Assertions.assertTrue(retVals.length>0);
		Assertions.assertNotNull(logLineInvoke.getReturnAndParameter());
		Assertions.assertTrue(logLineInvoke.getReturnAndParameter().length>0);
		
		Assertions.assertEquals(retVals[0], logLineInvoke.getReturnAndParameter()[0]);
	}
	
	@Test
	public void test_case2() throws Exception {
		String fileName = "sample/test_case2.log";//simple style
		LogLineInvoke logLineInvoke = LogLineHelper.readLogLineFile(PreDefinition.LoggerSchemaVersion.Version_1_0, fileName);
		Assertions.assertNotNull(logLineInvoke);
		Assertions.assertNotNull(logLineInvoke.getParameterSample());
		Assertions.assertNotNull(logLineInvoke.getReturnSample());
		
		LogLineInvokerHelper.invokeLogLineInvokers(loggingMethodIntercepter, methodToBeans, logLineInvoke);

		Assertions.assertNotNull(logLineInvoke.getMethod());
		Assertions.assertNotNull(logLineInvoke.getTargetObject());
		Assertions.assertNotNull(logLineInvoke.getArgsForMethod());
		Assertions.assertNotNull(logLineInvoke.getReturnAndParameter());
		
		Object[] retVals = LogLineInvokerHelper.parseReturnObject(logLineInvoke.getMethod(), logLineInvoke.getReturnSample().getPayload());
		Assertions.assertNotNull(retVals);
		Assertions.assertTrue(retVals.length>0);
		Assertions.assertNotNull(logLineInvoke.getReturnAndParameter());
		Assertions.assertTrue(logLineInvoke.getReturnAndParameter().length>0);
		
		io.bitryon.example.web.model.User sampleUser = (io.bitryon.example.web.model.User) retVals[0];
		io.bitryon.example.web.model.User returnUser = (io.bitryon.example.web.model.User) logLineInvoke.getReturnAndParameter()[0];

		Assertions.assertEquals(sampleUser, returnUser);
		Assertions.assertEquals(sampleUser.getAge(), returnUser.getAge());
		Assertions.assertEquals(sampleUser.getDriverLisenceId(), returnUser.getDriverLisenceId());
		Assertions.assertEquals(sampleUser.getName(), returnUser.getName());
		Assertions.assertEquals(sampleUser.getBirthday(), returnUser.getBirthday());
		Assertions.assertEquals(returnUser.getId(), sampleUser.getId());
		
	}

	@Test
	public void test_case3() throws Exception {
		String fileName = "sample/test_case3.log";
		LogLineInvoke logLineInvoke = LogLineHelper.readLogLineFile(PreDefinition.LoggerSchemaVersion.Version_1_0, fileName);
		Assertions.assertNotNull(logLineInvoke);
		Assertions.assertNotNull(logLineInvoke.getParameterSample());
		Assertions.assertNotNull(logLineInvoke.getReturnSample());
		
		LogLineInvokerHelper.invokeLogLineInvokers(loggingMethodIntercepter, methodToBeans, logLineInvoke);

		Assertions.assertNotNull(logLineInvoke.getMethod());
		Assertions.assertNotNull(logLineInvoke.getTargetObject());
		Assertions.assertNotNull(logLineInvoke.getArgsForMethod());
		Assertions.assertNotNull(logLineInvoke.getReturnAndParameter());
		
		Object[] retVals = LogLineInvokerHelper.parseReturnObject(logLineInvoke.getMethod(), logLineInvoke.getReturnSample().getPayload());
		Assertions.assertNotNull(retVals);
		Assertions.assertTrue(retVals.length>0);
		Assertions.assertNotNull(logLineInvoke.getReturnAndParameter());
		Assertions.assertTrue(logLineInvoke.getReturnAndParameter().length>0);
		
		Assertions.assertEquals(retVals[0], logLineInvoke.getReturnAndParameter()[0]);
	}
	
//	@Test
	public void test_case4() throws Exception {// Only works for the on-method mode.
		String fileName = "sample/test_case4.log";
		LogLineInvoke logLineInvoke = LogLineHelper.readLogLineFile(PreDefinition.LoggerSchemaVersion.Version_1_0, fileName);
		LogLineInvokerHelper.invokeLogLineInvokers(loggingMethodIntercepter, methodToBeans, logLineInvoke);
		Object[] retVals = LogLineInvokerHelper.parseReturnObject(logLineInvoke.getMethod(), logLineInvoke.getReturnSample().getPayload());
		Assertions.assertEquals(retVals[0], logLineInvoke.getReturnAndParameter()[0]);
	}
	
	@Test
	public void test_case5() throws Exception {
		String fileName = "sample/test_case5.log";// the unique method name[query] in MedicService, invalid line number, still match the method call
		LogLineInvoke logLineInvoke = LogLineHelper.readLogLineFile(PreDefinition.LoggerSchemaVersion.Version_1_0, fileName);
		LogLineInvokerHelper.invokeLogLineInvokers(loggingMethodIntercepter, methodToBeans, logLineInvoke);
		Object[] retVals = LogLineInvokerHelper.parseReturnObject(logLineInvoke.getMethod(), logLineInvoke.getReturnSample().getPayload());
		Assertions.assertEquals(retVals[0], logLineInvoke.getReturnAndParameter()[0]);
	}

	@Test
	public void test_case6() throws Exception {
		String[] fileNames = {
				"sample/test_case6.log", //non-pretty style
				"sample/test_case7.log"
				};
		List<LogLineInvoke> logLineInvokes = LogLineHelper.readLogLineFiles(PreDefinition.LoggerSchemaVersion.Version_1_0, fileNames);
		LogLineInvokerHelper.invokeLogLineInvokers(loggingMethodIntercepter, methodToBeans, logLineInvokes);
		for(LogLineInvoke logLineInvoke : logLineInvokes) {
			Object[] retVals = LogLineInvokerHelper.parseReturnObject(logLineInvoke.getMethod(), logLineInvoke.getReturnSample().getPayload());
			Assertions.assertEquals(retVals[0], logLineInvoke.getReturnAndParameter()[0]);
		}
	}
	
	// test if it contains values as subset. It allows some un-used values presence in superset.
	@Test
	public void test_case8() throws Exception {
		String fileName = "sample/test_case8.log";
		LogLineInvoke logLineInvoke = LogLineHelper.readLogLineFile(PreDefinition.LoggerSchemaVersion.Version_1_0, fileName);
		LogLineInvokerHelper.invokeLogLineInvokers(loggingMethodIntercepter, methodToBeans, logLineInvoke);
		Object[] retSampleVals = LogLineInvokerHelper.parseReturnObject(logLineInvoke.getMethod(), logLineInvoke.getReturnSample().getPayload());
		Assertions.assertTrue(LogLineInvokerHelper.containSubset(retSampleVals[0], logLineInvoke.getReturnAndParameter()[0]));
		
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			Assertions.assertTrue(LogLineInvokerHelper.containSubset(logLineInvoke.getReturnAndParameter()[0], retSampleVals[0]));
		});
	}

	@Test
	public void test_case9() throws Exception {
		String fileName = "sample/test_case9.log";
		LogLineInvoke logLineInvoke = LogLineHelper.readLogLineFile(PreDefinition.LoggerSchemaVersion.Version_1_0, fileName);
		LogLineInvokerHelper.invokeLogLineInvokers(loggingMethodIntercepter, methodToBeans, logLineInvoke);
		Object[] retSampleVals = LogLineInvokerHelper.parseReturnObject(logLineInvoke.getMethod(), logLineInvoke.getReturnSample().getPayload());
		
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			Assertions.assertTrue(LogLineInvokerHelper.containSubset(logLineInvoke.getReturnAndParameter()[0], retSampleVals[0]));
		});
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			Assertions.assertTrue(LogLineInvokerHelper.containSubset(retSampleVals[0], logLineInvoke.getReturnAndParameter()[0]));
		});
	}
}
