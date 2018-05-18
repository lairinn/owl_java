package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by ishulga on 18.05.2018.
 */
public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    app.getNavigationHelper().gotoHome();
    app.getNavigationHelper().gotoContactMod();
    app.getContactHelper().fillAddress("Moscow, prospect Prospect, flat");
    app.getContactHelper().submitContactUpdate();
  }
}
