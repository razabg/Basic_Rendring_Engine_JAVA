package renderer;

import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

/**
 * Our final image. We created a nocturnal scene of a forest with a moon and stars
 */
public class FinalProjectTest {

   public List<LightSource> LightSourceInit = new LinkedList<LightSource>();
    public Scene scene1 = new Scene.SceneBuilder("Test scene").setLights(LightSourceInit).build();

    // todo 3 make report


    /**
     * y = positive up,negative down
     * x = positive right,negative left
     * z = our point of view,positive zoom out,negative zoom in
     */

    @Test
    public void miniProject() {



        Geometry floor= new Plane(new Point(0,0,0),new Vector(0,1,0)) // floor plane
                .setEmission( new Color(91,87,61))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setKr(0.15));


        //fire
        Geometry triangle1 = new Triangle((new Point(-56,9,71)),new Point(-50,0,66),
                new Point(-50,0,76))
                .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangle2 = new Triangle((new Point(-56,9,71)),new Point(-50,0,76),
                new Point(-62,0,76))
                .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangle3 = new Triangle((new Point(-56,9,71)),new Point(-62,0,76),
                new Point(-62,0,66))
                .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangle4 = new Triangle((new Point(-56,9,71)),new Point(-62,0,66),
                new Point(-50,0,66))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));




       Forest myForest = new Forest(); //class that create the forest


        scene1 = myForest.sceneOfForest; //copy the forest
        scene1.setBackground(new Color(29,31,47));
        scene1.geometries.add(floor,triangle1,triangle2,triangle3,triangle4);


        scene1.lights.add(new SpotLight(new Color(java.awt.Color.orange), new Point(-56, 50, 71),new Vector(0,-1,0)));
        scene1.lights.add(new PointLight(
                (new Color(java.awt.Color.YELLOW)).scale(0.2), new Point(0, 65, 35)));


           scene1.geometries.BuildTree();

        Camera camera3 = new Camera.CameraBuilder(new Point(0, 50, 160),
                new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200) //
                .setVPDistance(110)
                .setImageWriter( new ImageWriter("test1006", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene1).set_bb(true))
                .build();

       camera3.setDepthOfField(true)
                .setAntiAliasing(true)
                .setFocalDistance(120) // depth
                .setApertureSize(160) // depth
                .setNumOfRaysInAperture(60) // depth
                .setNumOfRaysInPixel(20); //aa

         camera3.setMultithreading(6).setDebugPrint(1);

        camera3.renderImage();
        camera3.writeToImage();




    }


}