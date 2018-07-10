package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactinGroup extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {
    Groups groups = app.db().groups();

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test").withFooter("test1").withHeader("test2"));
      groups = app.db().groups();
    }

    app.goTo().home();
    if (app.db().contacts().size() == 0) {
      app.contact().createContact(new ContactData().
              withFirstName("Ivan").withLastName("Ivanov").withAddress("St.Petersburg, street Street, house 1").withHome("+78126666666").withEmail("example@test.ru"), false);
    }
  }

  @Test

  //public void testAddToGroup() {
  // Groups groupBefore = app.db().groups();
  //Contacts contactBefore = app.db().contacts();
  // ContactData selectedContact = contactBefore.iterator().next();
  // Groups groupSelectedContact = selectedContact.getGroups();
  // GroupData selectedGroup;
  // Iterator<ContactData> iteratorContacts = contactBefore.iterator();

  // while (iteratorContacts.hasNext()) {
  //  if (groupSelectedContact.equals(groupBefore)) {
  //   selectedContact = iteratorContacts.next();
  //   groupSelectedContact = selectedContact.getGroups();
  // } else {
  //    break;
  //   }
  // }
  //  if (groupSelectedContact.equals(groupBefore)) {
  // app.goTo().groupPage();
  //  app.group().create(new GroupData().withName("test"));
  //  }
  //  groupBefore = app.db().groups();
  //  groupSelectedContact = selectedContact.getGroups();
  //  groupBefore.removeAll(groupSelectedContact);

  //  if (groupBefore.size() > 0) {
  //   selectedGroup = groupBefore.iterator().next();
  // } else {
  //    throw new RuntimeException("no groups");
  //  }
  // app.goTo().home();
  // app.contact().selectContact(selectedContact.getId());
  // app.contact().addInGroup(selectedGroup.getId());
  // app.goTo().selectGroup(selectedGroup.getId());
  // ContactData contactAfter = app.db().contact(selectedContact.getId()).iterator().next();
  // Groups groupsContactAfter = contactAfter.getGroups();
  // assertThat(groupsContactAfter, equalTo(groupSelectedContact.withAdded(selectedGroup)));

  // }

  public void testAddToGroup() {
    ContactData contactToAdd = app.db().contacts().iterator().next();
    Groups before = contactToAdd.getGroups();
    app.contact().findFreeGroup(before);
    GroupData groupToAdd = app.contact().findGroupToAdd(before);
    app.contact().addInGroup(contactToAdd, groupToAdd);
    Groups after = app.contact().getContactById(contactToAdd.getId()).getGroups();
    assertThat(after, equalTo(before.withAdded(groupToAdd)));
  }
}

