package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {


    private Point _p0;
    private Vector _vTo;
    private Vector _vUp;
    private Vector _vRight;

    private double _distance;

    private int _width;
    private int _height;

    /**
     *
     * @param p0
     * @param vTo
     * @param vUp
     */
    private Camera(CameraBuilder builder) {
        _p0 = builder._p0;
        _vTo = builder._vTo;
        _vUp = builder._vUp;
        _vRight = builder._vRight;
        _height = builder._height;
        _width = builder._width;
        _distance = builder._distance;
    }

    /**
     * set view plane distance
     * @param distance distancebetween camera and view plane
     * @return
     */
    public Camera setVPDistance(double distance) {

        _distance=distance;
        return this;

    }

    public Camera setVPSize(int width, int height) {
        _width=width;
        _height=height;
        return this;
    }

    public Ray constructRay(int Nx, int Ny, int j, int i) {

        //Image center
        Point Pc= _p0.add(_vTo.Scale(_distance));

        //Ratio width and height
        double Ry=(double) _height/Ny;
        double Rx=(double) _width/Nx;

        Point Pij= Pc;
        //bring thing from the lecture

        double yI= -(i-(Ny-1)/2d)*Ry;
        double xJ= (j-(Nx-1)/2d)*Rx;

        //move to middle of pixel ij
        if (!isZero(xJ))
        {
            Pij=Pij.add(_vRight.Scale(xJ));
        }
        if (!isZero(yI))
        {
            Pij=Pij.add(_vUp.Scale(yI));
        }

        //return ray from camera to view plane coordinate(i,j)
        return new Ray(_p0,Pij.subtract(_p0));

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


    public static class CameraBuilder{

        private Point _p0;
        private Vector _vTo;
        private Vector _vUp;
        private Vector _vRight;

        private double _distance;

        private int _width;
        private int _height;


        public CameraBuilder(Point p0, Vector vTo, Vector vUp) {

            _p0=p0;

            //vTo and vup must be orthogonal
            if (!isZero(vTo.dotProduct(vUp)))
                throw new IllegalArgumentException("the vectors vUP and VTo aren't orthogonal");

            //normalize the vectors

            _vTo= vTo.normalize();
            _vUp=vUp.normalize();

            _vRight = _vTo.crossProduct(_vUp);
        }

        public  CameraBuilder setDistance(double distance) { //
            _distance=distance;
            return this;
        }

        public CameraBuilder setSize(int width, int height) {
            _width = width;
            _height = height;
            return this;
        }


        public Camera build() {
            Camera camera = new Camera(this);
            return camera;
        }



    }

}
