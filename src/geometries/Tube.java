package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;


/**
 * the class implement tube shape
 */
public class Tube implements Geometry {

    protected final Ray axisRay;
    protected final double radius;

    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }//להסתכל במצגות בשביל מימוש
    
}
