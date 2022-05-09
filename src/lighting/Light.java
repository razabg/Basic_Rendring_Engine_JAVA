package lighting;

import primitives.Color;

/**
 * this Abstract Class represents a basic element which is defined by the colors' intensity of the lighting,
 * according to the light propagation model.
 * represents all types of light sources in the scene like spotlight, point light etc.
 * the class goal is to illuminate the objects with colors in different ways.
 */
abstract class Light {

    private Color intensity;  // intensity of the ambient light

    /**
     * Light constructor accepting the intensity of colors value
     * creates the light's intensity
     *
     * @param intensity - the color of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter for intensity
     * @return intensity of ambient light
     */
    public Color getIntensity() {
        return intensity;
    }
}
