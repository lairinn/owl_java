package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by ishulga on 18.05.2018.
 */
public class ContactModificationTests extends TestBase{

  @Test

  public void testContactModification () {
    app.getNavigationHelper().gotoHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "St.Petersburg, street Street, house 1", "+78126666666", "example@test.ru", "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoContactMod();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Ivanna", "Ivanova", "St.Petersburg, street Street, house 1", "+78126666666", "example@test.ru", null);
    app.getContactHelper().fillName(contact, false);
app.getContactHelper().submitContactUpdate();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}

