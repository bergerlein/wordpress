
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import static sun.security.jgss.GSSUtil.login;

public class SeleniumWordpressTest {

    String user = "annaeichberger@hotmail.com";
    String password = "e6hGp&rcD1%FcqNMtCtCNyQ&";
    String enterTag = "iLoveSelenium";
    String enterTitle = "Minimie`s erste Seite";
    String enterText = "Hallo und Willkommen auf Minime`s erster Seite";

    public SeleniumWordpressTest() {
       // System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\geckodriver.exe");
         System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
    }

    // private WebDriver driver;
    private static WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        baseUrl = "http://localhost/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSeleniumWordpressLogin() throws Exception {
        //LOG IN
        driver.get(baseUrl + "/wordpress/wp-login.php?");

        //driver.findElement(By.cssSelector("#user_login")).clear();
        driver.findElement(By.cssSelector("#user_login")).sendKeys(user);

        //driver.findElement(By.cssSelector("#user_pass")).clear();
        driver.findElement(By.cssSelector("#user_pass")).sendKeys(password);
        driver.findElement(By.cssSelector("#wp-submit")).click();
        for (int second = 0;; second++) {
            if (second >= 20) {
                fail("timeout");
            }
            try {
                if ("Dashboard ‹ minime — WordPress".equals(driver.getTitle())) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(3000);
        }
        assertTrue(driver.findElement(By.className("display-name")).getText().matches("user"));
        
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
    driver.findElement(By.linkText("Log Out")).click();
    try {
      assertTrue(isElementPresent(By.cssSelector("p.message")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("#user_login")).sendKeys(user);
    driver.findElement(By.cssSelector("#user_pass")).clear();
    driver.findElement(By.cssSelector("#user_pass")).sendKeys(password);
    driver.findElement(By.id("wp-submit")).click();
  

        //NEW PAGE
        driver.findElement(By.xpath("//li[@id='menu-pages']/a/div[3]")).click();
        // wait for "Pages"
       for (int second = 0;; second++) {
            if (second >= 60) {
                fail("timeout");
            }
            try {
                if ("Pages ‹ minime — WordPress".equals(driver.getTitle())) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(3000);
        }

        driver.findElement(By.xpath("//a[contains(@href, 'post-new.php?post_type=page')]")).click();
        driver.findElement(By.id("title")).sendKeys(enterTitle);
        System.out.println("titel eingegeben");
        driver.findElement(By.cssSelector("#content")).sendKeys(enterText);
        System.out.println("text eingegeben");
        driver.findElement(By.name("publish")).click();
        for (int second = 0;; second++) {
            if (second >= 20) {
                fail("timeout");
                Thread.sleep(3000);
            }
            try {
                if ("Edit Page ‹ minime — WordPress".equals(driver.getTitle())) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(3000);
        }
        try {
            assertTrue(isElementPresent(By.cssSelector("#message > p")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.linkText("View page")).click();
        System.out.println("Seite veröffentlicht");
        try {
            assertTrue(isElementPresent(By.cssSelector("h1.entry-title")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isElementPresent(By.cssSelector("div.entry-content > p")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        //NEW POST
        driver.findElement(By.cssSelector("#wp-admin-bar-new-content > a.ab-item > span.ab-icon")).click();
        System.out.println("neuer POSt");
        Thread.sleep(3000);
        driver.findElement(By.id("title")).sendKeys("enterTitl");
        driver.findElement(By.id("content")).sendKeys("enterText");
        driver.findElement(By.id("publish")).click();
        try {
            assertTrue(isElementPresent(By.cssSelector("#message > p")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        System.out.println("überprüft message");
        driver.findElement(By.id("publish")).click();
        Thread.sleep(1000);
        System.out.println("click view post - erstellten Post anzeigen");
        for (int second = 0;; second++) {
            if (second >= 60) {
                fail("timeout");
            }

            try {
                assertTrue(isElementPresent(By.cssSelector("h1.entry-title")));
                System.out.println("überprüfen ob titel da");
            } catch (Error e) {
                verificationErrors.append(e.toString());
                
            }
            try {
                assertTrue(isElementPresent(By.cssSelector("div.entry-content > p")));
                System.out.println("überprüfe ");

            } catch (Error e) {
                verificationErrors.append(e.toString());

            }
            Thread.sleep(1000);
            
            
            //ENTER TAG
            driver.findElement(By.cssSelector("#wp-admin-bar-new-content > a.ab-item > span.ab-label")).click();
            String enterPost = "mein erster Post";
            driver.findElement(By.cssSelector("#title")).clear();
            driver.findElement(By.cssSelector("#title")).sendKeys(enterPost);
            //driver.findElement(By.id("content")).clear();
            driver.findElement(By.id("content")).sendKeys(enterPost);
            String enterTag = "iLoveSelenium";
            //driver.findElement(By.cssSelector("#new-tag-post_tag")).clear();
            driver.findElement(By.cssSelector("#new-tag-post_tag")).sendKeys(enterTag);
            driver.findElement(By.cssSelector("input.button.tagadd")).click();
            driver.findElement(By.cssSelector("#publish")).click();
            System.out.println("tag erstellt");

            for (second = 0;; second++) {
                if (second >= 60) {
                    fail("timeout");
                }
                try {
                    if (isElementPresent(By.cssSelector("#message > p"))) {
                        break;
                    }
                } catch (Exception e) {
                }
                Thread.sleep(1000);
            }

            driver.findElement(By.name("View Post")).click();
            Thread.sleep(1000);
            System.out.println("tag selenium vorhanden");
            try {
                assertTrue(isElementPresent(By.name("View Post")));
            } catch (Error e) {
                verificationErrors.append(e.toString());
            }
            try {
                assertEquals("TagsiLoveSelenium", driver.findElement(By.cssSelector("span.tags-links")).getText());
            } catch (Error e) {
                verificationErrors.append(e.toString());
            }
        
        
    //NEW CATEGORY
    driver.findElement(By.cssSelector("#wp-admin-bar-new-content > a.ab-item > span.ab-icon")).click();
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
    }
    //EDIT POST
    public void testEditPost() throws Exception {
    driver.get(baseUrl + "/wordpress/wp-admin/post.php");
    driver.findElement(By.cssSelector("span.edit > a")).click();
    String editPost = "Hallo und Willkommen auf meiner Seite und blub";
    driver.findElement(By.cssSelector("#content")).sendKeys(editPost);
    driver.findElement(By.cssSelector("#publish")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("#message > p"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.linkText("View post")).click();
    driver.findElement(By.linkText("View post")).click();
    try {
      assertTrue(isElementPresent(By.cssSelector("div.entry-conten:contains(" + editPost + ")t > p")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("#wp-admin-bar-site-name > a.ab-item")).click();
    driver.findElement(By.linkText("My CMS")).click();
  }


    private boolean isElementPresent(By cssSelector) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

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
