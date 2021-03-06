package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ishulga on 16.05.2018.
 */
public class ContactHelper extends HelperBase {

  private ApplicationManager app;

  public ContactHelper(WebDriver wd, ApplicationManager app)
  {
    super(wd);
    this.app = app;
  }


  public void submitContactCreation() {
    click(By.cssSelector("body"));
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }


  public void fillName(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("address"), contactData.getAddress());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(String.valueOf(contactData.getGroups()));
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void submitContactUpdate() {
    click(By.name("update"));
  }



  public void delete() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    contactCache = null;
  }


  public void createContact(ContactData contactData, boolean b) {
    fillName(new ContactData().withFirstName("Ivan").withLastName("Ivanov").withAddress("St.Petersburg, street Street, house 1").withHome("+78126666666").withEmail("example@test.ru").withGroup("test1"), true);
    submitContactCreation();
  }

  public void create(ContactData contact, boolean b) {
    fillName(contact, true);
    submitContactCreation();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    fillName(contact, false);
    submitContactUpdate();
    contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element : elements) {
      String firstName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String lastName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
    }
    return contacts;
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      //String[] phones = cells.get(5).getText().split("\n");
      String allPhones = cells.get(5).getText();
      String allMails = cells.get(4).getText();
      //String[] mails = cells.get(4).getText().split("\n");
      //String[] addresses = cells.get(3).getText().split("\n");
      String allAddresses = cells.get(3).getText();
      //String firstName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      //String lastName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      //String allMails = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
      //String allPhones = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
      //String allAddresses = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
      //int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAllPhones(allPhones).
              withAllAddresses(allAddresses).withAllMails(allMails));
    }
    return contactCache;
  }


  public ContactData infoFromEditForm(ContactData contact) {
    intContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String secaddress = wd.findElement(By.name("address2")).getAttribute("value");
    String emailtwo = wd.findElement(By.name("email2")).getAttribute("value");
    String emailthree = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).
            withHome(home).withMobile(mobile).withWork(work).withAddress(address).withEmail(email).
            withSecAddress(secaddress).withEmail2(emailtwo).withEmail3(emailthree);

  }
  public void selectContact(int id) {
    wd.findElement(By.cssSelector("input[value ='" + id + "']")).click();
  }

  public void addInGroup(ContactData contact, GroupData group){
    selectContact(contact.getId());
    selectGroupByValue(By.name("to_group"), group);
      click(By.name("add"));
    app.goTo().home();
  }

  private void selectGroupByValue(By menuLocator, GroupData group) {
    Select dropdownlist = new Select(wd.findElement(menuLocator));
    dropdownlist.selectByValue(String.valueOf(group.getId()));
  }

  public void findGroup(int id) {
    click(By.cssSelector("#right"));
    click(By.cssSelector("#right>select>option[value='" + id + "']"));
  }

  public void delContactFromGroup(ContactData contact, GroupData group){
    selectGroupByValue(By.cssSelector("select[name='group']"), group);
    selectContact(contact.getId());
    click(By.cssSelector("input[name='remove']"));
    app.goTo().home();
  }

  private void intContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td")); //список ячеек
    cells.get(7).findElement(By.tagName("a")).click();

    //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }


  public void findFreeGroup(Groups groups) {
    if (groups.size() == app.db().groups().size()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("group").withHeader("header").withFooter("footer"));
      app.goTo().home();
    }
  }

    public GroupData findGroupToAdd(Groups groupsOfContact) {
      Groups allGroups = app.db().groups();
      for(GroupData aGroup: allGroups){
        if (!groupsOfContact.contains(aGroup)){
          return aGroup;
        }
      }
      return new GroupData();
    }
  public ContactData getContactById(int id) {
    Contacts contactList = app.db().contacts();
    for (ContactData contact: contactList){
      if (contact.getId() == id) {
        return  contact;
      }
    }
    return new ContactData();
  }

  public GroupData findContactInGroup(ContactData contact, Groups groupsOfContact) {
    if (groupsOfContact.size() == 0) {
      GroupData result = app.db().groups().iterator().next();
      app.contact().addInGroup(contact, result);
      return result;
    } else return groupsOfContact.iterator().next();

  }
  public void findAnyGroupExists() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Group 1").withHeader("Header 1").withFooter("Footer 1"));
      app.goTo().home();
    }

  }
}
