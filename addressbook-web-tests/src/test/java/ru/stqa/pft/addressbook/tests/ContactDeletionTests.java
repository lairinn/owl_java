package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by ishulga on 18.05.2018.
 */
public class ContactDeletionTests extends TestBase{

  @Test

  public void testContactDeletion () {
    app.getNavigationHelper().gotoHome();
    app.getNavigationHelper().gotoContactMod();
    app.getContactHelper().deleteContact();
  }

}
