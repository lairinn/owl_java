package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Projects;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by ishulga on 02.07.2018.
 */
public class SoapTests extends TestBase {

  @Test

  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Projects> projects = app.soap().getProject();
    System.out.println(projects.size());
    for (Projects project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test

  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Projects> projects = app.soap().getProject();
    Issue issue = new Issue().withSummary("test issue").withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }
}
