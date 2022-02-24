package geometries;

import Primitives.Point;
import Primitives.Vector;

public class Plane implements Geometry {
    final Point _q0;
    final Vector _normal;

    public Plane(Point p1, Point p2, Point p3) {
        _q0 = p1;
        _normal = vector.
    }

    public Point getQ0() {
        return _q0;
    }

    public Vector getNormal() {
        return _normal;
    }

    public Plane(Point q0, Vector normal) {
        _q0 = q0;
        _normal = normal;
    }
}
