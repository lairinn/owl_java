package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NameData;

public class ContactCreationTests extends TestBase {



    @Test
    public void testContactCreation() {
        app.gotoCreateContact();
        app.fillName(new NameData("Ivan", "Ivanov"));
        app.fillAddress("St.Petersburg, street Street, house 1");
        app.fillPhone("+78126666666");
        app.fillMail("example@test.ru");
        app.submitContactCreation();
    }


}
