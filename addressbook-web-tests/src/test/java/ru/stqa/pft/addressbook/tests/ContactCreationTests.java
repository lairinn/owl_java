package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoCreateContact();
    app.getContactHelper().fillName(new ContactData("Ivan", "Ivanov", "St.Petersburg, street Street, house 1", "+78126666666", "example@test.ru"));
    app.getContactHelper().submitContactCreation();
  }


}

