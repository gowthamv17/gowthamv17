package org.cloudblm;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase {

	public static WebDriver driver;
	public static ExtentTest test;
	public static ExtentReports report;
	
	public String path = "C:\\selenium failed cases\\image\\";
	public static String reportPath = "D:\\Selenium Report\\Extent\\Smoke Test Report.html";
	public static void main(String[] args) throws InterruptedException {
		
		
			report = new ExtentReports(reportPath);
			test = report.startTest("BLM Login");	
	
			test.log(LogStatus.INFO, "BLM Login Initiated");
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
			
			
			driver.get("http://192.168.2.14/blm/login");
			driver.manage().window().maximize();
			
			String actualtitle = driver.getTitle();
			String exptitle = "CloudBLM";
			
			if(actualtitle.equalsIgnoreCase(exptitle)) {
				
				test.log(LogStatus.PASS,"Browser Title Matched");
			}
			else {
				test.log(LogStatus.FAIL, "Browser Not Launched");
			}
			
			WebElement emailid = driver.findElement(By.id("username"));
			emailid.sendKeys("blmadmin@srinsofttech.com");
			WebElement password = driver.findElement(By.id("userkey"));
			password.sendKeys("Welcome@123");
			WebElement signin = driver.findElement(By.xpath("//button[text()='Sign In']"));
			signin.click();

			Thread.sleep(2000);
			

	/*	catch (Exception e) {
			// TODO: handle exception
			WebElement invalidcredential = driver.findElement(By.xpath("//p[text()='Invalid Credentials!!']"));
			String text = invalidcredential.getText();
			System.out.println(text);
			Thread.sleep(2000);
			if (text.equals(invalidcredential)) {
				System.out.println("Invalid Login");
			} else {
				System.out.println("Valid Login");
			}
			e.printStackTrace();
		}*/

		List<WebElement> li = driver.findElements(By.xpath("//div[@class='cblm-main-navigation']"));
		for (WebElement newlist : li) {
			String lists = newlist.getText();
			System.out.println(lists);
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()=' Business Units '] ")).click();

		// To get list of business units
		List<WebElement> bulist = driver.findElements(By.xpath("//div[contains(@id,'card')]"));
		Thread.sleep(3000);
		int size = bulist.size();
		System.out.println(size);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Create']")).click();
		report.endTest(test);
		report.flush();
	//to get validation texts
		driver.findElement(By.xpath("//button[text()='Create']")).click();
		WebElement danger = driver.findElement(By.xpath("(//div[@class='text-danger'])[1]"));
		System.out.println(danger.getText());
		
	}
	

}

