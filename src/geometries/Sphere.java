package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * the class implement sphere shape
 */
public class Sphere extends Geometry {

    Point _center;
    double _radius;

    public Sphere(Point center, double radius) {
        this._center = center;
        this._radius = radius;
    }

    @Override
    public Vector getNormal(Point point) {

        return (point.subtract(_center)).normalize();

    }

    public Point get_center() {
        return _center;
    }

    public double get_radius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + _center +
                ", radius=" + _radius +
                '}';
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance)
     {

        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        if (P0.equals(_center)) {
            if (alignZero(_radius-maxDistance) > 0) {
                return null;
            }
            return List.of(new GeoPoint(this,ray.getPoint(_radius)));
        }

        Vector U = _center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - (tm * tm)));

        // no intersections : the ray direction is above the sphere
        if (d >= _radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(_radius * _radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 > 0 && t2 > 0 && alignZero(t1 - maxDistance) <= 0 && alignZero(t2 - maxDistance) <= 0 ) {
//            Point P1 = P0.add(v.scale(t1));
//            Point P2 = P0.add(v.scale(t2));
            Point P1 =ray.getPoint(t1);
            Point P2 =ray.getPoint(t2);
            return List.of(new GeoPoint(this,P1),new GeoPoint(this,P2));
        }
        if (t1 > 0 && alignZero(t1 - maxDistance) <= 0) {
//            Point P1 = P0.add(v.scale(t1));
            Point P1 =ray.getPoint(t1);
            return List.of(new GeoPoint(this, P1));
        }
        if (t2 > 0 && alignZero(t2 - maxDistance) <= 0) {
//            Point P2 = P0.add(v.scale(t2));
            Point P2 =ray.getPoint(t2);
            return List.of(new GeoPoint(this, P2));
        }
        return null;
    }


}
