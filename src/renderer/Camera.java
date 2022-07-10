package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.MissingResourceException;
import java.util.stream.IntStream;


import java.util.*;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


public class Camera {

    private static final String RESOURCE_ERROR = "Renderer resource not set";
    private static final String IMAGE_WRITER_COMPONENT = "Image writer";
    private static final String CAMERA_CLASS = "Camera";
    private static final String RAY_TRACER_COMPONENT = "Ray tracer";


    private Point _p0; // camera location
    private Vector _vTo; // y axis vector
    private Vector _vUp;// x axis vector
    private Vector _vRight; // z axis vector

    private double _distance;//object's actual distance from the camera center

    private double _width;//actual width
    private double _height;//actual height

    private ImageWriter imageWriter; //the object create the image
    private RayTracerBase rayTracerBase;//the object used to trace rays for the rendering engine


    // ******** params for Anti-Aliasing effect *********
    /**
     * number of rays in a single pixel for active super sampling
     */
    private int numOfRaysInPixel = 0;
    /**
     * boolean value to determine anti aliasing
     */
    private boolean AntiAliasing;



    // ***** params for Depth of field effect ****
    /**
     * the size of the aperture surface (located in the camera location), the size is relative to a single pixel, example: 1 = one pixel, and so on.
     * the smaller the aperture the greater the width of the sharpness, (similar to blind view without glasses)
     * (smaller = more objects in focus, bigger = less objects in focus, stronger blurring)
     */
    private double ApertureSize;
    /**
     * the distance between the camera's beginning location to the center of focal plane,
     * forward vision becomes sharper in the distance, the more it's close to the focal plane
     * (focal plane, the second imaginary plane, in which the beam rays emitted from the matching pixel in the view plane are passing through)
     */
    private double FocalDistance;
    /**
     * number of rays in the focal plane of the camera (per pixel),
     * the more rays in focal plane the more quality of the blurring
     */
    private int numOfRaysInAperture;
    /**
     * boolean value to set whether the camera has depth of field (bokeh) effect of not
     */
    private boolean DepthOfField;


    private int threadsCount = 0; // num of threads
    private static final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
    private double printInterval = 0l; // print interval in seconds

    /**
     * Set multi-threading <br>
     * - if the parameter is 0 - number of cores less 2 is taken
     *
     * @param threads number of threads
     * @return the Render object itself
     */
    public Camera setMultithreading(int threads) {
        if (threads < -2)
            throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");
        if (threads >= -1)
            this.threadsCount = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            this.threadsCount = cores <= 2 ? 1 : cores;
        }
        return this;
    }




