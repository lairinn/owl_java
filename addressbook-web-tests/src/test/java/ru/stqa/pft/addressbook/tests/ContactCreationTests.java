package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoCreateContact();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().
            withFirstName("Ivan").withLastName("Ivanov").withAddress("St.Petersburg, street Street, house 1").withHome("+78126666666"). withEmail("example@test.ru"). withGroup("test1");
    app.contact().create(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

   // int max = 0;
   // for (ContactData g : after) {
    //  if (g.getId() > max) {
    //    max = g.getId();
   //   }
   // }
   //contact.setId(max); версия с циклом

        //before.add(contact);
    //Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
        //before.sort(byId);
    //after.sort(byId);
    //Assert.assertEquals(before,after);


  }




}

