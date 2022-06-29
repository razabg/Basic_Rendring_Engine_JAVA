package renderer;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class FinalProjectTest {

   public List<LightSource> LightSourceInit = new LinkedList<LightSource>();
    public Scene scene1 = new Scene.SceneBuilder("Test scene").setLights(LightSourceInit).build();



    /**
     * y = positive up,negative down
     * x = positive right,negative left
     * z = our point of view,positive zoom out,negative zoom in
     */



    @Test
    public void miniProject() {



//        camera3.setDepthOfField(true)
//               .setAntiAliasing(true)
//                .setFocalDistance(180)
//               .setApertureSize(150)
//               .setNumOfRaysInAperture(81)
//                .setNumOfRaysInPixel(16);


        Geometry door1 = new Polygon(new Point(0,31,0),  new Point(0,61,0), //upper right door of the closet
                new Point(15,61,0),  new Point(15,31,0))
                .setEmission(new Color(java.awt.Color.WHITE).scale(0.7))
                .setMaterial(new Material().setkD(0.6).setkS(0.8).setnShininess(400));

        Geometry middle1 = new Polygon(new Point(0,0,0),  new Point(-1,0,0), // black lines divided the closet
                new Point(-1,61,0),  new Point(0,61,0))
                .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300));

        Geometry door2 = new Polygon(new Point(-1,31,0),  new Point(-1,61,0),
                new Point(-16,61,0),  new Point(-16,31,0))
                .setMaterial(new Material().setkD(0.1).setkS(0.7).setnShininess(300).setKr(1));

        Geometry middle2 = new Polygon(new Point(-16,0,0),  new Point(-17,0,0),
                new Point(-17,61,0),  new Point(-16,61,0))
                .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        /////
        Geometry middle3 = new Polygon(new Point(15,31,0),  new Point(15,29,0),
                new Point(-16,29,0),  new Point(-16,31,0))
                .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));

        Geometry middle5 = new Polygon(new Point(-33,31,0),  new Point(-33,30,0),
                new Point(-48,30,0),  new Point(-48,31,0))
                .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));

        Geometry middle4 = new Polygon(new Point(-32,0,0),  new Point(-33,0,0),
                new Point(-33,61,0),  new Point(-32,61,0))
                .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry door3 = new Polygon(new Point(0,30,0),  new Point(0,0,0),
                new Point(15,0,0),  new Point(15,30,0))
                .setEmission(new Color(java.awt.Color.WHITE).scale(0.7))
                .setMaterial(new Material().setkD(0.6).setkS(0.8).setnShininess(400));
        Geometry door4 = new Polygon(new Point(-1,30,0),  new Point(-1,0,0),
                new Point(-16,0,0),  new Point(-16,30,0))
                .setMaterial(new Material().setkD(0.1).setkS(0.7).setnShininess(300).setKr(1));

        Geometry door5 = new Polygon(new Point(-17,61,0),  new Point(-17,0,0), // door 5 and 6 are mirrors
                new Point(-32,0,0),  new Point(-32,61,0))
                .setMaterial(new Material().setkD(0.1).setkS(0.7).setnShininess(300).setKr(1));
        Geometry door6 = new Polygon(new Point(-33,61,0),  new Point(-33,0,0),
                new Point(-48,0,0),  new Point(-48,61,0))
                .setEmission(new Color(java.awt.Color.WHITE).scale(0.7))
                .setMaterial(new Material().setkD(0.6).setkS(0.8).setnShininess(400));




        Geometry floor= new Plane(new Point(0,0,0),new Vector(0,1,0)) // floor plane
                .setEmission( new Color(91,87,61))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setKr(0.4));



        Geometry handle1 = new Sphere(new Point(2,33,1),1) //Cabinet handles
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry handle2 = new Sphere(new Point(2,28,1),1)
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry handle3 = new Sphere(new Point(-14,33,1),1)
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry handle4 = new Sphere(new Point(-14,28,1),1)
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));


        /**
         * 4 parts to each leg of the table
         */
        //foot1
        Geometry foot11 = new Polygon(new Point(-10,0,60),new Point(-10,0,62),
                new Point(-10,20,62),new Point(-10,20,60))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry foot12 = new Polygon(new Point(-10,0,62),new Point(-12,0,62),
                new Point(-12,20,62),new Point(-10,20,62))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry foot13 = new Polygon(new Point(-12,0,60),new Point(-12,0,62),
                new Point(-12,20,62),new Point(-12,20,60))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry foot14 = new Polygon(new Point(-10,0,60),new Point(-12,0,60),
                new Point(-12,20,60),new Point(-10,20,60))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));

        //foot2

        Geometry foot21 = new Polygon(new Point(-10,0,80),new Point(-10,0,82),
                new Point(-10,20,82),new Point(-10,20,80))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry foot22 = new Polygon(new Point(-10,0,82),new Point(-12,0,82),
                new Point(-12,20,82),new Point(-10,20,82))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry foot23 = new Polygon(new Point(-12,0,80),new Point(-12,0,82),
                new Point(-12,20,82),new Point(-12,20,80))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry foot24 = new Polygon(new Point(-10,0,80),new Point(-12,0,80),
                new Point(-12,20,80),new Point(-10,20,80))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        //foot3
        Geometry foot31 = new Polygon(new Point(-40,0,60),new Point(-40,0,62),
                new Point(-40,20,62),new Point(-40,20,60))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry foot32 = new Polygon(new Point(-40,0,62),new Point(-42,0,62),
                new Point(-42,20,62),new Point(-40,20,62))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry foot33 = new Polygon(new Point(-42,0,60),new Point(-42,0,62),
                new Point(-42,20,62),new Point(-42,20,60))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry foot34 = new Polygon(new Point(-40,0,60),new Point(-42,0,60),
                new Point(-42,20,60),new Point(-40,20,60))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        //foot4
        Geometry foot41 = new Polygon(new Point(-40,0,80),new Point(-40,0,82),
                new Point(-40,20,82),new Point(-40,20,80))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry foot42 = new Polygon(new Point(-40,0,82),new Point(-42,0,82),
                new Point(-42,20,82),new Point(-40,20,82))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry foot43 = new Polygon(new Point(-42,0,80),new Point(-42,0,82),
                new Point(-42,20,82),new Point(-42,20,80))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry foot44 = new Polygon(new Point(-40,0,80),new Point(-42,0,80),
                new Point(-42,20,80),new Point(-40,20,80))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));







        Geometry plateDown = new Polygon(new Point(-7,20,58),new Point(-7,20,84), // the plates of the table
                new Point(-45,20,84),new Point(-45,20,58))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry plateUp = new Polygon(new Point(-7,21,58),new Point(-7,21,84),
                new Point(-45,21,84),new Point(-45,21,58))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry plateSide1 = new Polygon(new Point(-7,20,58),new Point(-7,21,58),
                new Point(-45,21,58),new Point(-45,20,58))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry plateSide2 = new Polygon(new Point(-7,20,58),new Point(-7,21,58),
                new Point(-7,21,84),new Point(-7,20,84))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry plateSide3 = new Polygon(new Point(-7,20,84),new Point(-7,21,84),
                new Point(-45,21,84),new Point(-45,20,84))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry plateSide4 = new Polygon(new Point(-45,20,58),new Point(-45,21,58),
                new Point(-45,21,84),new Point(-45,20,84))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
