package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ishulga on 10.05.2018.
 */
public class PointTest {

  @Test
  public void TestDistance() {
    Point p1 = new Point(1,1);
    Point p2 = new Point(0,0);
Assert.assertEquals(p2.distance(p1), 1.4142135623730951);

  }

  @Test
  public void TestDistance2() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p2.distance(p1), 0.0);
  }

  @Test
  public void TestDistanceNeg() {
    Point p1 = new Point(-1, -1);
    Point p2 = new Point(0, -1);
    Assert.assertEquals(p2.distance(p1), 1.0);
  }

  }
