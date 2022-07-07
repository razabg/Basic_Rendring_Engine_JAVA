package geometries;

import primitives.Ray;

import java.util.*;


/**
 * class that represents a group of shapes that represent a picture.
 * Composite class which includes components and composite geometries
 */
public class Geometries extends Container {


    /**
     * _intersectables - list of all components in the scene
     */
    private List<Container> _containers;


    /**
     * constructor of class, creates the list and for now it is empty.
     * implements as a linked list that allows to delete members if necessary.
     */
    public Geometries() {
        _containers = new LinkedList<Container>();
        this.setBoundingBox();
    }


    /**
     * constructor of class, creats the list and for now it is empty.
     * implements as a linked list that allows to delete members if necessary.
     * after initializing, it adds shapes to the list, by using the add method.
     *
     * @param geometries - shapes to be added to the constructed instance
     */
    public Geometries(Container... geometries) {
        _containers = new LinkedList<>();
        add(geometries);
        this.setBoundingBox();
    }


    /**
     * a method that receive one or more shape and adds to this list.
     *
     * @param geometries - shapes to be added to this instance
     */
    public void add(Container... geometries) {
        _containers.addAll(Arrays.asList(geometries));
    }

    /**
     * remove method allow removing (even zero) geometries from the composite class
     *
     * @param geometries which we want to add to the composite class
     * @return the geometries after the remove
     */
    public Geometries remove(Container... geometries) {
        _containers.removeAll(Arrays.asList(geometries));
        return this;
    }

    /**
     * a method that receive shapes and adds to this list.
     *
     * @param geometries - shapes to be added to this instance
     */
    public void addAll(List<Geometry> geometries) {
        _containers.addAll(geometries);
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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance ,boolean bb) {
        List<GeoPoint> intersections = new LinkedList<>();
        for (Container geometry : _containers) {
            // declare list as null
            List<Intersectable.GeoPoint> geoIntersections = null;
            // if we don't want to use bounding boxes, find intersections as usual
            if (!bb) {
                geoIntersections = geometry.findGeoIntersectionsHelper(ray, maxDistance, false);
            }
            // but if we do want to use it, if the ray intersects the bounding box...
            else if (geometry.boundingBox.intersectBV(ray)) {
                geoIntersections = geometry.findGeoIntersectionsHelper(ray, maxDistance, true);
            }
            // else - geoIntersections will stay null as defined earlier..


            if (geoIntersections != null && geoIntersections.size() > 0) {
                intersections.addAll(geoIntersections);
            }
        }
        if (intersections.size() > 0) {
            return intersections;
        }
        return null;
    }



    /**
     * method sets the values of the bounding volume for each component
     */
    @Override
    public void setBoundingBox() {
        super.setBoundingBox();                 // first, create a default bounding region if necessary
        for (Container geo : _containers) {     // in a recursive call set bounding region for all the
            geo.setBoundingBox();               // components and composites inside
        }

        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;

        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;

        for (Container inter : _containers) {

            // get minimal & maximal x value for the containing box
            minX = Math.min(inter.boundingBox.getMinX(), minX);
            maxX = Math.max(inter.boundingBox.getMaxX(), maxX);

            // get minimal & maximal y value for the containing box
            minY = Math.min(inter.boundingBox.getMinY(), minY);
            maxY = Math.max(inter.boundingBox.getMaxY(), maxY);

            // get minimal & maximal z value for the containing box
            minZ = Math.min(inter.boundingBox.getMinZ(), minZ);
            maxZ = Math.max(inter.boundingBox.getMaxZ(), maxZ);
        }

        // set the minimum and maximum values in 3 axes for this bounding region of the component
        boundingBox.setBoundingBox(minX, maxX, minY, maxY, minZ, maxZ);
    }

    /**
     * automated build bounding volume hierarchy tree
     */
    public void BuildTree() {

        // flatten the list of Geometries
        this.flatten();

        // define reusable variables (improved performance)
        double distance;
        Container bestGeometry1 = null;
        Container bestGeometry2 = null;

        // while any container contains more then one geometry
        while (_containers.size() > 1) {
            double best = Double.MAX_VALUE;
            // loop through the containers
            for (Container geometry1 : _containers) {
                for (Container geometry2 : _containers) {
                    // measure the distance between every couple of bounding boxes
                    distance = geometry1.boundingBox.BoundingBoxDistance(geometry2.boundingBox);
                    // if the geometries are not the same geometry, and the distance is the lowest possible
                    if (!geometry1.equals(geometry2) && distance < best) {
                        // define the best distance as the minimal one we have found
                        best = distance;
                        // define the best candidates to be together in a container
                        bestGeometry1 = geometry1;
                        bestGeometry2 = geometry2;
                    }
                }
            }
            // after we have determined the best geometries to couple in a container,
            // create new container which contains the geometries
            _containers.add(new Geometries(bestGeometry1, bestGeometry2));
            // and remove the same ones from the original tree
            _containers.remove(bestGeometry1);
            _containers.remove(bestGeometry2);
        }


    }

    /**
     * method to flatten the geometries list
     */
    public void flatten() {
        // copy the containers to a new temporary list
        Geometries new_geometries = new Geometries(_containers.toArray(new Container[_containers.size()]));
        // clear the original list of containers
        _containers.clear();
        // call the second function which will make sure we only
        // have containers with simple instances of geometry
        flatten(new_geometries);
    }

    /**
     * recursive func to flatten the geometries list (break the tree)
     * receives a Geometries instance, flattens it and adds the shapes to this current instance
     *
     * @param new_geometries geometries
     */
    private void flatten(Geometries new_geometries) {
        // loop through the temporary list
        for (Container container : new_geometries._containers) {
            // if the container contains only a simple geometry
            if (container instanceof Geometry) {
                // add it to the original list
                _containers.add(container);
            }
            // else, this is instance of Geometries
            // which needs to get flattened as well
            else {
                // so send it recursively to this function
                flatten((Geometries) container);
            }
        }

    }

}
