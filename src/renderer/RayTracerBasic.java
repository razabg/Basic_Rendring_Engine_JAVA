package renderer;

import geometries.FlatGeometry;
import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 *class used to trace rays for the rendering engine
 */
public class RayTracerBasic extends RayTracerBase {


    /**
     * constant size of start points of rays for shading rays
     */
    private static final double DELTA = 0.1;

    /**
     * constructor
     *
     * @param scene - the scene the rays are sent to
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    /**
     * function to determine the color of a pixel
     *
     * @param ray - the ray sent from the light source to the scene
     * @return the color of the pixel intersects the given ray
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> IntersectPoints = scene.geometries.findGeoIntersections(ray);

        if (IntersectPoints == null)
            return scene.background;
        else {

            GeoPoint closetPoint = ray.findClosestGeoPoint(IntersectPoints);
            return calcColor(closetPoint, ray);
        }

    }


    /**
     * function which returns the color of the object the ray is intersecting
     * if no intersection was found, returns the ambient light's color
     *
     * @param point
     * @return
     */
    private Color calcColor(GeoPoint point, Ray ray) {
        Color result = scene.ambientLight.getIntensity();
        result = result.add(calcLocalEffects(point, ray));
        return result;


    }


    /**
     * function which returns the color of the object the ray is intersecting after adding the required effects
     *
     * @param point - the point on the object
     * @param ray   - ray to the point
     * @return the color in the point with local effects
     */
    private Color calcLocalEffects(GeoPoint point, Ray ray) { //implemented by the instruction in the slides
        Color color = point.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = point.geometry.getNormal(point.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = point.geometry.getMaterial();


        for (LightSource lightSource : scene.lights) {

            Vector l = lightSource.getL(point.point);
            double nl = alignZero(n.dotProduct(l));

            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if (unshaded(point, lightSource,l, n, nl,nv)) {
                    Color iL = lightSource.getIntensity(point.point);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }

        }
        return color;

    }

    /**
     * @param material
     * @param n        normal to the object
     * @param l        ray that going to the object (vector from the light source)
     * @param nl       dot product of n and l
     * @param v        direction vector of the ray that coming from the camera
     * @return
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) { ////implemented by the instruction in the slides

        Vector ReflectanceVector = l.add(n.scale(-2 * nl)); // nl not have to be zero
        double minusVR = -alignZero(ReflectanceVector.dotProduct(v));
        if (minusVR <= 0)
            return Double3.ZERO; //in this case the direction view is opposite to  ReflectanceVector
        Double3 result = material.kS.scale(Math.pow(minusVR, material.nShininess));
        return result;

    }

    /**
     * calc diffusive component of  light reflection
     *
     * @param material
     * @param nl       dot product n*l
     * @return
     */
    private Double3 calcDiffusive(Material material, double nl) { //implemented by the instruction in the slides
        nl = Math.abs(nl); //abs value
        Double3 result = material.kD.scale(nl);
        return result;

    }


    /**
     *   boolean method test whether a given point is shaded or not
     *  @param nl       -  dot product of n and l
     *  @param lightSource - the light source which we check if the point is shaded from
     *  @param n        - the normal of l with the body
     *  @param geoPoint - the tested point
     * @return true if the point is shaded, false otherwise
     */
    private boolean unshaded(GeoPoint geoPoint, LightSource lightSource,Vector l, Vector n, double nl,double nv) {
        Vector LightDirection = lightSource.getL(geoPoint.point).scale(-1);// from the point to the light source

        Vector DELTA_vector = n.scale(nv < 0 ? DELTA : -DELTA);
        Point pointRay = geoPoint.point.add(DELTA_vector);
        Ray LightRay = new Ray(pointRay,LightDirection);

        double maxDistacne = lightSource.getDistance(geoPoint.point);

        List<GeoPoint> Intersections = scene.geometries.findGeoIntersections(LightRay,maxDistacne);

        return Intersections == null;

    }
}
