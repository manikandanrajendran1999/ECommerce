package com.testCases;

import org.testng.annotations.Test;

import com.baseUtils.DriverManager;
import com.baseUtils.ParallelBaseClass;

public class ParallelEcexution2 extends ParallelBaseClass {
	
	@Test
	private void tc4() {
		DriverManager.getDriver().get("https://google.com");
		System.out.println("tc4");
		System.out.println("Thread : " + Thread.currentThread().getId());
	}
	
	@Test
	private void tc5() {
		DriverManager.getDriver().get("https://facebook.com");
		System.out.println("tc5");
		System.out.println("Thread : " + Thread.currentThread().getId());
	}
	
	@Test
	private void tc6() {
		DriverManager.getDriver().get("https://digival-staging-nginx-ds-yk25kmkzeq-el.a.run.app/staging1-dcweb/frameworkPortfolio");
		System.out.println("tc6");
		System.out.println("Thread : " + Thread.currentThread().getId());
	}

}
