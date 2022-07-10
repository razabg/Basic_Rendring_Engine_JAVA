package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;


/**
 * class represents axis-aligned bounding box, it is used to check if ray is in the area of a geometry
 * by checking if the ray direction come with intersection in the bounding box of the geometry.
 * It means for us that the calculation of all the intersections of the same ray should be considered
 */
public class BoundingBox {

    /**
     * _xMin - the minimum value of X coordinate of this bounding box
     */
    private double _xMin = Double.POSITIVE_INFINITY;
    /**
     *  _yMin - the minimum value of Y coordinate of this bounding box
     */
    private double _yMin = Double.POSITIVE_INFINITY;
    /**
     *  _zMin - the minimum value of Z coordinate of this bounding box
     */
    private double _zMin = Double.POSITIVE_INFINITY;
    /**
     *   xMax - the maximum value of X coordinate of this bounding box
     */
    private double _xMax = Double.NEGATIVE_INFINITY;
    /**
     * _yMax - the maximum value of Y coordinate of this bounding box
     */
    private double _yMax = Double.NEGATIVE_INFINITY;
    /**
     * _zMax - the maximum value of Z coordinate of this bounding box
     */
    private double _zMax = Double.NEGATIVE_INFINITY;


    /**
     * Setter for new values for class BoundingBox, with 6 inputs, minimum value and maximum value inputs per axis
     *
     * @param x1 minimum/maximum value in x axis
     * @param x2 minimum/maximum value in x axis
     * @param y1 minimum/maximum value in y axis
     * @param y2 minimum/maximum value in y axis
     * @param z1 minimum/maximum value in z axis
     * @param z2 minimum/maximum value in z axis
     */
    public void setBoundingBox(double x1, double x2, double y1, double y2, double z1, double z2) {
        this._xMin = Math.min(x1, x2);
        this._xMax = Math.max(x1, x2);
        this._yMin = Math.min(y1, y2);
        this._yMax = Math.max(y1, y2);
        this._zMin = Math.min(z1, z2);
        this._zMax = Math.max(z1, z2);
    }


    /**
     * get min X value
     * @return min x
     */
    public double getMinX() {
        return _xMin;
    }
    /**
     * get max X value
     * @return max x
     */
    public double getMaxX() {
        return _xMax;
    }
    /**
     * get min y value
     * @return min y
     */
    public double getMinY() {
        return _yMin;
    }
    /**
     * get max y value
     * @return  max y
     */
    public double getMaxY() {
        return _yMax;
    }
    /**
     * get min z value
     * @return min z
     */
    public double getMinZ() {
        return _zMin;
    }
    /**
     * get max z value
     * @return  max z
     */
    public double getMaxZ() {
        return _zMax;
    }

