package com.capstone.application;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SeleniumLoginTestCase {

	@Test
	public void loginSuccessful() {
		System.setProperty("webdriver.chrome.driver", "C:\\Testing Training\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(options);
		// WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:4200/patient/login");
		// driver.get("http://www.google.com");

		WebElement login = driver.findElement(By.cssSelector("#email"));
		login.isDisplayed();
		login.isEnabled();
		login.sendKeys("mrunalbarde2@gmail.com");

		WebElement password = driver.findElement(By.cssSelector(".mat-mdc-form-field-infix.ng-tns-c28-1"));
		password.isDisplayed();
		password.isEnabled();
		password.sendKeys("Mrunal@11");

		WebElement loginbutton = driver.findElement(By.cssSelector(".mat-mdc-button-touch-target"));
		loginbutton.isDisplayed();
		loginbutton.isEnabled();
		loginbutton.click();
	}

//	@Test
//	public void addVisitDetails() {
//		System.setProperty("webdriver.chrome.driver", "D:\\chrome driver for selenium\\chromedriver.exe");
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-allow-origins=*");
//		ChromeDriver driver = new ChromeDriver(options);
//		// WebDriver driver = new ChromeDriver();
//		driver.get("http://localhost:4200/nurse/dashboard");
//		// driver.get("http://www.google.com");
//
//// WebElement addinfobutton = driver.findElement(By.className("mdc-button__label"));
//		WebElement addinfobutton = driver.findElement(By.xpath("(//span[contains(text(),'Add Info')])[1]"));
//		addinfobutton.isDisplayed();
//		addinfobutton.isEnabled();
//		addinfobutton.click();
//
//		WebElement height = driver.findElement(By.id("ht"));
//		height.isDisplayed();
//		height.isEnabled();
//		height.sendKeys("7");
//
//		WebElement weight = driver.findElement(By.id("wt"));
//		weight.isDisplayed();
//		weight.isEnabled();
//		weight.sendKeys("67");
//
//		WebElement respirationRate = driver.findElement(By.id("rrt"));
//		respirationRate.isDisplayed();
//		respirationRate.isEnabled();
//		respirationRate.sendKeys("20");
//
//		WebElement bodyTemperature = driver.findElement(By.id("bt"));
//		bodyTemperature.isDisplayed();
//		bodyTemperature.isEnabled();
//		bodyTemperature.sendKeys("37");
//
//		WebElement bloodPressureSystolic = driver.findElement(By.id("bps"));
//		bloodPressureSystolic.isDisplayed();
//		bloodPressureSystolic.isEnabled();
//		bloodPressureSystolic.sendKeys("120");
//
//		WebElement bloodPressureDiastolic = driver.findElement(By.id("bpd"));
//		bloodPressureDiastolic.isDisplayed();
//		bloodPressureDiastolic.isEnabled();
//		bloodPressureDiastolic.sendKeys("87");
//
////  
////  WebElement allergies = driver.findElement(By.id("mat-input-1"));
////  allergies.isDisplayed();
////  allergies.isEnabled();
////  allergies.sendKeys("1234");
////  
//		WebElement notes = driver.findElement(By.id("notes"));
//		notes.isDisplayed();
//		notes.isEnabled();
//		notes.sendKeys("ghjghjgfd");
//
//		WebElement saveButton = driver.findElement(By.xpath("(//span[normalize-space()='Save'])[1]"));
//		saveButton.isDisplayed();
//		saveButton.isEnabled();
//		WebElement closeButton = driver.findElement(By.xpath("(//span[contains(text(),'Close')])[1]"));
//		closeButton.isDisplayed();
//		closeButton.isEnabled();
//		closeButton.click();
//	}
//
//	@Test
//	public void physicianLogin() {
//		System.setProperty("webdriver.chrome.driver", "D:\\chrome driver for selenium\\chromedriver.exe");
////  saveButton.click();
//
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-allow-origins=*");
//		ChromeDriver driver = new ChromeDriver(options);
//		// WebDriver driver = new ChromeDriver();
//		driver.get("http://localhost:4200");
//		// driver.get("http://www.google.com");
//
//		WebElement login = driver.findElement(By.cssSelector(
//				"a[class='login mdc-button mdc-button--outlined mat-mdc-outlined-button mat-accent mat-mdc-button-base'] span[class='mdc-button__label']"));
//		login.isDisplayed();
//		login.isEnabled();
//		login.click();
//
//		WebElement email = driver.findElement(By.id("username"));
//		email.isDisplayed();
//		email.isEnabled();
//		email.sendKeys("krunal.zodape@gmail.com");
//
//		WebElement pwd = driver.findElement(By.id("password"));
//		pwd.isDisplayed();
//		pwd.isEnabled();
//		pwd.sendKeys("abcd@123");
//
//		WebElement continue1 = driver.findElement(By.xpath("(//button[normalize-space()='Continue'])[1]"));
//		continue1.isDisplayed();
//		continue1.isEnabled();
//		continue1.click();
//		driver.get("http://localhost:4200/physician/dashboard");
//
////ChromeDriver d = new ChromeDriver();
//		WebElement update = driver.findElement(By.cssSelector(
//				" body > app-root:nth-child(1) > app-default:nth-child(2) > mat-sidenav-container:nth-child(1) > mat-sidenav-content:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-dashboard:nth-child(2) > table:nth-child(3) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(6) > button:nth-child(1) > span:nth-child(5)"));
//		update.isDisplayed();
//		update.isEnabled();
//
//// WebDriverWait wait=new WebDriverWait(WebDriver driver,10);
//// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Update')])[2])"));
////  update.click();body > app-root:nth-child(1) > app-default:nth-child(2) > mat-sidenav-container:nth-child(1) > mat-sidenav-content:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-dashboard:nth-child(2) > table:nth-child(3) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(6) > button:nth-child(1) > span:nth-child(5)body > app-root:nth-child(1) > app-default:nth-child(2) > mat-sidenav-container:nth-child(1) > mat-sidenav-content:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-dashboard:nth-child(2) > table:nth-child(3) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(6) > button:nth-child(1) > span:nth-child(5)
//
//		driver.findElement(By.xpath("//button[contains(text(),'Update')]")).click();
//
//	}

}
