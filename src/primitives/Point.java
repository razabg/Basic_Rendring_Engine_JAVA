package primitives;

import java.util.Objects;


/**
 * the class implement point structure
 */
public class Point {
    final Double3 xyz;

    public final static Point ZERO = new Point(0d, 0d, 0d);

    public double get_x() {
        return xyz.d1;
    }
    public double get_y() {return xyz.d2;}
    public double get_z() {
        return xyz.d3;
    }


    @Override
    public String toString() {
        return "Point" + xyz;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    public Point(double x, double y, double z) {
        xyz = new Double3(x,y,z);
    }

    /**
     *
     * @param  vector the vector pointing to the new point
     * @return the new point
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }



    /**
     *
     * @param other Point
     * @return  d = ((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) + (z2 - z1)*(z2 - z1))
     */
    public double distanceSquared(Point other){
        double x1 = xyz.d1;
        double y1 = xyz.d2;
        double z1 = xyz.d3;

        double x2 = other.xyz.d1;
        double y2 = other.xyz.d2;
        double z2 = other.xyz.d3;

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
        Double3 result = xyz.subtract(point.xyz);
        if (result.equals(Double3.ZERO)){
            throw new IllegalArgumentException("resulting of subtract Vector (0,0,0) not allowed");
        }
        return new Vector(result);
    }


}
