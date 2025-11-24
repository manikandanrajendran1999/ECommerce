package com.testCases;

import org.testng.annotations.Test;

import com.baseUtils.ParallelBaseClass;

public class ParallelEcexution extends ParallelBaseClass {
	
	@Test
	private void tc1() {
		ParallelBaseClass.getDriver().get("https://google.com");
		System.out.println("tc1");
		System.out.println("Thread : " + Thread.currentThread().getId());
	}
	
	@Test
	private void tc2() {
		ParallelBaseClass.getDriver().get("https://facebook.com");
		System.out.println("tc2");
		System.out.println("Thread : " + Thread.currentThread().getId());
	}
	
	@Test
	private void tc3() {
		ParallelBaseClass.getDriver().get("https://digival-staging-nginx-ds-yk25kmkzeq-el.a.run.app/staging1-dcweb/frameworkPortfolio");
		System.out.println("tc3");
		System.out.println("Thread : " + Thread.currentThread().getId());
	}

}
