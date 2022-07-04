package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * this class implement the cylinder geometry
 */
public class Cylinder extends Tube {



    /**
     *  _height - the length of the cylinder from base to base
     */
    double _height;

    /**
     * constructor for cylinder.
     *
     * @param axisRay - ray for base tube
     * @param radius  = radius of base tube
     * @param height  - length of cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this._height = height;
    }

    /**
     * getter
     * @return height of cylinder
     */
    public double get_height() {
        return _height;
    }


    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + _height +
                ", axisRay=" + _axisRay +
                ", radius=" + _radius +
                '}';
    }


    /**
     *
     * impelemention {@link Geometry#getNormal(Point)}
     *
     * @param point POINT TO Calculate NORMAL FROM/TO
     * @return normal
     */
    @Override
    public Vector getNormal(Point point) {
        Point P0 = _axisRay.getP0(); //middle of starting base
        Vector v = _axisRay.getDir();
        Point P1 = P0.add(v.scale(_height)); //middle of far base

        //if point is on the starting base - if distance from p0 is radius, and orthogonal to ray (so it is not on ray itself)
        if ((point.distance(P0) <= _radius) && (point.subtract(P0).dotProduct(v) == 0)) {
            return get_axisRay().getDir().scale(-1);
        }
        //if point is on the far base - if distance from p1 is radius, and orthogonal to ray (so it is not on ray itself)
        else if ((point.distance(P1) <= _radius) && (point.subtract(P1).dotProduct(v) == 0)) {
            return get_axisRay().getDir();
        }

        // if point is on round surfaces - not based:
        else {

            Vector PMinusP0 = point.subtract(P0);
            double t = v.dotProduct(PMinusP0);

            Point O = P0.add(v.scale(t));

            return (point.subtract(O)).normalize();

        }




    }

    /**
     * this function finds the intersection points of a given ray with the cylinder
     *
     * @param ray         - which could intersect the cylinder
     * @param maxDistance - is the maximum distance to find intersections in
     * @return list of intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance,boolean bb) {
        // Step 1: find intersections between the ray and the tube which the cylinder is a part of
        List<GeoPoint> intersectionsTube = super.findGeoIntersectionsHelper(ray, maxDistance,bb);

        // Step 2: intersect is between caps
        Vector dir = _axisRay.getDir();
        Point bottomCapCenter = _axisRay.getP0();
        Point upperCupCenter = _axisRay.getPoint(_height);

        double loweBound;
        double upperBound;
        List<GeoPoint> intersectionsCylinder = new ArrayList<>();

        // validate each intersection (make sure it is in the cylinder itself and not on its continual)
        if (intersectionsTube != null) {
            for (GeoPoint geoPoint : intersectionsTube) {
                loweBound = dir.dotProduct(geoPoint.point.subtract(bottomCapCenter));
                upperBound = dir.dotProduct(geoPoint.point.subtract(upperCupCenter));
                if (loweBound > 0 && upperBound < 0) {
                    // the check for distance, if the intersection point is beyond the distance
                    if (alignZero(geoPoint.point.distance(ray.getP0()) - maxDistance) <= 0) {
                        intersectionsCylinder.add(geoPoint);
                    }
                }
            }
        }

        // Step 3: intersect with each plane which belongs to the caps
        Plane bottomCap = new Plane(bottomCapCenter, dir);
        Plane upperCap = new Plane(upperCupCenter, dir);
        List<GeoPoint> intersectionsBottomCup = bottomCap.findGeoIntersectionsHelper(ray, maxDistance,bb);
        List<GeoPoint> intersectionsUpperCup = upperCap.findGeoIntersectionsHelper(ray, maxDistance,bb);

        // if no intersections were found with the caps, return the ones we have already found
        if (intersectionsBottomCup == null && intersectionsUpperCup == null) {
            if (intersectionsCylinder.isEmpty()) {
                return null;
            }
            return intersectionsCylinder;
        }

        // Step 4: intersections inside caps
        Point bottomCapIntersectionPoint;
        Point upperCapIntersectionPoint;

        // bottom cup
        if (intersectionsBottomCup != null) {
            bottomCapIntersectionPoint = intersectionsBottomCup.get(0).point;
            if (bottomCapIntersectionPoint.subtract(bottomCapCenter).lengthSquared() < _radius * _radius) {
                intersectionsCylinder.add(intersectionsBottomCup.get(0));
            }
        }

        // upper cup
        if (intersectionsUpperCup != null) {
            upperCapIntersectionPoint = intersectionsUpperCup.get(0).point;
            if (upperCapIntersectionPoint.subtract(upperCupCenter).lengthSquared() < _radius * _radius) {
                intersectionsCylinder.add(intersectionsUpperCup.get(0));
            }
        }

        if (intersectionsCylinder.isEmpty()) {
            return null;
        }

        return intersectionsCylinder;
    }

}
