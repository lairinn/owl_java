package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by ishulga on 16.05.2018.
 */
public class ApplicationManager {


  private final Properties properties;
  WebDriver wd;

  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private String browser;
  private DbHelper dbHelper;
  private RestHelper restHelper;

  public ApplicationManager(String browser)  {
    this.browser = browser;
        properties = new Properties();

  }

  public static boolean isAlertPresent(WebDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    dbHelper = new DbHelper();

      if (Objects.equals(browser, BrowserType.FIREFOX)) {
      wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    } else if (Objects.equals(browser, BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (Objects.equals(browser, BrowserType.IE)) {
        wd = new InternetExplorerDriver();
    }
      // wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }


  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public RestHelper rest() {
    if (restHelper == null) {
      restHelper = new RestHelper(this);
    }
    return restHelper;
  }

  public NavigationHelper goTo() {

    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public DbHelper db() {
    return dbHelper;
  }

  public void addToGroup() {
    if (!wd.findElement(By.id("5")).isSelected()) {
      wd.findElement(By.id("5")).click();
    }
    wd.findElement(By.name("add")).click();
    wd.findElement(By.linkText("group page \"aaaa\"")).click();
  }

  public void deletefromgroup() {
    wd.findElement(By.linkText("group page \"aaaa\"")).click();
    wd.findElement(By.id("container")).click();
    if (!wd.findElement(By.id("5")).isSelected()) {
      wd.findElement(By.id("5")).click();
    }
    wd.findElement(By.name("remove")).click();
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }
}
