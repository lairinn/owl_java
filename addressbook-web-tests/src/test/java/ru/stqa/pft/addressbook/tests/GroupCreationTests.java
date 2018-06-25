package ru.stqa.pft.addressbook.tests;


import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {




  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    //List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        //String[] split = line.split(";");
        //list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
        line = reader.readLine();
      }
      //list.add(new Object[] {new GroupData().withName("test1").withHeader("header 1").withFooter("footer1")});
      // list.add(new Object[] {new GroupData().withName("test2").withHeader("header 2").withFooter("footer2")});
      //list.add(new Object[] {new GroupData().withName("test3").withHeader("header 3").withFooter("footer3")});
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    //return list.iterator();
  }


  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
    Groups before = app.db().groups();
    app.group().create(group);
    assertThat(app.group().сount(), equalTo(before.size() + 1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyGroupListInUI();
       //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    //group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    // before.add(group);
  }

  @Test
  public void testBadGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.db().groups();
    GroupData group = new GroupData().withName("test2'");
    app.group().create(group);
    assertThat(app.group().сount(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before));
    verifyGroupListInUI();
  }


}
