package com.capstone.application;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;


public class PhysicianLoginSeleniumTestCase {
	
	@Test
	public void physicianLogin() {
		System.setProperty("webdriver.chrome.driver", "F:\\chrome\\chromedriver.exe");
//  saveButton.click();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(options);
		// WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:4200");
		// driver.get("http://www.google.com");

		WebElement login = driver.findElement(By.id(
				"login"));
		login.isDisplayed();
		login.isEnabled();
		login.click();

		WebElement email = driver.findElement(By.cssSelector("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > span:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(2)"));
		email.isDisplayed();
		email.isEnabled();
		email.sendKeys("shree@gmail.com");

		WebElement pwd = driver.findElement(By.xpath("//input[@placeholder='your password']"));
		pwd.isDisplayed();
		pwd.isEnabled();
		pwd.sendKeys("abcd@123");

		WebElement continue1 = driver.findElement(By.xpath("//span[@class='auth0-label-submit']//*[name()='svg']"));
		continue1.isDisplayed();
		continue1.isEnabled();
		continue1.click();
		driver.get("http://localhost:4200/physician/dashboard");
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
}
