package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * this class implement the cylinder geometry
 */
public class Cylinder extends Tube {

    double _height;

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this._height = height;
    }

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
}
