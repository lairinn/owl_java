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
public class ContactAddressTest extends TestBase {

  @Test

  public void testContactAddress() {
    app.getNavigationHelper().gotoHome();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllMails(), equalTo(mergeAddress(contactInfoFromEditForm)));

    //assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
   // assertThat(contact.getAddress2(), equalTo(contactInfoFromEditForm.getAddress2()));

  }
  private String mergeAddress(ContactData contact) {
    Arrays.asList(contact.getAddress(), contact.getAddress2()).stream().filter((s) -> !s.equals("")).map(ContactAddressTest::cleaned).
            collect(Collectors.joining("\n"));
    return null;
  }

  public static String cleaned(String address){
    return address.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
