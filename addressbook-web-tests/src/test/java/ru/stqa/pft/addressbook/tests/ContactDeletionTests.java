package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by ishulga on 18.05.2018.
 */
public class ContactDeletionTests extends TestBase{

  @Test

  public void testContactDeletion () {
    app.getNavigationHelper().gotoHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "St.Petersburg, street Street, house 1", "+78126666666", "example@test.ru", "test1"), true);
    }
    app.getNavigationHelper().gotoContactMod();
    app.getContactHelper().deleteContact();
  }

}
