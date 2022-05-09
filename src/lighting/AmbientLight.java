package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Ambient Light as color for all objects in the Scene
 */
public class  AmbientLight extends Light {



    /**
     * the ctor calclaute the final light intensity of the surroundings.
     * Primary Constructor,send the calculation of Ia.scale(Ka) to the base class Light ctor
     * @param Ia light intensity
     * @param Ka attenuation coefficient
     */
    public AmbientLight(Color Ia, Double3 Ka){
        super(Ia.scale(Ka));
    }

    /**
     * default constructor
     */
    public AmbientLight() {
        super(Color.BLACK);
    }



}
