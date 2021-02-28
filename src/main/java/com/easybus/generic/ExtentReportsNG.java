package com.easybus.generic;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	
	static ExtentReports extent;
	public static ExtentReports getReportObject() {
		String path=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("EasyBook Test Result");
		reporter.config().setReportName("Web Automation Result");
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Automation Tester", "SAQUEEB ALI");
		return extent;
	}
	

}
