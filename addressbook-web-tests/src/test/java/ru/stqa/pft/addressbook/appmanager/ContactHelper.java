package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by ishulga on 16.05.2018.
 */
public class ContactHelper extends HelperBase {


  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }


  public void submitContactCreation() {
    click(By.cssSelector("body"));
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillMail(ContactData emailData) {
    click(By.name("theform"));
     type(By.name("firstname"), emailData.getEmail());

  }

  public void fillPhone(ContactData homePhoneData) {
    click(By.name("theform"));
    type(By.name("home"), homePhoneData.getHomePhone());

  }

  public void fillAddress(ContactData addressData) {
    click(By.name("address"));
    type(By.name("address"), addressData.getAddress());

  }

  public void fillName(ContactData nameData) {
    type(By.name("firstname"), nameData.getFirstName());
    click(By.name("theform"));
    type(By.name("lastname"), nameData.getLastName());
  }

  public void submitContactUpdate() {
    click(By.name("update"));
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }
}
