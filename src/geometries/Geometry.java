package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;


/**
 * abstract class for all shapes,
 * objects with material and emission fields, and getNormal method
 * (and has the possibility to be intersected)
 */
public abstract class Geometry extends Container {

   /**
    * emission light
    */
   protected Color emission = Color.BLACK;


   /**
    * material type
    */
   private Material material = new Material();

   /**
    * the method return the orthogonal vector
    * @param point
    * @return
    */
   public abstract Vector getNormal(Point point);

   /**
    * getter for Material
    * @return
    */
   public Material getMaterial() {
      return material;
   }

   /**
    * the method return the self color of the shape
    * @return
    */
   public Color getEmission() {
      return emission;
   }

   /**
    * set the emission color of the shape
    * @param emission
    * @return
    */
   public Geometry setEmission(Color emission) {
      this.emission = emission;
      return this;
   }

   /**
    * setter of material, chaining method design pattern
    *
    * @param material type of material
    * @return this instance
    */
   public Geometry setMaterial(Material material) {
      this.material = material;
      return this;
   }
}
