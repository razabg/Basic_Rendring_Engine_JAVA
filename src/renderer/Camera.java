package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingFormatArgumentException;
import java.util.MissingResourceException;

import static primitives.Util.isZero;

public class Camera {


    private Point _p0; // camera location
    private Vector _vTo; // y axis vector
    private Vector _vUp;// x axis vector
    private Vector _vRight; // z axis vector

    private double _distance;//object's actual distance from the camera center

    private int _width;//actual width
    private int _height;//actual height

    private ImageWriter imageWriter; //the object create the image
    private RayTracerBase rayTracerBase;//the object used to trace rays for the rendering engine

    /**
     * private ctor using the builder design pattern
     *
     * @param builder
     */
    private Camera(CameraBuilder builder) {
        _p0 = builder._p0;
        _vTo = builder._vTo;
        _vUp = builder._vUp;
        _vRight = builder._vRight;
        _height = builder._height;
        _width = builder._width;
        _distance = builder._distance;
        imageWriter = builder.imageWriter;
        rayTracerBase = builder.rayTracerBase;
    }


    /**
     * this method create ray that going from the camera to a specific pixel
     *
     * @param Nx -amount of rows in view plane (number of pixels)
     * @param Ny -amount of columns in view plane (number of pixels)
     * @param j  -X's index
     * @param i  -Y's index
     * @return - the ray which goes through the pixel
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {

        //Image center
        Point Pc = _p0.add(_vTo.scale(_distance));

        //Ratio width and height
        double Ry = (double) _height / Ny;
        double Rx = (double) _width / Nx;

        Point Pij = Pc;
        //bring thing from the lecture

        double yI = -(i - (Ny - 1) / 2d) * Ry;
        double xJ = (j - (Nx - 1) / 2d) * Rx;

        //move to middle of pixel ij
        if (!isZero(xJ)) {
            Pij = Pij.add(_vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            Pij = Pij.add(_vUp.scale(yI));
        }

        //return ray from camera to view plane coordinate(i,j)
        return new Ray(_p0, Pij.subtract(_p0));

    }

    /**
     * the method calc the color of the pixel according to the ray
     *
     * @param col x axis
     * @param row y axis
     * @return color
     */
    private Color CastRay(int col, int row) {

        Ray RayToRender = constructRay(imageWriter.getNx(), imageWriter.getNy(), col, row);
        Color color = rayTracerBase.traceRay(RayToRender);
        return color;


    }

    /**
     * the method check if imageWriter is not equal to null and then
     * it will create the image using the imageWriter.writeToImage
     */
    public void writeToImage() {

        if (imageWriter == null)
            throw new MissingResourceException("image writer cannot be null", "Camera", null);

        imageWriter.writeToImage();
    }

    /**
     * the method render the image with the given data
     */
    public void renderImage() {

        if (_p0 == null || _vTo == null || _vUp == null ||
                _vRight == null || _distance == 0 || _width == 0 || _height == 0 ||
                imageWriter == null || rayTracerBase == null) {
            throw new MissingFormatArgumentException("fields cannot get null");
        }
      //  throw new UnsupportedOperationException(); //check where should be

        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                imageWriter.writePixel(i, j, CastRay(i, j));
            }
        }
    }









    /**
     * the method will create grid of lines
     * @param interval
     * @param color
     */
    public void printGrid(int interval, Color color)  {
        if (imageWriter == null) {
            throw new MissingResourceException("image writer cannot be null","Camera",null);
        }
        else{
            for (int i = 0; i < imageWriter.getNx(); i++) {
                for (int j = 0; j < imageWriter.getNy(); j++) {
                    if (i % interval == 0 || j % interval == 0) {
                        imageWriter.writePixel(i, j, color);
                    }
                }
            }
        }
    }

        /**
         * Implementation of builder pattern.
         */
        public static class CameraBuilder {

            private Point _p0;
            private Vector _vTo;
            private Vector _vUp;
            private Vector _vRight;

            private double _distance;

            private int _width;
            private int _height;

            private ImageWriter imageWriter = null;
            private RayTracerBase rayTracerBase = null;


            /**
             * c-tor for basic camera
             *
             * @param p0  the location point of the camera
             * @param vTo Y axis vector
             * @param vUp X axis vector
             */
            public CameraBuilder(Point p0, Vector vTo, Vector vUp) {

                _p0 = p0;

                //vTo and vup must be orthogonal
                if (!isZero(vTo.dotProduct(vUp)))
                    throw new IllegalArgumentException("the vectors vUP and VTo aren't orthogonal");

                //normalize the vectors

                _vTo = vTo.normalize();
                _vUp = vUp.normalize();


                _vRight = _vTo.crossProduct(_vUp);

            }

            /**
             * set distance between the camera and the view-plane
             *
             * @param distance distance
             * @return the new camera for optional adding attribute
             */
            public CameraBuilder setVPDistance(double distance) { //
                _distance = distance;
                return this;
            }

            /**
             * set size of the view-plane
             *
             * @param width  width the view-plane
             * @param height height the view-plane
             * @return the new camera for optional adding attribute
             */
            public CameraBuilder setVPSize(int width, int height) {
                _width = width;
                _height = height;
                return this;
            }

            public CameraBuilder setImageWriter(ImageWriter imageWriter) {
                this.imageWriter = imageWriter;
                return this;
            }


            public CameraBuilder setRayTracer(RayTracerBase rayTracerBase) {
                this.rayTracerBase = rayTracerBase;
                return this;
            }

            /**
             * build the new camera with the relevant attribute
             *
             * @return new camera
             */
            public Camera build() {
                Camera camera = new Camera(this);
                return camera;
            }

        }
    public Point get_p0() {
        return _p0;
    }

    public Vector get_vTo() {
        return _vTo;
    }

    public Vector get_vUp() {
        return _vUp;
    }

    public Vector get_vRight() {
        return _vRight;
    }

    public double get_distance() {
        return _distance;
    }

    public int get_width() {
        return _width;
    }

    public int get_height() {
        return _height;
    }

    public ImageWriter getImageWriter() {
        return imageWriter;
    }

    public RayTracerBase getRayTracer() {
        return rayTracerBase;
    }


    /**
     * set view plane distance
     *
     * @param distance distancebetween camera and view plane
     * @return
     */
    public Camera setVPDistance(double distance) {
        _distance = distance;
        return this;

    }

    /**
     * the method calc the size of the view plane
     *
     * @param width
     * @param height
     * @return
     */
    public Camera setVPSize(int width, int height) {
        _width = width;
        _height = height;
        return this;
    }

    }


