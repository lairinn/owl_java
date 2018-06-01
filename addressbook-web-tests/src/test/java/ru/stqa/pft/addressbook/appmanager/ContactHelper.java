package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by ishulga on 16.05.2018.
 */
public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }


  public void submitContactCreation() {
    click(By.cssSelector("body"));
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }


  public void fillName(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("address"), contactData.getAddress());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void submitContactUpdate() {
    click(By.name("update"));
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }
}
