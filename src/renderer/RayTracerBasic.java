package renderer;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

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
       result = result.add(calcLocalEffects(point,ray));
       return result;

}

    private Color calcLocalEffects(GeoPoint point, Ray ray) {
        Color color = point.geometry.getEmission();
        Vector v = ray.getDir ();
        Vector n = point.geometry.getNormal(point.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = point.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(point.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(point.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;


    }

    private double calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {

    }

    private double calcDiffusive(Material material, double nl) {

    }


}
