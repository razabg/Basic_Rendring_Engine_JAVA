package renderer;
import geometries.Geometry;
import geometries.*;
import lighting.AmbientLight;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;
import scene.Scene;
import java.awt.color.*;
import java.util.LinkedList;
import java.util.List;

import static java.awt.Color.*;


public class MiniProject {

    List<LightSource> temp = new LinkedList<>();

        Scene scene = new Scene.SceneBuilder("New Picture").setLights( temp ).build();

    @Test
        public void BonusImage() {
            Camera camera = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
                    .setVPSize(200, 200).setVPDistance(1000).build();

            camera.setDepthOfField(true).setFocalDistance(140).setApertureSize(100).setNumOfRaysInAperture(81);


            scene.setAmbientLight(new AmbientLight(new Color(BLACK), new Double3(0.3)));

            scene.geometries.add(
                    new Plane(new Point(0, -1,0), new Vector(0, 1, 0))
                            .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(20)).setEmission(new Color(blue)),

                    //the top head sphere of the  snow man
                    new Sphere(new Point(0, 0, -10), 20) //
                            .setEmission(new Color(lightGray)) //
                            .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.0001)),
                    //the middle part of the snow man
                    new Sphere(new Point(0, 2, 1), 30) //
                            .setEmission(new Color(lightGray)) //
                            .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.00001)),
                    //the bottom part of the snow man
                    new Sphere(new Point(-4, 0, -10), 20) //
                            .setEmission(new Color(lightGray)) //
                            .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.00001)),
                    new Sphere(new Point(-2, 0, 15), 20) //
                            .setEmission(new Color(lightGray)) //
                            .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.00001))


            );


            scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(-90, 80, 5), new Vector(0, 10, 5)) //
                    .set_kL(0.0001).set_kQ(0.0005));

            scene.lights.add(new PointLight(new Color(YELLOW), new Point(150, 90, 5)) //
                    .set_kL(0.0001).set_kQ(0.00005));

            scene.lights.add(new PointLight(new Color(YELLOW), new Point(-90, 80, 5)) //
                    .set_kL(0.000000000001).set_kQ(0.0000000000005));


            ImageWriter imageWriter = new ImageWriter("Bonus image", 600, 600);

            camera.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene)).renderImage(); //
            camera.writeToImage();; //
        }
    }

