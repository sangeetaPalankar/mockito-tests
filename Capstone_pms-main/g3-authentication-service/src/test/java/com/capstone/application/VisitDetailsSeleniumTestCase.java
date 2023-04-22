package com.capstone.application;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;

public class VisitDetailsSeleniumTestCase {

	@Test
	public void addVisitDetails() {
		System.setProperty("webdriver.chrome.driver", "F:\\chrome\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(options);
		// WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:4200/nurse/dashboard");
		// driver.get("http://www.google.com");

// WebElement addinfobutton = driver.findElement(By.className("mdc-button__label"));
		WebElement addinfobutton = driver.findElement(By.xpath("(//span[normalize-space()='Add Info'])[1]"));
		addinfobutton.isDisplayed();
		addinfobutton.isEnabled();
		addinfobutton.click();

		WebElement height = driver.findElement(By.id("ht"));
		height.isDisplayed();
		height.isEnabled();
		height.sendKeys("7");

		WebElement weight = driver.findElement(By.id("wt"));
		weight.isDisplayed();
		weight.isEnabled();
		weight.sendKeys("67");

		WebElement respirationRate = driver.findElement(By.id("rrt"));
		respirationRate.isDisplayed();
		respirationRate.isEnabled();
		respirationRate.sendKeys("20");

		WebElement bodyTemperature = driver.findElement(By.id("bt"));
		bodyTemperature.isDisplayed();
		bodyTemperature.isEnabled();
		bodyTemperature.sendKeys("37");

		WebElement bloodPressureSystolic = driver.findElement(By.id("bps"));
		bloodPressureSystolic.isDisplayed();
		bloodPressureSystolic.isEnabled();
		bloodPressureSystolic.sendKeys("120");

		WebElement bloodPressureDiastolic = driver.findElement(By.id("bpd"));
		bloodPressureDiastolic.isDisplayed();
		bloodPressureDiastolic.isEnabled();
		bloodPressureDiastolic.sendKeys("87");
//		
//		Select drpCountry = new Select(driver.findElement(By.id("ag")));
//		drpCountry.selectByVisibleText("food");

//  
//  WebElement allergies = driver.findElement(By.id("mat-input-1"));
//  allergies.isDisplayed();
//  allergies.isEnabled();
//  allergies.sendKeys("1234");
//  
		WebElement notes = driver.findElement(By.id("nt"));
		notes.isDisplayed();
		notes.isEnabled();
		notes.sendKeys("ghjghjgfd");

		WebElement saveButton = driver.findElement(By.xpath("(//span[normalize-space()='Save'])[1]"));
		saveButton.isDisplayed();
		saveButton.isEnabled();
		WebElement closeButton = driver.findElement(By.xpath("(//span[contains(text(),'Close')])[1]"));
		closeButton.isDisplayed();
		closeButton.isEnabled();
		closeButton.click();
	}
}
