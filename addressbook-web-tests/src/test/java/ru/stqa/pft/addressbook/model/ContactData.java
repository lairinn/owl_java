package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String address;
  private final String home;


  public ContactData(String firstName, String lastName, String email, String address, String home) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.address = address;
    this.home = home;

  }


  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public String getEmail() {
    return email;
  }
  public String getAddress () {
    return address;
  }
public String getHomePhone() {return home;}
}
