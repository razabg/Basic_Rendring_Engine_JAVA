package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * this class implement the triangle geometry
 */
public class Triangle extends Polygon{


    public Triangle(Point... vertices) {
        super(vertices);
        super.setBoundingBox();

    }


  /** @Override
    public Vector getNormal(Point point) {
        return null;
    }
    **/



    /**
     * we've learned the calculations from the introduction lecture (we improved the complexity of polygon intersections calc)
     * @param ray ray toward the object
     * @return  intersection point
     */

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance ,boolean bb) {


        List<GeoPoint> result = _plane.findGeoIntersections(ray);

        if (result == null) {
            return result;
        }

        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        Point P1 = _vertices.get(1);
        Point P2 = _vertices.get(0);
        Point P3 = _vertices.get(2);

        Vector v1 = P1.subtract(P0);
        Vector v2 = P2.subtract(P0);
        Vector v3 = P3.subtract(P0);

        double sign1 = alignZero(v.dotProduct(v1.crossProduct(v2)));
        double sign2 = alignZero(v.dotProduct(v2.crossProduct(v3)));
        double sign3 = alignZero(v.dotProduct(v3.crossProduct(v1)));

        if (isZero(sign1) || isZero(sign2) || isZero(sign3)) {
            return null;
        }

        boolean positive1 = sign1 > 0;
        boolean positive2 = sign2 > 0;
        boolean positive3 = sign3 > 0;

        if(positive1 == positive2 && positive2 == positive3){
            return List.of(new GeoPoint(this,result.get(0).point));
        }
        else return null;


    }


    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + _vertices +
                ", plane=" + _plane +
                '}';
    }


}
