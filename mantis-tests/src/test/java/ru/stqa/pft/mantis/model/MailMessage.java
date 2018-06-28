package ru.stqa.pft.mantis.model;

/**
 * Created by ishulga on 28.06.2018.
 */
public class MailMessage {

  public String to;
  public String text;

  public MailMessage(String to, String text) {
    this.to = to;
    this.text = text;
  }
}