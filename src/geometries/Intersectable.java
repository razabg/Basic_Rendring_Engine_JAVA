package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;


/**
 *
 * interface for all objects that can intersect with a ray
 */
public interface Intersectable {


    /**
     *  find all intersection {@link Point}s to the specific object
     * @param ray ray toward the object
     *
     */
   // public final List<Point> findIntersections(Ray ray){
    //    List<Point> geoList=findGeoIntersections(ray);
  //      return geoList==null?null
    //            :geoList.stream().map(gp->gp.point).toList();
  //  }

   // private List<Point> findGeoIntersections(Ray ray) {

  //  }

    //private class GeoPoint {
     //   Geometry geometry;
     //   Point point;

    //    public GeoPoint(Geometry geometry, Point point) {
    //        this.geometry = geometry;
      //      this.point = point;
     //   }


   // }


     public  List<Point> findIntersections(Ray ray);

}



