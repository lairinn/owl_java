package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
 * Created by ishulga on 18.05.2018.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {
    app.goTo().home();
    if (app.contact().list().size() == 0) {
      app.contact().createContact(new ContactData().
              withFirstName("Ivan").withLastName("Ivanov").withAddress("St.Petersburg, street Street, house 1").withHome("+78126666666").withEmail("example@test.ru").withGroup("test1"), true);
    }
  }

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line !=null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contact = (List<ContactData>) xstream.fromXML(xml);
    return  contact.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
  }

  @Test (dataProvider = "validContacts")

  public void testContactModification(ContactData contact) {
    Contacts before = app.contact().all();
    app.goTo().contactMod();
    ContactData modifiedContact = before.iterator().next();
    //ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Ivanna").withLastName("Ivanova").withAddress("St.Petersburg, street Street, house 1").
            //withHome("+78126666666").withEmail("example@test.ru");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }



}

