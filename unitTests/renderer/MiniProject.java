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
    public void EXP() {

        Camera camera = new Camera.CameraBuilder(new Point(100, 200, 2000), new Vector(0, 0, -1)
                , new Vector(0, 1, 0))
                .setVPSize(600, 600).
                setVPDistance(1000).
                build();

//        camera.setDepthOfField(true)
//                .setAntiAliasing(true)
//                .setFocalDistance(180)
//                .setApertureSize(150)
//                .setNumOfRaysInAperture(81)
//                .setNumOfRaysInPixel(16);

        scene.geometries.add(
                new Plane(new Point(20, -250,0), new Vector(0, 1, 0)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(20)).setEmission(new Color(gray)),
//                new Sphere(new Point(0,-50,0),50).setEmission(new Color(red)).setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.0001)),
                new Cylinder(new Ray(new Point(150,-250,0),new Vector(0,1,0)),10,100).setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.0001)),
                new Cylinder(new Ray(new Point(-150,-250,0),new Vector(0,1,0)),10,100).setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.0001)),
                new Cylinder(new Ray(new Point(100,-190,0),new Vector(0,1,0)),10,100).setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.0001)),
                new Cylinder(new Ray(new Point(-200,-190,0),new Vector(0,1,0)),10,100).setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.0001)),
                new Cylinder(new Ray(new Point(-30,-100,0),new Vector(0,1,0)),350,10).setEmission(new Color(green)).setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(100).setKt(0.6)));


        scene.lights.add(new SpotLight(new Color(800,500,0),new Point(-150,250,50) , new Vector(0, -1, 0)).set_kL(0.001).set_kQ(0.0001));
        scene.lights.add(new SpotLight(new Color(800,500,0),new Point(-30,-100,0) , new Vector(0, -1, 0)).set_kL(0.001).set_kQ(0.0001));



        ImageWriter imageWriter = new ImageWriter("EXP", 600, 600);

        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)).renderImage(); //
        camera.writeToImage();//

    }

    @Test
        public void BonusImage() {
            Camera camera = new Camera.CameraBuilder(new Point(0, 0, 2000), new Vector(0, 0, -1)
                    , new Vector(0, 1, 0))
                    .setVPSize(600, 600).
                    setVPDistance(1000).
                    build();

            camera.setDepthOfField(true)
                    .setAntiAliasing(true)
                    .setFocalDistance(180)
                    .setApertureSize(150)
                    .setNumOfRaysInAperture(81)
                    .setNumOfRaysInPixel(16);


//        ImmutableList<Shape> shapes =
//                ImmutableList.of(
//                        PolygonSphere.withVertexNormals(glossy().setColor(GREEN).build(), 100).transform(translate(2, 0, -7)),
//                        PolygonSphere.withSurfaceNormals(glossy().setColor(BLUE).build(), 100).transform(translate(-2, 0, -7)),
////            new Sphere(glossy().setColor(RED).build()).transform(translate(2, 0, -7)),
//                        new Sphere(shiny().setColor(GREEN).build()).transform(translate(-4, 0, -10)),
//                        new Sphere(glossy().setColor(BLUE).build()).transform(translate(-2, 0, -15)),
//
//                        new Sphere(REFLECTIVE)
//                                .transform(translate(0, 0, -10)),
//                        new Sphere(shiny().setColor(new MutableColor(1.0f, 0.0f, 1.0f)).build())
//                                .transform(translate(0, 2, 1)),
//                        new Plane(
//                                new Vector(0, -1, 0),
//                                new Vector(0, 1.0, 0.0),
//                                glossy().setColor(new MutableColor(72, 136, 168)).build()));

        /**
         * y = positive up,negative down
         * x = positive right,negative left
         * z = our point of view,positive zoom out,negative zoom in
         */
        scene.setAmbientLight(new AmbientLight(new Color(BLACK), new Double3(0.3)));

            scene.geometries.add(
                    new Plane(new Point(0, -1,0), new Vector(0, 1, 0))
                            .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(20)).setEmission(new Color(blue)),


                    new Sphere(new Point(2, 100, 200), 100) //
                            .setEmission(new Color(red)) //
                            .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.0001))
//
//                    new Sphere(new Point(0, 2, 1), 30) //
//                            .setEmission(new Color(lightGray)) //
//                            .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.00001)),
//
//                    new Sphere(new Point(-4, 0, -10), 20) //
//                            .setEmission(new Color(lightGray)) //
//                            .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.00001)),
//                    new Sphere(new Point(-2, 0, 15), 20) //
//                            .setEmission(new Color(lightGray)) //
//                            .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.00001))





            );


            scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(-90, 80, 5), new Vector(0, 10, 5)) //
                    .set_kL(0.0001).set_kQ(0.0005));



            scene.lights.add(new PointLight(new Color(YELLOW), new Point(150, 90, 5)) //
                    .set_kL(0.0001).set_kQ(0.00005));

            scene.lights.add(new PointLight(new Color(YELLOW), new Point(-90, 80, 5)) //
                    .set_kL(0.000000000001).set_kQ(0.0000000000005));


            ImageWriter imageWriter = new ImageWriter("MP1", 600, 600);

            camera.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene)).renderImage(); //
            camera.writeToImage();//
        }
    }

