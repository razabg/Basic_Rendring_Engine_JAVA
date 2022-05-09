package lighting;

import primitives.Color;

/**
 *
 */
abstract class Light {

    private Color intensity;  // intensity of the ambient light

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
