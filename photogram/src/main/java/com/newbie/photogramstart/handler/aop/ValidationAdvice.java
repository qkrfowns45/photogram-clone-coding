package com.newbie.photogramstart.handler.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component //RestController,Service 모든 것들이 Component를 상속해서 만들어져 있음
@Aspect
public class ValidationAdvice {

	@Around("execution(* com.newbie.photogramstart.web.api.*Controller.*(..))")
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		System.out.println("web api 컨트롤러=========================");
		//proceedingJoinPoint -> 함수의 모든 곳에 접근할 수 있는 변수
		//함수들보다 먼저 실행
		
		return proceedingJoinPoint.proceed(); //그 함수로 다시 돌아가라는 뜻 함수 실행 null처리하면 실행안됨
	}
	
	@Around("execution(* com.newbie.photogramstart.web.*Controller.*(..))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		System.out.println("web 컨트롤러=========================");
		return proceedingJoinPoint.proceed();
	}
}
