package ru.stqa.pft.sandbox;

public class MyFirstProgram {

public static void main (String[] args) {

  Point p1 = new Point();
 p1.x = 1;
 p1.y = 1;

  Point p2 = new Point();
  p2.x = 0;
 p2.y = 0;

  double result = p2.distance(p1);
  System.out.println("Расстояние = " + result);



    System.out.println("Расстояние = " + distance(p1,p2));
}

  public static double distance(Point p1, Point p2) {
    double d;
    d = (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    Math.sqrt(d);
    d = Math.sqrt(d);
    return d;
  }

}

