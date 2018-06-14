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
public class ContactPhoneTests extends TestBase {

  @Test
  public void testContactPhones () {
    app.getNavigationHelper().gotoHome();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInfoFromEditForm)));
    //assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    //assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
    //assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));

  }

  private String mergePhones(ContactData contact) {
    Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(),contact.getWorkPhone()).
            stream().filter((s) -> !s.equals("")).map(ContactPhoneTests::cleaned).
            collect(Collectors.joining("\n"));
    return null;
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
  }


