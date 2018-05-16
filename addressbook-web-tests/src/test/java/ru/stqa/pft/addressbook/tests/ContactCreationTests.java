package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NameData;

public class ContactCreationTests extends TestBase {



    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoCreateContact();
        app.getContactHelper().fillName(new NameData("Ivan", "Ivanov"));
        app.getContactHelper().fillAddress("St.Petersburg, street Street, house 1");
        app.getContactHelper().fillPhone("+78126666666");
        app.getContactHelper().fillMail("example@test.ru");
        app.getContactHelper().submitContactCreation();
    }


}
