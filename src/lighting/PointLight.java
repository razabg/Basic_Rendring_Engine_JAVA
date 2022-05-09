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

    @Override
    public Color getIntensity(Point p) {
        return null;
    }

    @Override
    public Vector getL(Point p) {
        return null;
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
