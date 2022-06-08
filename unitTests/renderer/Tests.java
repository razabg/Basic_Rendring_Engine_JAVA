package renderer;

import geometries.Intersectable;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.LightSource;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

import static java.awt.Color.BLUE;

public class Tests {

    List<LightSource> LightSourceInit = new LinkedList<>();
    private Intersectable sphere = new Sphere(new Point(0, 0, -200), 60d) //
            .setEmission(new Color(BLUE)) //
            .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30));
    private Material trMaterial = new Material().setkD(0.5).setkS(0.5).setnShininess(30);

    private Scene scene = new Scene.SceneBuilder("Test scene").setLights(LightSourceInit).build();
    private Camera camera = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            /**
             * y = positive up,negative down
             * x = positive right,negative left
             * z = our point of view,positive zoom out,negative zoom in
             */
            .setVPSize(200, 200).setVPDistance(1000) //
            .setRayTracer(new RayTracerBasic(scene))
            .build();

    @Test
    public void trianglesSphere_2() {



        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                new Sphere(new Point(0, 0, -11), 30d) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
                        .set_kL(4E-4).set_kQ(2E-5));

        camera.setImageWriter(new ImageWriter("shadowTrianglesSphere_check", 600, 600)) //
                .renderImage();
        camera.writeToImage();

    }
}
