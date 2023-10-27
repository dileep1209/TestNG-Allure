package com.TestNG.Tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoTesting{
	
	public static WebDriver driver;
	
	public static void main(String[] args) {
		
		driver = new ChromeDriver();
		
		driver.get("https://magento.softwaretestingboard.com/");
		
		List<WebElement> elements = new ArrayList<>();

		elements.add(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/nav/ul/li[1]/a")));
		elements.add(driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/a/img")));
		elements.add(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/nav/ul/li[6]/a")));

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		for (WebElement element : elements) {
			jse.executeScript("arguments[0].setAttribute('style','border: solid 5px red');", element);
		}

		
		
		// Refresh the element list
		elements = driver.findElements(By.xpath("/html/body/div[2]/div/div/div[2]/nav/ul/li/a"));
		elements.add(driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/a/img")));
		elements.add(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/nav/ul/li[6]/a")));

		for (WebElement element : elements) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div/div[2]/nav/ul/li/a")));
		      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/header/div[2]/a/img")));
		      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div/div[2]/nav/ul/li[6]/a")));
		    element.click();
		}

		  
	}
}