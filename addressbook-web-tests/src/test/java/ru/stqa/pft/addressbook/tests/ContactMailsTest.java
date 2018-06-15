package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ishulga on 14.06.2018.
 */
public class ContactMailsTest extends TestBase {

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

  public void testContactMails() {
  app.goTo().home();
  ContactData contact = app.contact().all().iterator().next();
  ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);


  assertThat(contact.getAllMails(), equalTo(mergeMails(contactInfoFromEditForm)));
  //assertThat(contact.getEmail(), equalTo(contactInfoFromEditForm.getEmail()));
  //assertThat(contact.getEmail2(), equalTo(contactInfoFromEditForm.getEmail2()));
  //assertThat(contact.getEmail3(), equalTo(contactInfoFromEditForm.getEmail3()));

}

  private String mergeMails(ContactData contact) {
    Arrays.asList(contact.getEmail(), contact.getEmail2(),contact.getEmail3()).
            stream().filter((s) -> !s.equals("")).map(ContactMailsTest::cleaned).
            collect(Collectors.joining("\n"));
    return null;
  }

  public static String cleaned(String mail){
    return mail.replaceAll("\\s","").replaceAll("[-()]","");
}
}
