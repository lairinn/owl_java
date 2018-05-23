package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by ishulga on 18.05.2018.
 */
public class ContactModificationTests extends TestBase{

  @Test

  public void testContactModification () {
    app.getNavigationHelper().gotoHome();
    app.getNavigationHelper().gotoContactMod();
app.getContactHelper().fillName(new ContactData ("Ivanna", "Ivanova", "St.Petersburg, street Street, house 1", "+78126666666", "example@test.ru"));
app.getContactHelper().submitContactUpdate();
  }
}