    /**
     * method that check a ray intersects the bounding region
     *
     * @param ray the ray to check for intersection
     * @return boolean result, true if intersects, false otherwise
     *
     */
    public boolean intersectBV(Ray ray) {
        Point p0 = ray.getP0();
        Point dirHead = new Point(ray.getDir().getXyz());

        // the coordinates of the ray direction
        double dirHeadX = dirHead.get_x();
        double dirHeadY = dirHead.get_y();
        double dirHeadZ = dirHead.get_z();

        // the coordinates of the ray starting point
        double rayStartPointX = p0.get_x();
        double rayStartPointY = p0.get_y();
        double rayStartPointZ = p0.get_z();

        // define default variables for calculations
        double t_xMin;
        double t_xMax;
        double t_yMin;
        double t_yMax;
        double t_zMin;
        double t_zMax;

        // for all 3 axes:
        //
        // calculate the intersection distance t0 and t1
        // (t_Min represent the min and t_Max represent the max)
        //
        //  1. when the values for t are negative, the box is behind the ray (no need to find intersections)
        //  2. if the ray is parallel to an axis it will not intersect with the bounding volume plane for this axis.
        //  3. we first find where the ray intersects the planes defined by each face of the bounding cube,
        //     after that, we find the ray's first and second intersections with the planes.

        if (dirHeadX > 0) {
            t_xMax = (_xMax - rayStartPointX) / dirHeadX;
            if (t_xMax <= 0) {
                return false; // if value for t_Max is negative, the box is behind the ray.
            }
            t_xMin = (_xMin - rayStartPointX) / dirHeadX;
        } else if (dirHeadX < 0) {
            t_xMax = (_xMin - rayStartPointX) / dirHeadX;
            if (t_xMax <= 0) {
                return false; // if value for t_Max is negative, the box is behind the ray.
            }
            t_xMin = (_xMax - rayStartPointX) / dirHeadX;
        } else { // preventing parallel to the x axis
            t_xMax = Double.POSITIVE_INFINITY;
            t_xMin = Double.NEGATIVE_INFINITY;
        }

        if (dirHeadY > 0) {
            t_yMax = (_yMax - rayStartPointY) / dirHeadY;
            if (t_yMax <= 0) {
                return false; // if value for t_yMax is negative, the box is behind the ray.
            }
            t_yMin = (_yMin - rayStartPointY) / dirHeadY;
        } else if (dirHeadY < 0) {
            t_yMax = (_yMin - rayStartPointY) / dirHeadY;
            if (t_yMax <= 0) {
                return false; // if value for t_yMax is negative, the box is behind the ray.
            }
            t_yMin = (_yMax - rayStartPointY) / dirHeadY;
        } else { // preventing parallel to the y axis
            t_yMax = Double.POSITIVE_INFINITY;
            t_yMin = Double.NEGATIVE_INFINITY;
        }
        // cases where the ray misses the cube
        // the ray misses the box when t0x is greater than t1y and when t0y is greater than  t1x
        if ((t_xMin > t_yMax) || (t_yMin > t_xMax)) {
            return false;
        }

        // we find which one of these two points lie on the cube by comparing their values:
        // we simply need to chose the point which value for t is the greatest.
        if (t_yMin > t_xMin)
            t_xMin = t_yMin;
        // we find the second point where the ray intersects the box
        // we simply need to chose the point which value for t is the smallest
        if (t_yMax < t_xMax)
            t_xMax = t_yMax;

        if (dirHeadZ > 0) {
            t_zMax = (_zMax - rayStartPointZ) / dirHeadZ;
            if (t_zMax <= 0) {
                return false; // if value for t_zMax is negative, the box is behind the ray.
            }
            t_zMin = (_zMin - rayStartPointZ) / dirHeadZ;
        } else if (dirHeadZ < 0) {
            t_zMax = (_zMin - rayStartPointZ) / dirHeadZ;
            if (t_zMax <= 0) {
                return false; // if value for t_zMax is negative, the box is behind the ray.
            }
            t_zMin = (_zMax - rayStartPointZ) / dirHeadZ;
        } else { // preventing parallel to the z axis
            t_zMax = Double.POSITIVE_INFINITY;
            t_zMin = Double.NEGATIVE_INFINITY;
        }

        // cases where the ray misses the cube
        // the ray misses the box when t0 is greater than t1z and when t0z is greater than  t1
        return (!(t_xMin > t_zMax)) && (!(t_zMin > t_xMax));
    }

    /**
     * calculate volume of BoundingBox
     *
     * @return the volume of bounding box
     */
    public double size() {
        return (_xMax - _xMin) * (_yMax - _yMin) * (_zMax - _zMin);
    }


    /**
     * function to get the center of the bounding box
     *
     * @return the Point3D in the middle of the bounding box
     */
    public Point getBoundingBoxCenter() {
        return new Point(
                (getMaxX() + getMinX()) / 2,
                (getMaxY() + getMinY()) / 2,
                (getMaxZ() + getMinZ()) / 2
        );
    }

    /**
     * function to get the distance between the centers of two bounding boxes
     *
     * @param boundingBox - the other bounding box
     * @return the distance between the center of the boxes
     */
    public double BoundingBoxDistance(BoundingBox boundingBox) {
        return this.getBoundingBoxCenter().distance(boundingBox.getBoundingBoxCenter());
    }

    /**
     * creates string which identify the values of the bounding region
     *
     * @return a string of minimum Vector and maximum Vector
     */
    @Override
    public String toString() {
        return "minimum : (" + _xMin + " " + _yMin + " " + _zMin + ") \n" +
                "maximum : (" + _xMax + " " + _yMax + " " + _zMax + ")";
    }


    /**
     * Bounding Box method equals implementation
     *
     * @param obj - another object to compare with
     * @return boolean result (same values of the bounding boxes)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BoundingBox)) return false;
        BoundingBox other = (BoundingBox) obj;
        // Purpose: Make sure that all vertices of bounding boxes are equal:
        return Util.isZero(_xMin - other._xMin) &&
                Util.isZero(_yMin - other._yMin) &&
                Util.isZero(_zMin - other._zMin) &&
                Util.isZero(_xMax - other._xMax) &&
                Util.isZero(_yMax - other._yMax) &&
                Util.isZero(_zMax - other._zMax);
    }
}