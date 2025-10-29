package org.baseUtils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.IListeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listener extends Hooks implements ITestListener{
	
	ExtentTest test;
	WebDriver driver;
	
	ExtentReports extent = ExtendReporterNG.extentReporter();
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			test.addScreenCaptureFromPath(getScreenShot(driver, result.getMethod().getMethodName()), result.getMethod().getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
