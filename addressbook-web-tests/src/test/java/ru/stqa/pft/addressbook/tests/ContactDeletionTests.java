package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by ishulga on 18.05.2018.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {
    app.getNavigationHelper().gotoHome();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().
              withFirstName("Ivan").withLastName("Ivanov").withAddress("St.Petersburg, street Street, house 1").
              withHome("+78126666666").withEmail("example@test.ru"). withGroup("test1"), true);
    }
  }

  @Test

  public void testContactDeletion() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.getNavigationHelper().gotoContactMod();
    app.contact().delete();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));

  }

}
