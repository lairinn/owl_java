package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by ishulga on 28.06.2018.
 */
public class LoginHelper extends HelperBase{

public LoginHelper(ApplicationManager app) {
  super(app);
}

  public void loginTo() {
    String username = app.getProperty("web.adminLogin");
    String password = app.getProperty("web.adminPassword");
    wd.get(app.getProperty("web.baseUrl"));

    type(By.name("username"),username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public void goToUsers(){
    wd.get(app.getProperty("web.userURL"));
  }


}
