package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().initGroupCreation();
    app.group().fillGroupForm(group);
    app.group().submitGroupCreation();
    app.group().returnToGroupPage();
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);


    //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
  }

}