//
        //decor inner
        Geometry triangle1 = new Triangle((new Point(-56,25,71)),new Point(-53,21,69), //the two pyramids
                new Point(-53,21,73))
                .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100));
        Geometry triangle2 = new Triangle((new Point(-56,25,71)),new Point(-53,21,73),
                new Point(-59,21,73))
                .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100));
        Geometry triangle3 = new Triangle((new Point(-56,25,71)),new Point(-59,21,73),
                new Point(-59,21,69))
                .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100));
        Geometry triangle4 = new Triangle((new Point(-56,25,71)),new Point(-59,21,69),
                new Point(-53,21,69))
                .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100));
        //decor outter
        Geometry triangle5 = new Triangle((new Point(-56,30,71)),new Point(-50,21,66),
                new Point(-50,21,76))
                .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0.8));
        Geometry triangle6 = new Triangle((new Point(-56,30,71)),new Point(-50,21,76),
                new Point(-62,21,76))
                .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0.8));
        Geometry triangle7 = new Triangle((new Point(-56,30,71)),new Point(-62,21,76),
                new Point(-62,21,66))
                .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0.8));
        Geometry triangle8 = new Triangle((new Point(-56,30,71)),new Point(-62,21,66),
                new Point(-50,21,66))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0.8));

//wallSpheres
//        Geometry sphere1 = new Sphere(new Point(35, 30, 50), 4)
//                .setEmission(new Color(java.awt.Color.RED).scale(0.5))
//                .setMaterial(new Material().setkD(0.5).setkS(0.9).setnShininess(100));
//        Geometry sphere2 = new Sphere(new Point(35, 30, 75), 4)
//                .setEmission(new Color(java.awt.Color.ORANGE).scale(0.5))
//                .setMaterial(new Material().setkD(0.5).setkS(0.9).setnShininess(100));
//        Geometry sphere3 = new Sphere(new Point(35, 30, 100), 4)
//                .setEmission(new Color(java.awt.Color.GREEN).scale(0.3))
//                .setMaterial(new Material().setkD(0.5).setkS(0.9).setnShininess(100));


