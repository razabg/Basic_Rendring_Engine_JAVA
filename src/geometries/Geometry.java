package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

public abstract class Geometry extends Intersectable {

   /**
    * emission light
    */
   protected Color emission = Color.BLACK;


   /**
    * //todo documentation
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

   public Geometry setMaterial(Material material) {
      this.material = material;
      return this;
   }
}
