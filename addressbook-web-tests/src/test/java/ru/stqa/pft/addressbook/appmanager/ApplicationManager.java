package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.NameData;

import java.util.concurrent.TimeUnit;

/**
 * Created by ishulga on 16.05.2018.
 */
public class ApplicationManager {
  FirefoxDriver wd;

  public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

  public void init() {
    wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    login("admin", "secret");
  }

  private void login(String username, String password) {
      wd.get("http://localhost/addressbook/group.php");
      wd.findElement(By.name("user")).click();
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  public void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
  }

  public void submitGroupCreation() {
    wd.findElement(By.name("submit")).click();
  }

  public void fillGroupForm(GroupData groupData) {
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
    wd.findElement(By.xpath("//div[@id='content']/form")).click();
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).clear();
    wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).clear();
    wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  public void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }

  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  public void submitContactCreation() {
      wd.findElement(By.cssSelector("body")).click();
      wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void fillMail(String email) {
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(email);
  }

  public void fillPhone(String homephone) {
      wd.findElement(By.name("home")).click();
      wd.findElement(By.name("home")).clear();
      wd.findElement(By.name("home")).sendKeys(homephone);
      wd.findElement(By.name("theform")).click();
  }

  public void fillAddress(String address) {
      wd.findElement(By.name("theform")).click();
      wd.findElement(By.name("address")).click();
      wd.findElement(By.name("address")).clear();
      wd.findElement(By.name("address")).sendKeys(address);
  }

  public void fillName(NameData nameData) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(nameData.getFirstName());
      wd.findElement(By.name("theform")).click();
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(nameData.getLastName());
  }

  public void gotoCreateContact() {
      wd.findElement(By.linkText("add new")).click();
  }

  public void stop() {
    wd.quit();
  }

  public void deleteSelectedGroups() {
      wd.findElement(By.xpath("//div[@id='content']/form/input[5]")).click();
  }

  public void selectGroup() {
      if (!wd.findElement(By.xpath("//div[@id='content']/form/span[2]/input")).isSelected()) {
          wd.findElement(By.xpath("//div[@id='content']/form/span[2]/input")).click();
      }
  }
}
