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

    private Vector direction;

    /**
     *
     * @param intensity
     * @param direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point p) {
        return null;
    }

    @Override
    public Vector getL(Point p) {
        return null;
    }
}
