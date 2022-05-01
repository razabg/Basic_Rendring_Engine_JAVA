package primitives;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;
import geometries.Intersectable.GeoPoint;

/**
 * this class implement Ray
 */
public class Ray {
    Point p0;
    Vector dir;

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    /**
     * Creating a {@link Point} at a specific distance in the ray direction
     *
     * @param delta
     * @return di
     */
    public Point getPoint(double delta) {
        if (isZero(delta)) {
            return p0;
        }
        return p0.add(dir.scale(delta));
    }

    /**
     * The method find the closest point to the ray's beginning.
     * @param pointList
     * @return point
     */
    public Point findClosestPoint(List<Point> pointList) {
        Point result = null;
        double minDistance = Double.MAX_VALUE;
        double ptDistance;
        if (pointList!= null){
        for (Point pt : pointList) { // traverse all the points and find the min distance point
            ptDistance = p0.distance(pt);
            if (ptDistance < minDistance) {
                minDistance = ptDistance;
                result = pt;
            }
        }

        }
        return result;
    }

    /**
     * find the closest GeoPoint to Ray
     *
     * @param GeoPointList List of intersections point
     * @return the closest point
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> GeoPointList) {

        double distance = Double.POSITIVE_INFINITY;

        GeoPoint nearPoint = null;

        if (GeoPointList == null) {
            return null;
        }

        // distance => distanceSquared
        // no need to activate the Math.sqrt function
        // distance is always a positive value,
        for (GeoPoint p : GeoPointList) {
            double dis = p.point.distanceSquared(p0); // distance from the starting point of the ray
            if (dis < distance) {
                distance = dis;
                nearPoint = p;
            }
        }

        return nearPoint;
    }

}
