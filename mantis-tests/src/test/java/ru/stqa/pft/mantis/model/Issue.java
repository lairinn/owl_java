package ru.stqa.pft.mantis.model;

/**
 * Created by ishulga on 02.07.2018.
 */
public class Issue {

  int id;
  private String summary;
  private String description;
  private Projects project;

  public int getId() {
    return id;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public String getSummary() {
    return summary;
  }

  public Issue withSummary(String summary) {
    this.summary = summary;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public Projects getProject() {
    return project;
  }

  public Issue withProject(Projects project) {
    this.project = project;
    return this;
  }
}
