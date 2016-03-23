package org.jsystem.quickstart;

import jsystem.framework.system.SystemObjectImpl;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class WwwSeleniumCom extends SystemObjectImpl {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  
	  public void init() throws Exception {
		  super.init();
	      report.report("In init method");		          
	   }

	   public void close(){
		   super.close();
	         report.report("In close method");		    
	  }
	   
    public void testOpen22222() throws Exception {
    	driver = new FirefoxDriver();
	    baseUrl = "http://docs.seleniumhq.org/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 	
    }
	  public void test22222() throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.linkText("Selenium Remote Control")).click();
	    driver.findElement(By.linkText("our documentation")).click();
	    driver.findElement(By.linkText("Introduction")).click();
	    driver.findElement(By.linkText("How Selenium RC Works")).click();
	    driver.findElement(By.xpath("//div[@id='side']/div[2]/div/ul/li/ul/li[7]/div")).click();
	    driver.findElement(By.xpath("//div[@id='side']/div[2]/div/ul/li/ul/li[8]/div")).click();
	    driver.findElement(By.xpath("//div[@id='side']/div[2]/div/ul/li/ul/li[9]/div")).click();
	    driver.findElement(By.xpath("//div[@id='side']/div[2]/div/ul/li/ul/li[11]/div")).click();
	    driver.findElement(By.linkText("The Same Origin Policy")).click();
	    driver.findElement(By.cssSelector("a.fn-backref")).click();
	    driver.findElement(By.cssSelector("#id4 > tbody > tr > td.label > a.fn-backref")).click();
	    driver.findElement(By.cssSelector("#id4 > tbody > tr > td.label > a.fn-backref")).click();
	  }

    public void testClose22222() throws Exception {
    	driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    } 	
    }
	  
	  private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }

		  private String closeAlertAndGetItsText() {
		    try {
		      Alert alert = driver.switchTo().alert();
		      if (acceptNextAlert) {
		        alert.accept();
		      } else {
		        alert.dismiss();
		      }
		      return alert.getText();
		    } finally {
		      acceptNextAlert = true;
		    }
		  }
		  
    public String getBaseUrl() {
	    return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
	    this.baseUrl = baseUrl;
	}
}
