package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by ishulga on 25.06.2018.
 */
public class DelContactFromGroup extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {
    Groups groups = app.db().groups();

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("testName").withFooter("FooterTest").withHeader("HeaderTest"));
      groups = app.db().groups();
    }

    app.goTo().home();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Ivan").withLastName("Ivanov").withHome("111").withMobile("222").withWork("333").withEmail("email1").withEmail2("email2").withEmail3("email3").inGroup(groups.iterator().next()), true);
    }
  }



  @Test

  public void testDelContactFromGroup () {
    Contacts contacts = app.db().contacts();
    Iterator<ContactData> iteratorContacts = contacts.iterator();
    ContactData contact = iteratorContacts.next();
    GroupData group = contact.getGroups().iterator().next();
    app.goTo().home();

    while (iteratorContacts.hasNext()) {
      if (contact.getGroups().size() > 0) {
        group = contact.getGroups().iterator().next();
        app.contact().findGroup(group.getId());
        break;
      } else {
        contact = iteratorContacts.next();
      }
    }
    app.contact().selectContact(contact.getId());
    app.contact().delContactFromGroup();
    app.goTo().selectGroup(group.getId());
    Groups groupsContactsAfter = app.db().contact(contact.getId()).iterator().next().getGroups();

    assertThat(groupsContactsAfter, equalTo(contact.getGroups().without(group)));
  }


}
