

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginLogout {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://s1.demo.opensourcecms.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLoginLogout() throws Exception {
    driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-login.php");
    String login = "opensourcecms";
    driver.findElement(By.cssSelector("#user_login")).clear();
    driver.findElement(By.cssSelector("#user_login")).sendKeys(login);
    driver.findElement(By.cssSelector("#user_pass")).clear();
    driver.findElement(By.cssSelector("#user_pass")).sendKeys(login);
    driver.findElement(By.cssSelector("#wp-submit")).click();
    String howdy = "Howdy, opensourcecms";
    try {
      assertTrue(isElementPresent(By.cssSelector("h2")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertTrue(isElementPresent(By.cssSelector("#wp-admin-bar-my-account > a.ab-item:contains(" + howdy + ")")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Howdy, opensourcecms")).click();
    driver.findElement(By.linkText("Howdy, opensourcecms")).click();
    driver.findElement(By.linkText("Log Out")).click();
    try {
      assertTrue(isElementPresent(By.cssSelector("p.message")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("#user_login")).clear();
    driver.findElement(By.cssSelector("#user_login")).sendKeys(login);
    driver.findElement(By.cssSelector("#user_pass")).clear();
    driver.findElement(By.cssSelector("#user_pass")).sendKeys(login);
    driver.findElement(By.id("wp-submit")).click();
  }

  @After
  public void tearDown() throws Exception {
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

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
