package primitives;

import java.util.Objects;


/**
 * the class implement point structure
 */
public class Point {
    final Double3 _xyz;


    @Override
    public String toString() {
        return "Point" + _xyz ;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(_xyz, point._xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_xyz);
    }

    public Point(Double3 xyz) {
        _xyz = xyz;
    }

    public Point(double x, double y, double z) {
        _xyz = new Double3(x,y,z);
    }

    /**
     *
     * @param the vector pointing to the new point
     * @return the new point
     */
    public Point add(Vector vector) {
        return new Point(_xyz.add(vector._xyz));
    }



    /**
     *
     * @param other Point
     * @return  d = ((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) + (z2 - z1)*(z2 - z1))
     */
    public double distanceSquared(Point other){
        double x1 = _xyz._d1;
        double y1 = _xyz._d2;
        double z1 = _xyz._d3;

        double x2 = other._xyz._d1;
        double y2 = other._xyz._d2;
        double z2 = other._xyz._d3;

        return ((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) + (z2 - z1)*(z2 - z1));
    }

    /**
     *
     * @param other
     * @return the distance between two points
     */
    public  double distance (Point other){
        return Math.sqrt(distanceSquared(other));
    }


    public Vector subtract(Point point) {
        Double3 result = _xyz.subtract(point._xyz);
        if (result.equals(Double3.ZERO)){
            throw new IllegalArgumentException("resulting of subtract Vector (0,0,0) not allowed");
        }
        return new Vector(result);
    }
}
