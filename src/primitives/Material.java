package primitives;

public class Material {

    public Double3 kD = new Double3(0,0,0);
    public Double3 kS = new Double3(0,0,0);
    public int nShininess = 0;

    /**
     * set the kd
     * @param kD Double3
     * @return the object
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return  this;
    }

    /**
     * set kd
     * @param kD double
     * @return the object
     */
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return  this;
    }

    /**
     * set ks
     * @param kS Double3
     * @return the object
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return  this;
    }

    /**
     * set ks
     * @param kS double
     * @return the object
     */
    public Material setkS(double kS) {
        this.kS = new Double3(kS) ;
        return  this;
    }

    /**
     * set Shininess
     * @param nShininess
     * @return
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return  this;
    }
}