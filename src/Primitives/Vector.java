package Primitives;

public class Vector extends Point {

    @Override
    public String toString() {
        return "Vector" + _xyz ;
    }

    public Vector(Double3 xyz) {
        super(xyz);
        if (_xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException(("Vector (0,0,0) not valid"));
    }

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (_xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException(("Vector (0,0,0) not valid"));
    }

    public double lengthSquared() {
       double u1 = _xyz._d1;
       double u2 = _xyz._d2;
       double u3 = _xyz._d3;

       return  u1 * u1 + u2 * u2 + u3* u3;
    }

    public double length(){
        return Math.sqrt(lengthSquared());
    }

    /**
     *
     * @param v2
     * @return
     */
    public double dotProduct(Vector vector) { //need add source;
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        double v1 = vector._xyz._d1;
        double v2 = vector._xyz._d2;
        double v3 = vector._xyz._d3;

        return  (u1 * v1 + u2*v2 + u3 * v3);


    }

    public Vector crossProduct(Vector vector) {//need add link
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        double v1 = vector._xyz._d1;
        double v2 = vector._xyz._d2;
        double v3 = vector._xyz._d3;

        return new Vector((u2*v3-v2*u3),-(u1*v3-v1*u3),(u1*v2-v1*u2));
    }

    /**
     *
     * @return vector with length = 1
     */
    public Vector normalize() {
        double len = length();

        return new Vector(_xyz.reduce(length()));


    }
}
