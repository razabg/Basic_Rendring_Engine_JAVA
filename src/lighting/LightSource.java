package lighting;


import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * this is an interface with critical methods for any sort of light source
 */
public interface LightSource {

    /**
     * the method calc the color of the light in a given point in the 3D space
     *
     * @param p - the point which we want to know its color
     * @return the light color in p
     */
    public Color getIntensity(Point p);

    /**
     * the method get the ray from the light source to the given point
     *
     * @param p - the ray's destination point
     * @return the ray - the normalized(p - pL)
     */
    public Vector getL(Point p);

    /**
     * the method calc the distance between
     * light source and a point in order to make sure that there is no object
     * behind the light source that is casting a shadow on the tested point
     *
     * @param point - the tested point
     * @return the distance between the given point and the light source
     */
    double getDistance(Point point);


}
