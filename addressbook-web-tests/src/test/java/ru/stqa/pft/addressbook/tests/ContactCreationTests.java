package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.NameData;

public class ContactCreationTests extends TestBase {



    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoCreateContact();
      app.getContactHelper().fillName(new ContactData("Ivan", "Ivanov", "St.Petersburg, street Street, house 1", "+78126666666", "example@test.ru" ));
      app.getContactHelper().fillAddress(new ContactData("St.Petersburg, street Street, house 1"));
      app.getContactHelper().fillPhone(new ContactData("+78126666666"));
      app.getContactHelper().fillMail(new ContactData("example@test.ru"));
        app.getContactHelper().submitContactCreation();
    }


}

