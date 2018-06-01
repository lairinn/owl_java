package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by ishulga on 16.05.2018.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new")))
    {
      return;
    }
    click(By.linkText("groups"));
  }

  public void gotoCreateContact() {
    click(By.linkText("add new"));
  }

  public void gotoContactMod() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void gotoHome() {
    if(isElementPresent(By.id("maintable")))
    {
      return;
    }
    click(By.linkText("home"));
  }
}
