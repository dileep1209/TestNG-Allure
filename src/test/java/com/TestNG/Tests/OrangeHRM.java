package com.TestNG.Tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class OrangeHRM {
	
	WebDriver driver;
	
	@Parameters("browserName")
	
	@BeforeTest
	public void InitialiseBrowser(@Optional("chrome") String browserName) {
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(); 
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		default:
			System.err.println("Browser name is invalid");
			break;
		}
		driver.manage().window().maximize();
	}
	
	@Parameters("sleepTime")
	@AfterTest
	public void Teardown(Long sleepTime) throws Exception {
		System.out.println(sleepTime);
		Thread.sleep(sleepTime);
		driver.quit();
	}
	
	  @Parameters("url")
	  @Test 
	  public void LaunchApp() { 
		 // WebDriverManager.chromedriver().setup();
	  //driver = new ChromeDriver(); 
	  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); 
	  System.out.println(driver.getTitle());
	  }
	  
	  @Parameters({"username" , "password"})
	  @Test 
	  public void Login(String userName, String password) {
	  
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
	  driver.findElement(By.name("username")).sendKeys(userName);
	  driver.findElement(By.name("password")).sendKeys(password);
	  driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
	  System.out.println("Login Success");
	 
	  }
	  
	  @Test
	  public void NavigateToMyInfo() {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[6]/a/span")));
		  driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[6]/a/span")).click();
		  System.out.println("Navigated to My Info");
	  }
	  
	  @Test
	  public void VerifyMyInfo() {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[1]/div[1]")));
		 System.out.println(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[1]/div[1]")).isDisplayed());
		 boolean actualValue = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[1]/div[1]")).isDisplayed();
		 assertTrue(actualValue);
		 System.out.println(actualValue);
	  }
	  @Test
	  public void VerifyLogin() {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[2]/ul/li/span/p")));
		  WebElement element = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[2]/ul/li/span/p"));
		  assertTrue(element.isDisplayed());
		  //assertTrue(element.getText().startsWith("Paul"));
		  //assertTrue(element.getText().endsWith("o L"));
		  assertTrue(element.getText().startsWith("Jyoti"));
		  //System.out.println(element.isDisplayed());
		  //System.out.println(element.getText());
	  }
}
