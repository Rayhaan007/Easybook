package com.easybus.generic;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ListenerImplementation extends BaseClass implements ITestListener{
	ExtentReports extent=ExtentReportsNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		 test=extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		String easybook=result.getMethod().getMethodName();
		String path="./failureScreenShot/"+easybook+".png";
		try {
			extentTest.get().addScreenCaptureFromPath(path);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		TakesScreenshot ts=(TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile=new File(path);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test Got Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
