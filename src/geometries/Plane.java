package geometries;

import primitives.*;


import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * this class implement the plane geometry
 */
public class Plane extends Geometry implements FlatGeometry {


    final Point _q0;
    final Vector _normal;

    public Plane(Point p1, Point p2, Point p3) {
        _q0 = p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);

        Vector N = U.crossProduct(V);

        _normal = N.normalize();
    }

    public Plane(Point q0, Vector normal) {
        _q0 = q0;
        _normal = normal;
    }


    public Point getQ0() {
        return _q0;
    }

    public Vector getNormal() {
        return _normal;
    }


    @Override
    public Vector getNormal(Point point) {
        return _normal;

    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
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
