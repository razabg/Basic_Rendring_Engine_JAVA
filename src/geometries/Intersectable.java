package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;


/**
 *
 * interface for all objects that can intersect with a ray
 */
public abstract class Intersectable {


    /**
     * PDS
     *  in order to find the correct color in a point, we need to
     *   take into consideration the
     *    shape which the light is bouncing from
     *
     *    Helper class representing a point on a geometry surface
     **/



    public static class GeoPoint {
       public Geometry geometry;
       public Point point;

        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }




        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    public List<GeoPoint> findGeoIntersections(Ray ray){
      return   findGeoIntersectionsHelper(ray);
    }

     protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
    public abstract List<Point> findIntersections(Ray ray);

    }


/**
 * find all intersection {@link Point}s to the specific object
 *
 * @param ray ray toward the object
 */
// public final List<Point> findIntersections(Ray ray){
//    List<Point> geoList=findGeoIntersections(ray);
//      return geoList==null?null
//            :geoList.stream().map(gp->gp.point).toList();
//  }

// private List<Point> findGeoIntersections(Ray ray) {

//  }


