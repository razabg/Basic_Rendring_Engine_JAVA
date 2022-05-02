package renderer;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 *class used to trace rays for the rendering engine
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * constructor
     * @param scene - the scene the rays are sent to
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    /**
     * function to determine the color of a pixel
     * @param ray - the ray sent from the light source to the scene
     * @return the color of the pixel intersects the given ray
     */
    @Override
  public  Color traceRay(Ray ray) {
       List<GeoPoint> IntersectPoints =  scene.geometries.findGeoIntersections(ray);

        if (IntersectPoints == null)
            return scene.background;
        else {

            GeoPoint closetPoint = ray.findClosestGeoPoint(IntersectPoints);
            return calcColor(closetPoint,ray);
        }

    }


    /**
     *function which returns the color of the object the ray is intersecting
     * if no intersection was found, returns the ambient light's color
     * @param point
     * @return
     */
    private Color calcColor(GeoPoint point,Ray ray){
       Color result =  scene.ambientLight.getIntensity();
       result = result.add(point.geometry.getEmission());
       return result;

}




}
