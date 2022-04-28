package renderer;

import geometries.Geometries;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 *class used to trace rays for the rendering engine
 */
public class RayTracerBasic extends RayTracerBase {
    /**
     *
     * @param scene - the scene the rays are sent to
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    Color traceRay(Ray ray) {
       List<Point> IntersectPoints =  scene.geometries.findIntersections(ray);

        if (IntersectPoints == null)
            return scene.getBackground();
        else {
            Point closetPoint = ray.findClosestPoint(IntersectPoints);
        }
    }


}
