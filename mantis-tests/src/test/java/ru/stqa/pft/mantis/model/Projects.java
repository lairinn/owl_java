package ru.stqa.pft.mantis.model;

/**
 * Created by ishulga on 02.07.2018.
 */
public class Projects {

  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public Projects withId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Projects withName(String name) {
    this.name = name;
    return this;
  }
}
