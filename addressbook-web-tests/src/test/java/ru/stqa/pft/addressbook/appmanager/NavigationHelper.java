package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by ishulga on 16.05.2018.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void gotoCreateContact() {
    click(By.linkText("add new"));
}

  public void gotoContactMod() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
      }

  public void gotoHome() {
    click(By.linkText("home"));
  }
}
