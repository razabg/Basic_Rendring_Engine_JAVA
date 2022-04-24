package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {


    private Point _p0; // camera location
    private Vector _vTo; // y axis vector
    private Vector _vUp;// x axis vector
    private Vector _vRight; // z axis vector

    private double _distance;

    private int _width;
    private int _height;

    private ImageWriter imageWriter;
    private RayTracer rayTracer;

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
        rayTracer = builder.rayTracer;
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


    /**
     * this method create ray that going from the camera to the center of the view plane
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

    public RayTracer getRayTracer() {
        return rayTracer;
    }

    public void writeToImage() {
        imageWriter.writeToImage();
    }

    public void renderImage() {
        // TODO
    }

    public void printGrid(int interval, Color color) {
        for (int i = 0; i <= imageWriter.getNx(); i++) {
            for (int j = 0; j <= imageWriter.getNy(); j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(i, j, color);
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
            private RayTracer rayTracer = null;


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


            public CameraBuilder setRayTracer(RayTracer rayTracer) {
                this.rayTracer = rayTracer;
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


    }


