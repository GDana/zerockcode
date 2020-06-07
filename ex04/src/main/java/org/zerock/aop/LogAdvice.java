package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect	//해당 클래스의 객체가 Aspect를 구현한 것임을 나타내는 어노테이션
@Log4j	
@Component	//AOP와는 관계가 없지만 빈(Bean)으로 인식하기 위해 사용
public class LogAdvice {

	@Before("execution(* org.zerock.service.SampleService*.*(..))")	
	public void logBefore() {
		log.info("-----------------------------");
		
		/* @Before("execution(* org.zerock.service.SampleService*.*(..))")	
		 * 
		 * BeforeAdvice를 구현한 메서드에 추가
		 * @After, @AfterReturning, @AfterThrowing, @Around 역시 동일하다.
		 * 어떤 위치에 Advice를 적용할 것인지를 결정하는 Pointcut이다.
		 */
	}
	
	@Before("execution(* org.zerock.service.SampleService*.doAdd(String, String)) && args(str1, str2)")	
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1 : " + str1);
		log.info("str2 : " + str2);
		
		/*  
		 * @Before 설정시 arg를 이용하여 간단히 파라미터를 구할 수 있다
		 */
	}
	
	@AfterThrowing(pointcut = "execution(* org.zerock.service.SampleService*.*(..))", throwing="exception")
	public void logException(Exception exception) {
		log.info("예외 발생...!!!!!!!!!!!!!");
		log.info("예외 발생...!!!!!!!!!!!!! : " + exception);
	}
	
	@Around("execution(* org.zerock.service.SampleService*.*(..))")
	public Object logTime( ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();
		
		log.info("Target : " + pjp.getTarget());
		log.info("Param: " + Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		log.info("TIME: " + (end - start));
		
		return result;
	}
}










