package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class represents a light source with a known position, emits light to all directions (e.g. light bulb)
 * Point light is defined by the location of the lighting, and the intensity of light, (and attenuation coefficients).
 * It's transmits the same light intensity in all the directions on an equal level.
 * The attenuation constants give better control over the lighting model so that the distance affects the intensity.
 */
public class PointLight extends Light implements LightSource {

    /**
     * location of the light source
     */
    private Point position;

    /**
     *  kC - fixed attenuation regardless of distance
     *  kL - fixed attenuation dependent on distance
     *  kQ - fixed attenuation depending on square distance
     */
    private double kC = 1,kL = 0,kQ = 0;

    /**
     * constructor
     *
     * @param intensity    - the color of the light source
     * @param position - the point which the light is being emitted from
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;


    }

    /**
     * the method calc the color of the light in a given point in the 3D space
     *
     * @param p - the point which we want to know what its color
     * @return the light color in p
     */
    @Override
    public Color getIntensity(Point p) {


            double dist = p.distance(position);
            double distSquared = p.distanceSquared(position);
            return getIntensity().scale(1 / (kC + kL * dist + kQ * distSquared));
        }

    /**
     * the method get the ray from the light source to the given point
     *
     * @param p - the ray's destination point
     * @return the ray - the normalized(p - pL)
     */
    @Override
    public Vector getL(Point p) {
            return p.subtract(position).normalize();
        }

    /**
     * the method calc the distance between
     * light source and a point in order to make sure that there is no object
     * behind the light source that is casting a shadow on the tested point
     *
     * @param point - the tested point
     * @return the distance between the given point and the light source
     */
    @Override
    public double getDistance(Point point) {
        return point.distance(position);
    }


    /**
     * setter - chaining method design pattern
     * @param kC
     * @return PointLight
     */

    public PointLight set_kC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * setter - chaining method design pattern
     * @param kL
     * @return PointLight
     */
    public PointLight set_kL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setter - chaining method design pattern
     * @param kQ
     * @return PointLight
     */
    public PointLight set_kQ(double kQ) {
        this.kQ = kQ;
        return this;
    }


}
