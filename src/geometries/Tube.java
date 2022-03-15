package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;


/**
 * the class implement tube shape
 */
public class Tube implements Geometry {

    protected final Ray _axisRay;
    protected final double radius;

    public Tube(Ray _axisRay, double radius) {
        this._axisRay = _axisRay;
        this.radius = radius;
    }

    public Ray get_axisRay() {
        return _axisRay;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + _axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point point) {
        Vector v = _axisRay.getDir();
        Point P0 = _axisRay.getP0();
        Vector PMinusP0 = point.subtract(P0);
        double t = v.dotProduct(PMinusP0);

        Point O = P0.add(v.Scale(t));

        return (point.subtract(O)).normalize();
    }
    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}
