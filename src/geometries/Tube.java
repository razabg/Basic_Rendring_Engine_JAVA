package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;


/**
 * the class implement tube shape
 */
public class Tube extends Geometry {

    protected final Ray _axisRay;
    protected final double _radius;

    public Tube(Ray _axisRay, double radius) {
        this._axisRay = _axisRay;
        this._radius = radius;
    }

    public Ray get_axisRay() {
        return _axisRay;
    }

    public double get_radius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + _axisRay +
                ", radius=" + _radius +
                '}';
    }

    @Override
    public Vector getNormal(Point point) {
        Vector v = _axisRay.getDir();
        Point P0 = _axisRay.getP0();
        Vector PMinusP0 = point.subtract(P0);
        double t = v.dotProduct(PMinusP0);

        Point O = P0.add(v.scale(t));

        return (point.subtract(O)).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
