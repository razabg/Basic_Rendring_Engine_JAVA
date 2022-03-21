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
    public List<Point> findIntsersections(Ray ray);
}

