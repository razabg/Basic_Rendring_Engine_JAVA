package renderer;

import geometries.FlatGeometry;
import geometries.Geometries;
import geometries.Geometry;
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
     * how many reflections we want to calculate - The maximum value of the tree height (number of intersections) for reflection/refraction calculations for a geometry the camera sees.
     * Stop condition for transparency (reflection: the rays hit the geometric body, and they can get to other geometric bodies):
     * how far we consider calculating the ray's intersections with other geometries which are behind original geometry.
     * Stop condition for refraction (the rays that cross the geometric body):
     * how far we consider calculating the intersections of the rays which created by refractions in the original geometric body.
     * <p>
     * USE WITH CAUTION! higher values leads to performance decreasing rapidly
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10; // 10

    /**
     * the minimum value of transparency/refraction to be considered if the calculation
     * for reflection/refraction is necessary in the current geometry.
     */
    private static final double MIN_CALC_COLOR_K = 0.001; // 0.001
    private static final Double3 INITIAL_K = Double3.ONE;
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
     * boolean value to determine use of bounding box improvement
     */
    private boolean _bb; // bounding box

    /**
     * setter for bounding box flag
     *
     * @param _bb to set the bb factor?
     * @return this instance
     */
    public RayTracerBasic set_bb(boolean _bb) {
        this._bb = _bb;
        return this;
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
     * @param geopoint
     * @return
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        GeoPoint closestPoint =findClosestIntersection(ray);
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }




    /**
     * function which returns the color of the object the ray is intersecting after adding the required effects
     *
     * @param gp  - the gp on the object
     * @param ray - ray to the gp
     * @param  k  - factor of reflection and refraction so far
     * @return the color in the gp with local effects
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray,Double3 k) { //implemented by the instruction in the slides

        Geometry geometry = gp.geometry;
        Point point = gp.point;

        Color color = geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = geometry.getNormal(point);

        double nv = alignZero(n.dotProduct(v));
        if (nv==0)
            return color.BLACK;
        Material material = geometry.getMaterial();


        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));

            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(gp,lightSource,l,n,nv);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K))
                {
               // if (unshaded(gp, lightSource,l, n, nl,nv)) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add (
                            iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }

        }
        return color;

    }

    /**
     * the method calculate transparency and reflections
     *
     * @param gp        - the tested point
     * @param v         - the vector that goes from the camera through a pixel
     * @param level     - recursion iterations upper limit
     * @param k         - initial value
     * @return the Color of the returned light after calculating all the required effects
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        Double3 kkr = material.Kr.product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.Kr, kkr);

         Double3 kkt = material.Kt.product(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.Kt, kkt));
        return color;
    }

    /**
     * the method returns the color of the object the ray is intersecting after adding the required effects
     * @param kkx   - kR or kT factor multiplied by k - factor of reflection and refraction
     * @param kx    - kR or kT factor
     * @param ray   - ray to the point
     * @param level - recursion level
     * @return the color in the point with local effects
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp =  findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level-1, kkx).scale(kx));
    }


    /**
     * the method returns the color of the object the ray is intersecting
     * if no intersection was found, returns the ambient light's color
     *
     * @param intersection - closest intersection point on the object
     * @param ray          - ray to the point
     * @param level        - recursion iterations upper limit
     * @param k            - initial value
     * @return the color in the point with all the effects
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray,k);
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }

    /**
     * the method find the closest geo-point to the starting point of the ray
     * among all the intersections with an object
     *
     * @param ray - the tested ray
     * @return the closest point  to the ray's starting point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections;

        if (!_bb) {
           intersections  = scene.geometries.findGeoIntersections(ray);
            GeoPoint closestPoint =ray.findClosestGeoPoint(intersections);
            return closestPoint;
        }
        else{
            intersections = scene.geometries.findIntersectBoundingRegion(ray);
            if (intersections == null || intersections.size() == 0) {
                return null;
            } else {
                return ray.findClosestGeoPoint(intersections);
            }
        }


    }

    /**
     * the method construct the new ray refracted the certain geometry
     *
     *
     * @param n     - the normal in point
     * @param point - refraction point
     * @param v     - the normalized ray from the viewer point of view
     * @return the reflected ray
     */
    private Ray constructRefractedRay(Point point,Vector v, Vector n) {
            return new Ray(point,n,v);  // use the constructor with 3 arguments to move the head if needed
    }



    /**
     * the method construct the new ray reflected
     * from a point where another ray hits
     *
     * @param n     - the normal in point
     * @param point - the reflection point
     * @param v     - the normalized ray from the viewer point of view
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Point point,Vector v, Vector n) {

        double vn = v.dotProduct(n);

        if (vn == 0)
            return null;

        Vector r = v.subtract(n.scale( 2*vn));
        // use the constructor with 3 arguments to move the head if needed
        return new Ray(point,n,r);
    }


    /**
     *     the method calc the specular component of the light
     *
     * @param material the substance of the geometry
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
    private boolean unshaded (GeoPoint geoPoint, LightSource lightSource,Vector l, Vector n, double nl,double nv)
    {
        Vector LightDirection = lightSource.getL(geoPoint.point).scale(-1);// from the point to the light source

        Vector DELTA_vector = n.scale(nv < 0 ? DELTA : -DELTA);
        Point pointRay = geoPoint.point.add(DELTA_vector);
        Ray LightRay = new Ray(pointRay, LightDirection);

        double maxDistacne = lightSource.getDistance(geoPoint.point);

        List<GeoPoint> Intersections = scene.geometries.findGeoIntersections(LightRay, maxDistacne);


        if (Intersections!= null) {
            for (GeoPoint gp : Intersections) {
                if (gp.geometry.getMaterial().Kt.equals(Double3.ZERO)) {
                    return false;
                }
            }
            return true;
        }
        else//
            return true;


    }


        /**
         * the method determine the level of transparency of the point
         *
         * @param light    - the light source which we measure the distance from
         * @param l        - the vector between the light source and the point
         * @param n        - the normal in the point
         * @param gp       - the tested point
         * @param nv       - n*v
         * @return the transparency value
         */
        private Double3 transparency (GeoPoint gp, LightSource light, Vector l, Vector n,double nv)
        {// Pay attention to your method of distance screening
            Ray LightRay;
            Vector LightDirection = l.scale(-1);// from the point to the light source
            Point point = gp.point;

            if (nv < 0)
                LightRay = new Ray(point, n, LightDirection);
            else
                LightRay = new Ray(point, n.scale(-1), LightDirection);


            double maxDistacne = light.getDistance(point);
            List<GeoPoint> Intersections;
            if (!_bb)
             Intersections = scene.geometries.findGeoIntersections(LightRay, maxDistacne);
            else
             Intersections = scene.geometries.findIntersectBoundingRegion(LightRay);


            if (Intersections == null)
                return Double3.ONE;

            Double3 ktr = Double3.ONE;
//        loop over intersections and for each intersection which is closer to the
//        point than the light source multiply ktr by 𝒌𝑻 of its geometry.
//        Performance: if you get close to 0 – it’s time to get out (return 0)
            for (var geometry : Intersections) {
                ktr = ktr.product(geometry.geometry.getMaterial().Kt);
                if (ktr.lowerThan(MIN_CALC_COLOR_K))
                    return ktr;
            }

            return ktr;


        }
    }


