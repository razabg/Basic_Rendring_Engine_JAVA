package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.*;


/**
 * class that represents a group of shapes that represent a picture.
 * Composite class which includes components and composite geometries
 */
public class Geometries extends Intersectable {


    /**
     * _intersectables - list of all components in the scene
     */
    private List<Intersectable> _intersectables;

    /**
     * constructor of class, creates the list and for now it is empty.
     * implements as a linked list that allows to delete members if necessary.
     * after initializing, it adds shapes to the list, by using the addAll method.
     *
     * @param intersectables - shapes to be added to the constructed instance
     */
    public Geometries(Intersectable... intersectables)
    {
      _intersectables= new LinkedList<Intersectable>();
      Collections.addAll(_intersectables,intersectables);

    }


    /**
     * constructor of class, creates the list and for now it is empty.
     * implements as a linked list that allows to delete members if necessary.
     */
    public Geometries() {
        _intersectables= new LinkedList<Intersectable>();
    }


    /**
     * a method that receive one or more shape and adds to this list.
     *
     * @param intersectables - shapes to be added to this instance
     */
    public void add(Intersectable... intersectables)
    {

        Collections.addAll(_intersectables,intersectables);


    }


    /**
     * a method that receive a ray and find all intersections of this ray with the shapes in this class
     *
     * @param ray         - the ray to be checked with the shapes
     * @param maxDistance - the upper bound of distance, any point which
     *                    its distance is greater than this bound will not be returned
     * @return list of all intersections in a form of GeoPoint
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        List<GeoPoint> result=null;


        for (var item: _intersectables)
        {
            List <GeoPoint> itemList= item.findGeoIntersections(ray);

            if(itemList!= null) {
                if(result==null)
                {
                    result = new LinkedList<>();
                }
                result.addAll(itemList);
            }
        }

        return result;
    }



}
