package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Projects;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ishulga on 02.07.2018.
 */
public class SoapHelper {

  private ApplicationManager app;

  public SoapHelper (ApplicationManager app) {
    this.app = app;
  }
public Set<Projects> getProject() throws RemoteException, MalformedURLException, ServiceException {
  MantisConnectPortType mc = getMantisConnect();
  ProjectData[] projects = mc.mc_projects_get_user_accessible("web.adminLogin", "web.adminPassword");
  return Arrays.asList(projects).stream().map((p) -> new Projects().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
  }

  public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator().getMantisConnectPort(new URL("web.soapURL"));
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories("web.adminLogin", "web.adminPassword", BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories [0]);
    BigInteger issueID = mc.mc_issue_add("web.adminLogin", "web.adminPassword", issueData);
   IssueData createdIssueData = mc.mc_issue_get("web.adminLogin", "web.adminPassword", issueID);
    return new Issue().withId(createdIssueData.getId().intValueExact()).withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription()).
            withProject(new Projects().withId(createdIssueData.getProject().getId().intValue()).withName(createdIssueData.getProject().getName()));
  }
}
