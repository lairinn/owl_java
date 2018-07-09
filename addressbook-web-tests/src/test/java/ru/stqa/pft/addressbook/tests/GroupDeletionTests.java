package ru.stqa.pft.addressbook.tests;


import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
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


public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
     app.group().create(new GroupData().withName("test"));
   }
  }

 // @DataProvider
 // public Iterator<Object[]> validGroups() throws IOException {
  //  BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
//    String xml = "";
  //  String line = reader.readLine();
 //   while (line !=null) {
  //    xml += line;
  //      line = reader.readLine();
  //  }
     //   XStream xstream = new XStream();
   // xstream.processAnnotations(GroupData.class);
   // List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
   // return  groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();

 // }

  @Test
  public void testGroupDeletion() {
   Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
     app.group().delete(deletedGroup);
    assertEquals(app.group().сount(), before.size() - 1);
    Groups after = app.db().groups();


    //before.remove(deletedGroup);
    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupListInUI();
    //assertEquals(before, after);

  }




}
