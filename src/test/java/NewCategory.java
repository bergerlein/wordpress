
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class NewCategory {
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
  public void testNewCategory() throws Exception {
    driver.get(baseUrl + "/wordpress/wp-admin/post.php?");
    driver.findElement(By.cssSelector("#wp-admin-bar-new-content > a.ab-item > span.ab-icon")).click();
    driver.findElement(By.cssSelector("#title")).clear();
    driver.findElement(By.cssSelector("#title")).sendKeys("${enterTitle}");
    driver.findElement(By.cssSelector("#content")).clear();
    driver.findElement(By.cssSelector("#content")).sendKeys("${enterText}");
    driver.findElement(By.cssSelector("#category-add-toggle")).click();
    String enterToggle = "WeAreTesters";
    driver.findElement(By.cssSelector("#newcategory")).clear();
    driver.findElement(By.cssSelector("#newcategory")).sendKeys(enterToggle);
    driver.findElement(By.xpath("//input[@id='category-add-submit']")).click();
    driver.findElement(By.cssSelector("#publish")).click();
    try {
      assertTrue(isElementPresent(By.id("message")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertTrue(isElementPresent(By.cssSelector("#category-2 > label.selectit")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
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
