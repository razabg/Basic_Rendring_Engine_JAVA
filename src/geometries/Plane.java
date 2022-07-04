package geometries;

import primitives.*;


import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * this class implement the plane geometry
 */
public class Plane extends  FlatGeometry {


    /**
     *  _q0 - random point on plane
     */
    final Point _q0;

    /**
     *  _normal - normal to plane on q0
     */
    final Vector _normal;


    /**
     * * constructor for plane. receives 3 points.
     *
     * @param p1 - first point
     * @param p2 - second point
     * @param p3 - third point
     */
    public Plane(Point p1, Point p2, Point p3) {
        _q0 = p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);

        Vector N = U.crossProduct(V);

       _normal = N.normalize();
    }

    /**
     * constructor for plane.
     *
     * @param q0     - random point in the plane.
     * @param normal - normal to plane in the q0 point.
     */
    public Plane(Point q0, Vector normal) {
        _q0 = q0;
       _normal = normal.normalize();
    }


    /**
     *  getter method
     * @return the point that represent the plane
     */
    public Point getQ0() {
        return _q0;
    }

    /**
     * get normal to plane (field in class)
     * @return the normal
     */
    public Vector getNormal() {
        return _normal;
    }


    /**
     * get normal of plane in point
     * @return the normal
     */
    @Override
    public Vector getNormal(Point point) {
        return _normal;

    }

    /**
     * @param ray         - ray that cross the geometry
     * @param maxDistance - the upper bound of distance, any point which
     *                    its distance is greater than this bound will not be returned
     * @return list of intersection points that were found
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance,boolean bb) {
        Point P0= ray.getP0();
        Vector v= ray.getDir();
        Vector n = _normal;


        double nv = n.dotProduct(v);

        // ray direction cannot be parallel to plane orientation
        if (isZero(nv))
        {
            return null;
        }
        Vector Q_P0= _q0.subtract(P0);

        double nQMinusP0= alignZero(n.dotProduct(Q_P0));

        //t should not be equal to 0
        if(isZero(nQMinusP0))
        {
            return null;
        }
        double t = alignZero(nQMinusP0 / nv);
        if (alignZero(t-maxDistance) > 0 || t < 0)
        {
            return null;
            // return immutable List
            //return List.of(P0.add(v.Scale(t)));


        }
         return List.of(new GeoPoint(this, ray.getPoint(t)));

    }


}
