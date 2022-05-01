package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.*;

public class Geometries extends Intersectable {

    private List<Intersectable> _intersectables;

    public Geometries(Intersectable... intersectables)
    {
      _intersectables= new LinkedList<Intersectable>();
      Collections.addAll(_intersectables,intersectables);

    }

    public Geometries() {
        _intersectables= new LinkedList<Intersectable>();
    }

    public void add(Intersectable... intersectables)
    {

        Collections.addAll(_intersectables,intersectables);


    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
      return null;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result=null;

        //TODO comment here
        for (var item: _intersectables)
        {
            List <Point> itemList= item.findIntersections(ray);

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
