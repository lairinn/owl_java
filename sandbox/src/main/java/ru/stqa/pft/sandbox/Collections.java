package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ishulga on 08.06.2018.
 */
public class Collections {

  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP"}; //массив

    List<String> languages = new ArrayList<String>(); //список
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");

    List <String> languages1 = Arrays.asList("Java", "C#", "Python", "PHP");

    for (int i = 0; i <languages1.size(); i++)
    { System.out.println("Я хочу выучить " + languages1.get(i));}

    for (int i = 0; i < langs.length; i++) {
      System.out.println("Я хочу выучить " + langs[i]);

      for (String l : langs) {
        System.out.println("Я хочу выучить " + l);

        for (String ln : languages) {
          System.out.println("Я хочу выучить " + ln);
        }
      }
    }
  }
}