//todo add to  camera builder
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
     * this method gets the view plane size and a selected pixel,
     * and return the rays from the view plane which intersects the focal plane
     *
     * @param nX - amount of columns in view plane (number of pixels)
     * @param nY - amount of rows in view plane (number of pixels)
     * @param j  - X's index
     * @param i  - Y's index
     * @return - the list of rays which goes from the pixel through the focal plane
     */
    public List<Ray> constructRaysThroughPixel(int nX, int nY, int j, int i) {

        // the returned list of raysS
        List<Ray> rays = new ArrayList<>();

        // add the center ray to the list
        Ray centerRay = constructRay(nX, nY, j, i);
        rays.add(centerRay);

        // calculate the actual size of a pixel
        // pixel height is the division of the view plane height in the number of rows of pixels
        double pixelHeight = alignZero(_height / nY);   //  Ry = h/Ny
        // pixel width is the division of the view plane width in the number of columns of pixels
        double pixelWidth = alignZero(_width / nX);   //  Rx = w/Nx
        //anti-aliasing
        if (numOfRaysInPixel != 1) {
            rays.addAll(centerRay.randomRaysInGrid(
                    get_vUp(),
                    get_vRight(),
                    numOfRaysInPixel,
                    _distance,
                    pixelWidth,
                    pixelHeight)
            );
        }

        // if more than one ray is emitted (DOF effect)
        if (numOfRaysInAperture != 1) {
            List<Ray> temp_rays = new LinkedList<>();
            // apertureSize is the value of how many pixels it spreads on
            double apertureRadius = Math.sqrt(ApertureSize * (pixelHeight * pixelWidth)) / 2d;
            for (Ray ray : rays) {
                // creating list of focal rays (from the aperture on the view plane to the point on the focal plane)
                temp_rays.addAll(ray.randomRaysInCircle(ray.getP0(), get_vUp(), get_vRight(), apertureRadius, numOfRaysInAperture, FocalDistance));
            }
            // the original rays included in the temp rays
            rays = temp_rays;
        }

        return rays;
    }

    /**
     * the method calc the color of the pixel according to the ray
     *
     * @param nX x axis
     * @param nY y axis
     * @param  j x index
     * @param  i y index
     * @return color
     */
    private void CastRay(int nX, int nY,int i ,int j) {

        Ray RayToRender = constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
        Color color = rayTracerBase.traceRay(RayToRender);
        imageWriter.writePixel(j, i, color);

    }


    /**
     * Cast beam of rays from the pixel in the view plane to the focal point in the focal plane
     *
     * @param nX  - resolution on X axis (number of pixels in row)
     * @param nY  - resolution on Y axis (number of pixels in column)
     * @param col - pixel's column number (pixel index in row)
     * @param row - pixel's row number (pixel index in column)
     */
    private void castBeam(int nX, int nY, int col, int row) {
        List<Ray> rays = this.constructRaysThroughPixel(nX, nY, col, row);

        List<Color> colors = new LinkedList<>();
        for (Ray ray : rays) {
            colors.add(rayTracerBase.traceRay(ray));
        }
        imageWriter.writePixel(col, row, Color.avgColor(colors));
    }


    /**
     * the method check if imageWriter is not equal to null and then
     * it will create the image using the imageWriter.writeToImage
     */
    public void writeToImage() {


        if (imageWriter == null)
            throw new MissingResourceException("image writer cannot be null", "Camera", null);
        checkResources();
        imageWriter.writeToImage();
    }

    /**
     * the method render the image with the given data
     */
    public Camera renderImage() {


        checkResources();

        final int nX = imageWriter.getNx();
        final int nY = imageWriter.getNy();
        Pixel.initialize(nY, nX, printInterval);
        if (threadsCount == 0) {
            for (int i = 0; i < nY; ++i)
                for (int j = 0; j < nX; ++j) {
                    if (!this.is_DepthOfField() && !this.is_AntiAliasing()) {
                        CastRay(nX, nY, i, j);
                        Pixel.pixelDone();
                        Pixel.printPixel();
                    }
                    else {
                        castBeam(nX, nY, i, j);
                        Pixel.pixelDone();
                        Pixel.printPixel();
                    }
                }
//            // if it is not, proceed as usual (cast a single ray)
//            if (!this.is_DepthOfField() && !this.is_AntiAliasing()) {
//                CastRay(Nx,Ny,i,j);
//            }
//            // if it is on, cast a beam, instead of a single ray
//            else castBeam(Nx, Ny, i, j);

        } else if (threadsCount == -1) {
            IntStream.range(0, nY).parallel().forEach(i -> IntStream.range(0, nX).parallel().forEach(j -> {
                if (!this.is_DepthOfField() && !this.is_AntiAliasing()) {
                    CastRay(nX, nY, i, j);
                    Pixel.pixelDone();
                    Pixel.printPixel();
                }
                else {
                    castBeam(nX, nY, i, j);
                    Pixel.pixelDone();
                    Pixel.printPixel();
                }
            }));
        } else {
            while (threadsCount-- > 0) {
                new Thread(() -> {
                    for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                        if (!this.is_DepthOfField() && !this.is_AntiAliasing()) {
                            CastRay(nX, nY, pixel.row,pixel.col);
                        }
                        else {
                            castBeam(nX, nY, pixel.row,pixel.col);
                        }

                }).start();
            }
            Pixel.waitToFinish();
        }
        return this;
    }





    /**
     * the method will create grid of lines
     * @param interval
     * @param color
     */
    public void printGrid(int interval, Color color)  {
        if (imageWriter == null) {
            throw new MissingResourceException(RESOURCE_ERROR, CAMERA_CLASS, IMAGE_WRITER_COMPONENT);

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


    public Vector get_vUp() {
        return _vUp;
    }

    public Vector get_vRight() {
        return _vRight;
    }


    public ImageWriter getImageWriter() {
        return imageWriter;
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


    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Camera setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }


    public Camera setNumOfRaysInPixel(int numOfRaysInPixel) {
        this.numOfRaysInPixel = numOfRaysInPixel;
        return this;
    }

    public Camera setAntiAliasing(boolean antiAliasing) {
        AntiAliasing = antiAliasing;
        return this;
    }

    public Camera setApertureSize(double apertureSize) {
        ApertureSize = apertureSize;
        return this;
    }

    public Camera setFocalDistance(double focalDistance) {
        FocalDistance = focalDistance;
        return this;
    }

    public Camera setNumOfRaysInAperture(int numOfRaysInAperture) {
        this.numOfRaysInAperture = numOfRaysInAperture;
        return this;
    }

    public Camera setDepthOfField(boolean depthOfField) {
        this.DepthOfField = depthOfField;
        return this;
    }



    /**
     * get if DOF is activated in camera
     *
     * @return whether the DOF effect is activated or not
     */
    public boolean is_DepthOfField() {
        return DepthOfField;
    }

    /**
     * get if AA is activated in camera
     *
     * @return whether the AA effect is activated or not
     */
    public boolean is_AntiAliasing() {
        return AntiAliasing;
    }



    /**
     * Set debug printing interval. If it's zero - there won't be printing at all
     *
     * @param interval printing interval in seconds
     * @return the Render object itself
     */
    public Camera setDebugPrint(double interval)
    {
        printInterval = interval;
        return this;

    }

    /**
     * Throws exception if there is a missing resource
     */
    private void checkResources() {
        if (imageWriter == null)
            throw new MissingResourceException(RESOURCE_ERROR, CAMERA_CLASS, IMAGE_WRITER_COMPONENT);
        if (rayTracerBase == null)
            throw new MissingResourceException(RESOURCE_ERROR, CAMERA_CLASS, RAY_TRACER_COMPONENT);
    }




}


