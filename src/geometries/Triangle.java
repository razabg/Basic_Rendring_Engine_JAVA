package geometries;

import primitives.Point;
import primitives.Vector;

public class Triangle extends Polygon {


    public Triangle(Point... vertices) {
        super(vertices);
    }


    @Override
    public Vector getNormal(Point point) {
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
