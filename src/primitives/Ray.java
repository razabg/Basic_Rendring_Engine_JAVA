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



        public Point findClosestPoint(List<Point> points) {
            return points == null || points.isEmpty() ? null
                    : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
        }

//    }

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
