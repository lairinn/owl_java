package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

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
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return  contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
  }

  @Test (dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    Groups groups = app.db().groups();
    app.goTo().createContact();
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/image.png");
    //ContactData contact = new ContactData().
           // withFirstName("Ivan").withLastName("Ivanov").withAddress("St.Petersburg, street Street, house 1").withHome("+78126666666").
          //  withEmail("example@test.ru"). withGroup("test1").withPhoto(photo);
    app.contact().create(contact, true);
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    verifyContactListInUI();

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

  //@Test

 // public void testCurrentDir() {
   // File currentDir = new File(".");
  //  System.out.println(currentDir.getAbsolutePath());
  //  File photo = new File("src/test/resources/image.png");
  //  System.out.println(photo.getAbsolutePath());
  //  System.out.print(photo.exists());
 // }



}

