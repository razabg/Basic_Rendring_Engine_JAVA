package primitives;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static primitives.Util.isZero;
import geometries.Intersectable.GeoPoint;

/**
 * this class implement Ray
 */
public class Ray {


    /**
     * constant size of start points of rays for moving shading rays
     */
    private static final double DELTA = 0.1;
    /**
     *  _p0 - starting point of Ray
     */
    Point p0;
    /**
     *  _dir - the point the Ray points to from p0
     */
    Vector dir;


    /**
     * c-tor
     * @param p0
     * @param dir
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }


    /**
     constructor for ray.
     *  creates a new ray and moves its head in the
     *  normal direction by the normal scaled by DELTA
     * @param p origin
     * @param n   normal vector
     * @param dir direction
     */
    public Ray(Point p, Vector n, Vector dir) {
        this.dir = dir.normalize();
        double nv = n.dotProduct(this.dir);
        Vector delta  =n.scale(DELTA);
        if (nv < 0)
            delta = delta.scale(-1);
        this.p0 = p.add(delta);
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


    /**
     * auxiliary function to randomly scatter points within a rectangular surface.
     * returns a list of rays which relates to the surface.
     *
     * @param numRays     - number of rays we create in the rectangular surface.
     * @param vUp         - upper vector of rectangular surface.
     * @param vRight      - right vector of rectangular surface.
     * @param dist        - of the camera from the view plane
     * @param pixelWidth  - the width of a single pixel in view plane
     * @param pixelHeight - the height of a single pixel in view plane
     * @return list of rays from the area of the pixel to the scene
     */
    public List<Ray> randomRaysInGrid(Vector vUp, Vector vRight, int numRays, double dist, double pixelWidth, double pixelHeight) {
        List<Ray> rays = new LinkedList<>();

        // the starting point of the original vector
        Point p0 = getP0();

        // the center of the pixel
        Point pixelCenter = p0.add(dir.scale(dist));

        // calculate the maximal number of rays which we can compress in one dimension
        // if we don't make this calculation, we might add too many rays, which will decrease performance
        // if the sqrt is not an integer, it will be the floor value
        // (mostly, unless the number is x.99...99 with 15 9s or more, which will probably never happen)
        int raysInDimension = (int) Math.sqrt(numRays);

        // the size of one step from one ray to another in each dimension
        double xMove = pixelWidth / raysInDimension;
        double yMove = pixelHeight / raysInDimension;

        // the starting point is the right bottom corner of the pixel
        Point cornerOfPixel = pixelCenter
                .add(vRight.scale(pixelWidth / 2d))
                .add(vUp.scale(pixelHeight / 2d));

        Point newRayStartPoint;

        int sign = 1;
        Random rand = new Random();

        for (int i = 0; i < raysInDimension; ++i) {
            for (int j = 0; j < raysInDimension; ++j, sign *= -1) {

                newRayStartPoint = cornerOfPixel;

                // determine by how much we move the point in each iteration
                double randomMoveX = xMove / 2d + xMove * rand.nextDouble();
                double randomMoveY = yMove / 2d + yMove * rand.nextDouble();

                // move in the x dimension
                if (!isZero(i * randomMoveX)) {
                    newRayStartPoint = newRayStartPoint.add(vRight.scale((i) * randomMoveX));
                }

                // move in the y dimension
                if (!isZero(j * randomMoveY)) {
                    newRayStartPoint = newRayStartPoint.add(vUp.scale((j) * randomMoveY));
                }

//                // if we have reach the center point of the pixel
//                if (i == raysInDimension / 2 && j == i) {
//                    newRayStartPoint = pixelCenter;
//                }

                // make sure we do not add the canter ray more than once
                if (!newRayStartPoint.equals(pixelCenter)) {
                    rays.add(new Ray(p0, (newRayStartPoint.subtract(p0)))); // normalized inside Ray constructor
                }

            }
        }
        return rays;
    }

//todo docu
    /**
     * auxiliary method to randomly scatter points within a circular surface.
     * returns a list of rays which related to the surface.
     *
     * @param center  - center point of the circular surface.
     * @param vUp     - upper vector of circular surface.
     * @param vRight  - right vector of circular surface.
     * @param radius  - radius of circular surface. (mostly aperture)
     * @param numRays - number of rays we create in the circular surface.
     * @param dist    - distance between the view plane and the focal plane
     * @return list of rays from the area of the aperture to the focal point
     */
    public List<Ray> randomRaysInCircle(Point center, Vector vUp, Vector vRight, double radius, int numRays, double dist) {
        List<Ray> rays = new LinkedList<>();

        rays.add(this); // including the original ray
        if (radius == 0) {
            // radius input zero means there's no circular surface.
            return rays;
        }

        Point focalPoint = getPoint(dist);

        for (int i = 1; i < numRays; ++i) {

            // min = -1, max = 1
            // which means the degree of the line which the points located (between 0 to Pie)
            double cosTheta = -1 + (Math.random() * 2);

            // using Pythagoras theorem, define the complement to cosTheta (the 'mashlim' in Hebrew)
            double sinTheta = Math.sqrt(1 - cosTheta * cosTheta);

            // min = -radius, max = +radius
            // if we get extreme value, it means the new p0 will be located on the diameter,
            // otherwise, somewhere in the middle of the circle
            double d = -radius + (Math.random() * (2 * radius));

            // Move from polar to Cartesian system:
            double x_move = d * cosTheta;
            double y_move = d * sinTheta;

            // define a new starting point for the new ray
            // start from the center of the circle
            Point newP0 = center;

            // if the x and y steps are not 0, move the point
            if (!isZero(x_move)) {
                newP0 = newP0.add(vRight.scale(x_move));
            }
            if (!isZero(y_move)) {
                newP0 = newP0.add(vUp.scale(y_move));
            }

            // the vectors normalized inside Ray constructor
//            if (_p0.equals(center)) {

            // add the ray from the new starting point to the focal point
            rays.add(new Ray(newP0, (focalPoint.subtract(newP0)))); // from surface


        }
        return rays;
    }


}