//        //scene1.Back(Color.BLUE.add(Color.GREEN.reduce(2)).reduce(5));
//        scene1.setAmbientLight(new AmbientLight(Color.WHITE.reduce(5),new Double3 (0.1)));
//
//        Color naturalGreen = new Color( 124,176,109);
////        Color.GREEN.add(Color.RED.reduce(10)).reduce(4)
//        //Color naturalGreen2 = naturalGreen.add(Color.GREEN.reduce(5));
//
//        //region Tree1 points
//        // create all relevant points
//        double xMoveTree = 0;
//        double yMoveTree = -5;
//        double zMoveTree = 0;
//
//        Point a = new Point(-56 + xMoveTree, 25 + yMoveTree, 71 + zMoveTree);
//       Point b = new Point(-53 + xMoveTree, 21 + yMoveTree, 69 + zMoveTree);
//        Point c = new Point(14 + xMoveTree, 1 + yMoveTree, 1 + zMoveTree);
//       Point d = new Point(1 + xMoveTree, 14 + yMoveTree, 1 + zMoveTree);
//        Point f = new Point(-12 + xMoveTree, 1 + yMoveTree, 10 + zMoveTree);
//        Point g = new Point(1 + xMoveTree, -12 + yMoveTree, 10 + zMoveTree);
//        Point h = new Point(12 + xMoveTree, 1 + yMoveTree, 10 + zMoveTree);
//        Point i = new Point(1 + xMoveTree, 12 + yMoveTree, 10 + zMoveTree);
//        Point j = new Point(1 + xMoveTree, 1 + yMoveTree, 30 + zMoveTree);
//       Point k = new Point(-10 + xMoveTree, 1 + yMoveTree, 20 + zMoveTree);
//        Point l = new Point(1 + xMoveTree, -10 + yMoveTree, 20 + zMoveTree);
//        Point m = new Point(10 + xMoveTree, 1 + yMoveTree, 20 + zMoveTree);
//        Point n = new Point(1 + xMoveTree, 10 + yMoveTree, 20 + zMoveTree);
//        Point o = new Point(1 + xMoveTree, 1 + yMoveTree, 40 + zMoveTree);
//       Point p = new Point(-53 + xMoveTree, 21 + yMoveTree, 73 + zMoveTree);
//        //endregion
//
//       //region Tree1 polygons
//      //  Polygon BottomPyramidBottom = new Polygon(a, b, c, d);
//        // tree's triangles
//        Triangle BottomPyramid1 = new Triangle(a, b, p);
//        Triangle BottomPyramid2 = new Triangle(b, c, p);
//        Triangle BottomPyramid3 = new Triangle(c, d, p);
//       Triangle BottomPyramid4 = new Triangle(a, d, p);
//
//        Triangle MiddlePyramid1 = new Triangle(f, g, j);
//        Triangle MiddlePyramid2 = new Triangle(g, h, j);
//        Triangle MiddlePyramid3 = new Triangle(h, i, j);
//       Triangle MiddlePyramid4 = new Triangle(i, f, j);
//
//        Triangle TopPyramid1 = new Triangle(k, l, o);
//        Triangle TopPyramid2 = new Triangle(l, m, o);
//       Triangle TopPyramid3 = new Triangle(m, n, o);
//       Triangle TopPyramid4 = new Triangle(n, k, o);
//        //endregion
//
//        //region color Tree1 polygons and add to scene's geometries
//       // add all of them to a list to apply settings (e.g. color) to all of them easily
//        List<Geometry> greenTriangles = Arrays.asList(
//               // BottomPyramidBottom,
//                BottomPyramid1,
//                BottomPyramid2,
//                BottomPyramid3,
//                BottomPyramid4,
//                MiddlePyramid1,
//                MiddlePyramid2,
//               MiddlePyramid3,
//               MiddlePyramid4,
//                TopPyramid1,
//                TopPyramid2,
//                TopPyramid3,
//                TopPyramid4
//       );
//
//        for (Geometry geo : greenTriangles) {
//            geo.setEmission(naturalGreen)
//                    .setMaterial(new Material()
//                            .setkD(0.01)
//                           .setkS(0.0001)
//                           .setnShininess(2)
//                            .setKr(0.01));
//        }
//      scene1.geometries.addAll(greenTriangles);
//      //endregion

        //region
        Color naturalGreen = new Color( 124,176,109);
        Geometry triangleHeadOfTree1 = new Triangle((new Point(19,50,71)),new Point(25,41,66),
                new Point(25,41,76))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleHeadOfTree2 = new Triangle((new Point(19,50,71)),new Point(25,41,76),
                new Point(13,41,76))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleHeadOfTree3 = new Triangle((new Point(19,50,71)),new Point(13,41,76),
                new Point(13,41,66))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleHeadOfTree4 = new Triangle((new Point(19,50,71)),new Point(13,41,66),
                new Point(25,41,66))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);



        Geometry triangleSecondOfTree1 = new Triangle((new Point(19,45,71)),new Point(25,36,66),
                new Point(25,36,76))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSecondOfTree2 = new Triangle((new Point(19,45,71)),new Point(25,36,76),
                new Point(13,36,76))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSecondOfTree3 = new Triangle((new Point(19,45,71)),new Point(13,36,76),
                new Point(13,36,66))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSecondOfTree4 = new Triangle((new Point(19,45,71)),new Point(13,36,66),
                new Point(25,36,66))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);


        Geometry triangleThirdOfTree1 = new Triangle((new Point(19,40,71)),new Point(25,31,66),
                new Point(25,31,76))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleThirdOfTree2 = new Triangle((new Point(19,40,71)),new Point(25,31,76),
                new Point(13,31,76))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleThirdOfTree3 = new Triangle((new Point(19,40,71)),new Point(13,31,76),
                new Point(13,31,66))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleThirdOfTree4 = new Triangle((new Point(19,40,71)),new Point(13,31,66),
                new Point(25,31,66))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);

        Geometry triangleFourthOfTree1 = new Triangle((new Point(19,35,71)),new Point(25,26,66),
                new Point(25,26,76))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFourthOfTree2 = new Triangle((new Point(19,35,71)),new Point(25,26,76),
                new Point(13,26,76))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFourthOfTree3 = new Triangle((new Point(19,35,71)),new Point(13,26,76),
                new Point(13,26,66))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFourthOfTree4 = new Triangle((new Point(19,35,71)),new Point(13,26,66),
                new Point(25,26,66))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);


        Geometry triangleFifthOfTree1 = new Triangle((new Point(19,30,71)),new Point(25,21,66),
                new Point(25,21,76))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFifthOfTree2 = new Triangle((new Point(19,30,71)),new Point(25,21,76),
                new Point(13,21,76))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFifthOfTree3 = new Triangle((new Point(19,30,71)),new Point(13,21,76),
                new Point(13,21,66))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFifthOfTree4 = new Triangle((new Point(19,30,71)),new Point(13,21,66),
                new Point(25,21,66))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);



        Geometry triangleSixthOfTree1 = new Triangle((new Point(19,25,71)),new Point(25,16,66),
                new Point(25,16,76))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSixthOfTree2 = new Triangle((new Point(19,25,71)),new Point(25,16,76),
                new Point(13,16,76))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSixthOfTree3 = new Triangle((new Point(19,25,71)),new Point(13,16,76),
                new Point(13,16,66))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSixthOfTree4 = new Triangle((new Point(19,25,71)),new Point(13,16,66),
                new Point(25,16,66))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);



        Geometry Stem1 = new Polygon(new Point(20,0,70),new Point(20,0,72),
                new Point(18,15,72),new Point(18,15,70))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry Stem2 = new Polygon(new Point(20,0,72),new Point(18,0,72),
                new Point(18,15,72),new Point(20,15,72))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry Stem3 = new Polygon(new Point(18,0,70),new Point(18,0,72),
                new Point(18,15,72),new Point(18,15,70))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry Stem4 = new Polygon(new Point(20,0,70),new Point(18,0,70),
                new Point(18,15,70),new Point(20,15,70))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));


       Geometry Moon = new Sphere(new Point(2, 110, 30), 10) //
                .setEmission(new Color(241,229,145)) //
                .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.2).setKr(0.2));



        scene1.setBackground(new Color(29,31,47));

        scene1.geometries.add( door1,middle1,middle2,door2,door3,door4,door5,middle3,middle5,floor,handle1,handle2,handle3,handle4
               ,foot11,foot12,foot13,foot14,foot21,foot22,foot23,foot24,foot31,foot32,foot33,foot34,foot41,foot42,foot43,foot44,
                plateUp,plateDown,plateSide1,plateSide2,plateSide3,plateSide4,middle4,door6 ,triangle1,triangle2,triangle3
                ,triangle4,triangleHeadOfTree1,triangleHeadOfTree2,triangleHeadOfTree3,triangleHeadOfTree4,
                triangleSecondOfTree1,triangleSecondOfTree2,triangleSecondOfTree3,triangleSecondOfTree4,
                triangleThirdOfTree1,triangleThirdOfTree2,triangleThirdOfTree3,triangleThirdOfTree4,
                triangleFourthOfTree1,triangleFourthOfTree2,triangleFourthOfTree3,triangleFourthOfTree4,
                triangleFifthOfTree1,triangleFifthOfTree2,triangleFifthOfTree3,triangleFifthOfTree4,
                triangleSixthOfTree1,triangleSixthOfTree2,triangleSixthOfTree3,triangleSixthOfTree4,
                Stem1,Stem2,Stem3,Stem4,
                triangle5,triangle6,triangle7,triangle8,Moon);




//        scene1.lights.add(new PointLight(
//                new Color(java.awt.Color.YELLOW)
//                        .add(new Color(java.awt.Color.YELLOW)).scale(0.2), new Point(0, 50, 40)));

        scene1.lights.add(new SpotLight(new Color(java.awt.Color.orange), new Point(-56, 50, 71),new Vector(0,-1,0)));
      //  scene1.lights.add(new SpotLight(new Color(java.awt.Color.CYAN).scale(0.8), new Point(10, 55, 75),new Vector(1.5,-1,0)));



        Camera camera3 = new Camera.CameraBuilder(new Point(0, 50, 160),
                new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200) //
                .setVPDistance(110)
                .setImageWriter( new ImageWriter("test1002", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene1))
                .build();
        camera3.renderImage();
        camera3.writeToImage();


//        scene1.lights.add( //
//                new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
//                        .set_kL(4E-4).set_kQ(2E-5));

//        .setCamera(camera3.setMin_MULTI_SAMPLING_SAMPLES(4)) //

//                        .setMIN_SHADOW_SAMPLES(100)
//                        .setMULTISAMPLING())
//                .setMultithreading(3).setDebugPrint();

    }



}