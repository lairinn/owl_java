package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.NameData;

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

  public void fillMail(String email) {
    type(By.name("email"), email);

  }

  public void fillPhone(String homephone) {
    type(By.name("home"), homephone);
    click(By.name("theform"));
  }

  public void fillAddress(String address) {
    click(By.name("theform"));
    type(By.name("address"), address);

  }

  public void fillName(NameData nameData) {
    type(By.name("firstname"), nameData.getFirstName());
    click(By.name("theform"));
    type(By.name("lastname"), nameData.getLastName());
  }
}
