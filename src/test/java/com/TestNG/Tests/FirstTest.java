package com.TestNG.Tests;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstTest {

	@Test
	public void TestGoogle() throws Exception {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		

		driver.manage().window().maximize();
		driver.get("Https://www.google.com");
		driver.findElement(By.name("q")).sendKeys("facebook", Keys.ENTER);
		String expectedTitle = "facebook - Google Search";
		String actualTitle = driver.getTitle();
		assertEquals(expectedTitle, actualTitle, "Title is mismatched");
		Thread.sleep(5000);
		driver.quit();
		
	}
	
	@Test
	public void TestFacebook() throws Exception {
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		SoftAssert softAssert = new SoftAssert();
		
		driver.get("https://www.facebook.com");
		driver.findElement(By.id("email")).sendKeys("facebook", Keys.ENTER);
		Thread.sleep(10000);
		
		//Title Assertion
		String actualTitle = driver.getTitle(); 
		String expectedTitle = "Log in to Facebook";
		softAssert.assertEquals(expectedTitle, actualTitle, "Title is mismatched");
		
		//URL Assertion
		String expectedURL = "https://www.facebook.com/";
		String actualURL = driver.getCurrentUrl();
		softAssert.assertNotEquals(actualURL, expectedURL, "URL is mismatched");
		
		//Text Assertion
		String expectedText = "";
		String actualText = driver.findElement(By.id("email")).getAttribute("value");
		softAssert.assertNotEquals(expectedText, actualText, "Text is mismatched");
		
		//Error Message
		
		String actualErrorMessage = driver.findElement(By.xpath("(//div[@id=\"error_box\"]/div)[last()]")).getText();
		String expectedErrorMessage = "Invalid username or password";
		softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not matching");
		
		driver.quit();
	}
}
