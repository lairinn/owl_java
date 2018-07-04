package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by ishulga on 04.07.2018.
 */
public class GithubTests {

  @Test

  public void testCommits() throws IOException {
    Github github = new RtGithub("7c4d7d074eca952b52c6bd1eb788bb8e31ebc3df");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("lairinn", "java_owl")).commits();
   // for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String,String>().build())) {
    // System.out.println(new RepoCommit.Smart(commit).message());
   //}
  }
}

