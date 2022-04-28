package renderer;

import geometries.Geometries;
import lighting.AmbientLight;
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
  public  Color traceRay(Ray ray) {
       List<Point> IntersectPoints =  scene.geometries.findIntersections(ray);

        if (IntersectPoints == null)
            return scene.background;
        else {
            Point closetPoint = ray.findClosestPoint(IntersectPoints);
            return calcColor(closetPoint);
        }

    }

private Color calcColor(Point point){

       return scene.ambientLight.getIntensity();


}




}
