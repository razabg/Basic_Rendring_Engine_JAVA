package primitives;

public class Material {
    /**
     *  Kd - diffuse component, represents the scattering of light rays to all directions from the surface
     */
    public Double3 kD = new Double3(0,0,0);

    /**
     *  Ks - specular component, represents the reflectance of the light source over the surface
     */
    public Double3 kS = new Double3(0,0,0);

    /**
     *  Shininess - how shiny the material is
     */
    public int nShininess = 0;
    /**
     *  Kt - transparency component
     * 0.0 is sealed
     * 1.0 is clear
     */
    public Double3 Kt = new Double3(0,0,0);
    /**
     *  Kr - reflection component
     * 0.0 is matte
     * 1.0 is very reflexive
     */
    public Double3 Kr = new Double3(0,0,0);
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

    /**
     * set transparency component
     * @param  kt - transparency component
     * @return
     */
    public Material setKt(Double3 kt) {
       this.Kt = kt;
        return this;
    }

    /**
     * set reflection component
     * @param  kr - reflection component
     * @return
     */
    public Material setKr(Double3 kr) {
       this. Kr = kr;
        return this;
    }

    /**
     * set ransparency component
     * @param  kt - transparency component
     * @return
     */
    public Material setKt(double kt) {
        this.Kt = new Double3(kt);
        return this;
    }

    /**
     * set Shininess
     * @param  kr - reflection component
     * @return
     */
    public Material setKr(double kr) {
        this. Kr = new Double3(kr);
        return this;
    }
}
