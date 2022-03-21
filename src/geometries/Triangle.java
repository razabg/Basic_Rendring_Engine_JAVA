package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * this class implement the triangle geometry
 */
public class Triangle extends Polygon {


    public Triangle(Point... vertices) {
        super(vertices);
    }


  /** @Override
    public Vector getNormal(Point point) {
        return null;
    }
    **/

  @Override
  public List<Point> findIntsersections(Ray ray) {
      return null;
  }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }


}
