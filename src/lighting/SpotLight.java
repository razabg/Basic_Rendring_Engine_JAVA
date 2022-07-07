package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * the class represents a light source in a shape of spotlight
 * in order to get the same light intensity, we need to increase the initial intensity of the light source
 * class "SpotLight" defined by position, direction and light intensity.
 * it's a kind of point light that's why we chose to extend it from pointLight.
 */
public class SpotLight extends PointLight {


    /**
     *  direction - the direction of the light
     */
    private Vector direction;
    private double narrowBeam = 0d;


    /**
     * the method calc the color of the light in a given point in the 3D space for spotlight
     * @param p - the point which we want to know what its color
     * @return
     */
    @Override
    public Color getIntensity(Point p) {
        Color colorIc = super.getIntensity(p);
        double lv = getL(p).dotProduct(direction);
        double factor =Math.max(0,lv);
        return colorIc.scale(factor);
    }

    /**
     * constructor
     *
     * @param intensity    - the color of the light source
     * @param position - the point which the light is being emitted from
     * @param direction direction of light
     */

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }

    public double getNarrowBeam() {
        return narrowBeam;
    }



}
