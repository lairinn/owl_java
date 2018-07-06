package ru.stqa.pft.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by ishulga on 16.05.2018.
 */
public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite (alwaysRun = true)
    public void tearDown() {
    app.stop();
  }

  @BeforeMethod
public void logTestStart (Method m, Object[] p) {
    logger.info("Start test " + m.getName() +" with parameters " + Arrays.asList(p));
  }

  @AfterMethod (alwaysRun = true)
  public  void logTestStop (Method m) {
    logger.info("Stop test " + m.getName());
  }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }
  public void verifyContactListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts, equalTo(dbContacts.stream()
              .map((c) -> new ContactData().withId(c.getId()).withLastName(c.getFirstName()).withLastName(c.getLastName()))
              .collect(Collectors.toSet())));
    }
  }

    //public void skipIfNotFixed(int issueId) throws IOException {
    //  if (isIssueOpen(issueId)) {
      //  throw new SkipException("Ignored because of issue " + issueId);

    //  }
  //  }

//  private boolean isIssueOpen(int issueId) throws IOException {
  //  String json = RestAssured.get(
       //     String.format("http://demo.bugify.com/api/issues/%s.json",issueId)).asString();
   // JsonElement parsed = new JsonParser().parse(json);
    //JsonElement issues = parsed.getAsJsonObject().get("issues");
    //Set<Issue> newIssues = new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
   // String s = newIssues.iterator().next().getState();
  //  if(s.equals("Resolved")){return false;}
  //  return true;
 // }
  }




