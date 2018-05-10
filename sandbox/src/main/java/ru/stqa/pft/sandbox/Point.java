package ru.stqa.pft.sandbox;

/**
 * Created by ishulga on 07.05.2018.
 */
public class Point {

  double x;
  double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }


  public double distance(Point p2) {
    double d;
    d = (this.x - p2.x) * (this.x - p2.x) + (this.y - p2.y) * (this.y - p2.y);
    Math.sqrt(d);
    d = Math.sqrt(d);
    return d;
  }



}






