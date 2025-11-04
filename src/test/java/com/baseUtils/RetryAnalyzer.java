package com.baseUtils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int retryCount = 0;
	private static int maxRetryCount = 2;

	@Override
	public boolean retry(ITestResult result) {

		if (retryCount < maxRetryCount) {
			retryCount++;
			System.out.println("Retrying " + result.getMethod().getMethodName() + " | Attempt " + (retryCount));
			return true;
		}
		return false;
	}

}
