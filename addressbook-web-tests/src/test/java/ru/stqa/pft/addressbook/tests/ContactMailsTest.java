package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ishulga on 14.06.2018.
 */
public class ContactMailsTest extends TestBase {

@Test

  public void testContactMails() {
  app.getNavigationHelper().gotoHome();
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
