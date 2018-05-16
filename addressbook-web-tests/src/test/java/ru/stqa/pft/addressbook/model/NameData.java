package ru.stqa.pft.addressbook.model;

public class NameData {
  private final String firstName;
  private final String lastName;

  public NameData(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
}
