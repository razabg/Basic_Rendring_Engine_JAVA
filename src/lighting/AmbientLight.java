package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Ambient Light as color for all objects in the Scene
 */
public class AmbientLight {

    private final Color intensity;              // intensity of the ambient light

    /**
     * Primary Constructor
     * @param Ia light intensity
     * @param Ka attenuation coefficient
     */
    public AmbientLight(Color Ia, Double3 Ka){
        intensity = Ia.scale(Ka);
    }

    /**
     * default constructor
     */
    public AmbientLight() {
        intensity = Color.BLACK;
    }

    /**
     * getter for intensity
     * @return intensity of ambient light
     */
    public Color getIntensity() {
        return intensity;
    }
}
