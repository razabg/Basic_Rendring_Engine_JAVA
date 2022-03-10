package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.*;

public class Geometries implements Intersectable {

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
    public List<Point> findIntsersections(Ray ray) {
        List<Point> result=null;

        //TODO comment here
        for (var item: _intersectables)
        {
            List <Point> itemList= item.findIntsersections(ray);

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
