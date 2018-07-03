package ru.stqa.pft.addressbook.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import ru.stqa.pft.addressbook.model.Issue;

import java.io.IOException;
import java.util.Set;

/**
 * Created by ishulga on 03.07.2018.
 */
public class RestHelper {

  private final ApplicationManager app;

  public RestHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Issue> getIssues() throws IOException {
    String value = getExecutor().execute(Request.Get(app.getProperty("bug.address") + "/api/issues.json")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(value);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth(app.getProperty("bug.username"), app.getProperty("bug.password"));
  }
}
