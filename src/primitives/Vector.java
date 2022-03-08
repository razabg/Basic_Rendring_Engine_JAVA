package primitives;

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


    /**
     *
     * @param scalar
     * @return return new vector after multiply vector with scalar
     */
    public Vector Scale(double scalar){
       return new Vector(this._xyz._d1 * scalar,this._xyz._d2 * scalar,this._xyz._d3 * scalar);
    }


    /**
     *
     * @return
     */
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
     *WE USED THIS WEBSITE http://www.damada.co.il/topics/math/db/vectors_scalar_mult/vectors_scalar_mult.shtml
     * @param
     * @return
     */
    public double dotProduct(Vector vector) {
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        double v1 = vector._xyz._d1;
        double v2 = vector._xyz._d2;
        double v3 = vector._xyz._d3;

        return  (u1 * v1 + u2*v2 + u3 * v3);


    }

    /**
     *
     * @param vector
     * @return adding two vectors and return the new vector
     */
    public Vector add(Vector vector) {
        return ( new Vector(this._xyz._d1 + vector._xyz._d1,this._xyz._d2 + vector._xyz._d2,this._xyz._d3 + vector._xyz._d3));
    }

    /**
     * WE USED THIS WEBSITE https://www.mathsisfun.com/algebra/vectors-cross-product.html
     * @param vector
     * @return
     */
    public Vector crossProduct(Vector vector) {
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
