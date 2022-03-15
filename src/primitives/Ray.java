package primitives;

import static primitives.Util.isZero;

/**
 * this class implement Ray
 */
public class Ray {
    Point p0;
    Vector dir;

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir;
        this.dir.normalize();
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
     * @param t
     * @return di
     */
    public Point getPoint(double t) {
        if(isZero(t))
        {
            throw new IllegalArgumentException("t shoult not be ZERO");
        }
        return p0.add(dir.Scale(t));

    }
}
