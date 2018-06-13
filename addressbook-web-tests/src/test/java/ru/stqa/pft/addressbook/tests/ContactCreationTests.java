package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoCreateContact();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Ivan", "Ivanov", "St.Petersburg, street Street, house 1", "+78126666666", "example@test.ru", "test1");
    app.getContactHelper().fillName(contact, true);
    app.getContactHelper().submitContactCreation();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);


   // int max = 0;
   // for (ContactData g : after) {
    //  if (g.getId() > max) {
    //    max = g.getId();
   //   }
   // }
   //contact.setId(max); версия с циклом

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }


}

