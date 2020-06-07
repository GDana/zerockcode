package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SampleServiceTests {

	@Setter(onMethod_ = @Autowired)
	private SampleService service;
	
	//@Test
	public void testClass() {	
		log.info(service);
		log.info(service.getClass().getName());
		
		/*
		 * 1. AOP 설정을 한 Target에 대해서 Proxy 객체가 정상적으로 만들어져 있는지 확인하는 testClass()
		 * 2. <aop:aspectj-autoproxy>가 정상적으로 모든 동작을 하고 
		 * 3. LogAdvice에 설정 문제가 없다면
		 * 4. service 변수의 클래스는 단순히 org.zerock.service.SampleServiceImpl의 인스턴스가 아닌 
		 * 		생성된 proxy 클래스의 인스턴스가 된다
		 * 5. 결과 : log.info(service.getClass().getName());
		 	INFO : org.zerock.service.SampleServiceTests - org.zerock.service.SampleServiceImpl@771a660
			INFO : org.zerock.service.SampleServiceTests - com.sun.proxy.$Proxy23
		 */
	}
	
	@Test
	public void testAdd() throws Exception {
		log.info(service.doAdd("123", "456"));
		
		/* 결과1 : logBefore()
		 	INFO : org.zerock.aop.LogAdvice - -----------------------------
			INFO : org.zerock.service.SampleServiceTests - 579
		 */
		/* 결과2 : logBeforeWithParam(), arg이용하여 파라미터 구함
		 	INFO : org.zerock.aop.LogAdvice - -----------------------------
			INFO : org.zerock.aop.LogAdvice - str1 : 123
			INFO : org.zerock.aop.LogAdvice - str2 : 456
			INFO : org.zerock.service.SampleServiceTests - 579
		 */
		/* 결과3 : logTime(), @Around
		 	INFO : org.zerock.aop.LogAdvice - Target : org.zerock.service.SampleServiceImpl@6fdbe764
			INFO : org.zerock.aop.LogAdvice - Param: [123, 456]
			INFO : org.zerock.aop.LogAdvice - -----------------------------
			INFO : org.zerock.aop.LogAdvice - str1 : 123
			INFO : org.zerock.aop.LogAdvice - str2 : 456
			INFO : org.zerock.aop.LogAdvice - TIME: 7
			INFO : org.zerock.service.SampleServiceTests - 579
		 */
	}
	
	//@Test
	public void testAddError() throws Exception {
		log.info(service.doAdd("123", "ABC"));
		
		/* 결과 : logException()
		 	INFO : org.zerock.aop.LogAdvice - -----------------------------
			INFO : org.zerock.aop.LogAdvice - str1 : 123
			INFO : org.zerock.aop.LogAdvice - str2 : ABC
			INFO : org.zerock.aop.LogAdvice - 예외 발생...!!!!!!!!!!!!!
			INFO : org.zerock.aop.LogAdvice - 예외 발생...!!!!!!!!!!!!! : java.lang.NumberFormatException: For input string: "ABC"
		 */
	}
}










