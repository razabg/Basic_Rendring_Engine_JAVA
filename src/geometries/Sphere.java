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
        setBoundingBox();
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

    /**
     * @param ray         - ray that cross the geometry
     * @param maxDistance - the upper bound of distance, any point which
     *                    its distance is greater than this bound will not be returned
     * @param bb boolean for bounding box
     * @return list of intersection points that were found and has valid distance value
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance,boolean bb)
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


    /**
     * method sets the values of the bounding volume for the intersectable sphere
     */
    @Override
    public void setBoundingBox() {

        super.setBoundingBox();

        // get minimal & maximal x value for the containing box
        double minX = _center.get_x() - _radius;
        double maxX = _center.get_x() + _radius;

        // get minimal & maximal y value for the containing box
        double minY = _center.get_y() - _radius;
        double maxY = _center.get_y() + _radius;

        // get minimal & maximal z value for the containing box
        double minZ = _center.get_z() - _radius;
        double maxZ = _center.get_z() + _radius;

        // set the minimum and maximum values in 3 axes for this bounding region of the component
        boundingBox.setBoundingBox(minX, maxX, minY, maxY, minZ, maxZ);
    }


}
