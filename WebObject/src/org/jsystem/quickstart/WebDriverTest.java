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

public class WebDriverTest extends SystemObjectImpl {
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
    
    public void testOpen12212() throws Exception {
    	driver = new FirefoxDriver();
        //baseUrl = "http://9.111.71.133/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

	public void test12212() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals("System dump exists in FLASH!", closeAlertAndGetItsText());
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | NavTreeFrame | ]]
    driver.findElement(By.linkText("IBM Networking OS RackSwitch G8264")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | relative=up | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | Toolbar Frame | ]]
    driver.findElement(By.xpath("//area[9]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | relative=up | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | NavTreeFrame | ]]
    driver.findElement(By.linkText("IBM Networking OS RackSwitch G8264")).click();
    driver.findElement(By.linkText("IBM Networking OS RackSwitch G8264")).click();
    driver.findElement(By.linkText("Layer 3")).click();
    driver.findElement(By.linkText("OSPFv3 Routing Protocol")).click();
    driver.findElement(By.linkText("OSPFv3 Interfaces")).click();
    driver.findElement(By.xpath("//area[3]")).click();
  }

  public void tesClose12212() throws Exception {
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
