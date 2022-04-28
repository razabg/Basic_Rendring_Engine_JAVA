package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * class used to trace rays for the rendering engine
 */
public abstract class RayTracerBase {

    protected final Scene scene; // scene to be rendered

    /**
     *   constructor for the ray tracer
     *    @param scene to be intersected
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     *  abstract function to determine the color of a pixel
     * @param ray - ray to intersect
     * @return the color of the pixel intersects the given ray
     */
    abstract Color traceRay(Ray ray);
}
