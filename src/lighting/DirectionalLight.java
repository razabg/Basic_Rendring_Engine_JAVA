package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 *
 * This class represents a light source with a known direction, no position! (like the sun)
 * Directional Light defined by the intensity of light emitted from an undefined position, and vector direction.
 * The light energy propagation is uniform in its direction,and that's why
 * It will always go from surface to surface in the same way that energy is stored,and there is no attention to distance
 * and the whole scene receives the intensity of light in the same way.
 *
 * */
public class DirectionalLight extends Light implements LightSource {

    /**
     *   The direction of the directional light
     */
    private Vector direction;

    /**
     * c-tor
     * @param intensity
     * @param direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * the method calc the color of the light in a given point in the 3D space
     *
     * @param p - the point which we want to know its color
     * @return the light color in p
     */
    @Override
    public Color getIntensity(Point p) {

            return getIntensity(); // No attenuation with distance
        }


    /**
     * the method get the ray from the light source to the given point
     *
     * @param p - the ray's destination point
     * @return the ray - the normalized(p - pL)
     */
    @Override
    public Vector getL(Point p) {
            return direction.normalize();
    }



    }

