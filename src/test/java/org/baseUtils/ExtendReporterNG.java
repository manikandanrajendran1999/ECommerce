package org.baseUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporterNG {
	
	public static ExtentReports extentReporter() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//index.html");
		reporter.config().setReportName("ECommerce report");
		reporter.config().setDocumentTitle("Result");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("System", "Mac");
		extent.setSystemInfo("Tester", "Manikandan");
		return extent;
	}

}
