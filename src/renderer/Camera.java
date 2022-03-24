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

    public Camera(Point p0, Vector vTo, Vector vUp) {

        _p0=p0;
                //vTo and vup mast be ortogonal
                if (!isZero(vTo.dotProduct(vUp)))
                    throw new IllegalArgumentException("the vectors vUP and VTo arn't ortogonal");

                //normaliize the vectors

        _vTo= vTo.normalize();
        _vUp=vUp.normalize();

         _vRight = _vTo.crossProduct(_vUp);
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
        double xJ= -(j-(Nx-1)/2d)*Rx;

        //move to midle of pixel ij
        if (!isZero(xJ))
        {
            Pij=Pij.add(_vRight.Scale(xJ));
        }
        if (!isZero(yI))
        {
            Pij=Pij.add(_vUp.Scale(yI));
        }

        //return ray from camerea to view plane coordinate(i,j)
        return new Ray(_p0,Pij.subtract(_p0));

    }
}
