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
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setKr(0.15));



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

        //decor outter
        Geometry triangle5 = new Triangle((new Point(-56,9,71)),new Point(-50,0,66),
                new Point(-50,0,76))
                .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangle6 = new Triangle((new Point(-56,9,71)),new Point(-50,0,76),
                new Point(-62,0,76))
                .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangle7 = new Triangle((new Point(-56,9,71)),new Point(-62,0,76),
                new Point(-62,0,66))
                .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangle8 = new Triangle((new Point(-56,9,71)),new Point(-62,0,66),
                new Point(-50,0,66))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));


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

        //end of tree 1


        //********************//

        //tree 2

        Geometry triangleHeadOfSecondTree1 = new Triangle((new Point(90,69,20)),new Point(96,60,15),
                new Point(96,60,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleHeadOfSecondTree2 = new Triangle((new Point(90,69,20)),new Point(96,60,25),
                new Point(84,60,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleHeadOfSecondTree3 = new Triangle((new Point(90,69,20)),new Point(84,60,25),
                new Point(84,60,15))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleHeadOfSecondTree4 = new Triangle((new Point(90,69,20)),new Point(84,60,15),
                new Point(96,60,15))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);



        Geometry triangleSecondOfSecondTree1 =new Triangle((new Point(90,64,20)),new Point(96,55,15),
                new Point(96,55,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSecondOfSecondTree2 =new Triangle((new Point(90,64,20)),new Point(96,55,25),
                new Point(84,55,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSecondOfSecondTree3 =new Triangle((new Point(90,64,20)),new Point(84,55,25),
                new Point(84,55,15))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSecondOfSecondTree4 = new Triangle((new Point(90,64,20)),new Point(84,55,15),
                new Point(96,55,15))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);


        Geometry triangleThirdOfSecondTree1 = new Triangle((new Point(90,59,20)),new Point(96,50,15),
                new Point(96,50,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleThirdOfSecondTree2 = new Triangle((new Point(90,59,20)),new Point(96,50,25),
                new Point(84,50,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleThirdOfSecondTree3 = new Triangle((new Point(90,59,20)),new Point(84,50,25),
                new Point(84,50,15))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleThirdOfSecondTree4 = new Triangle((new Point(90,59,20)),new Point(84,50,15),
                new Point(96,50,15))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);

        Geometry triangleFourthOfSecondTree1 = new Triangle((new Point(90,54,20)),new Point(96,45,15),
                new Point(96,45,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFourthOfSecondTree2 =  new Triangle((new Point(90,54,20)),new Point(96,45,25),
                new Point(84,45,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFourthOfSecondTree3 = new Triangle((new Point(90,54,20)),new Point(84,45,25),
                new Point(84,45,15))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFourthOfSecondTree4 = new Triangle((new Point(90,54,20)),new Point(84,45,15),
                new Point(96,45,15))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);


        Geometry triangleFifthOfSecondTree1 =  new Triangle((new Point(90,49,20)),new Point(96,40,15),
                new Point(96,40,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFifthOfSecondTree2 = new Triangle((new Point(90,49,20)),new Point(96,40,25),
                new Point(84,40,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFifthOfSecondTree3 = new Triangle((new Point(90,49,20)),new Point(84,40,25),
                new Point(84,40,15))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFifthOfSecondTree4 = new Triangle((new Point(90,49,20)),new Point(84,40,15),
                new Point(96,40,15))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);



        Geometry triangleSixthOfSecondTree1 = new Triangle((new Point(90,44,20)),new Point(96,35,15),
                new Point(96,35,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSixthOfSecondTree2 = new Triangle((new Point(90,44,20)),new Point(96,35,25),
                new Point(84,35,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSixthOfSecondTree3 = new Triangle((new Point(90,44,20)),new Point(84,35,25),
                new Point(84,35,15))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSixthOfSecondTree4 = new Triangle((new Point(90,44,20)),new Point(84,35,15),
                new Point(96,35,15))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);



        Geometry StemSeocnd1 = new Polygon(new Point(90,0,20),new Point(90,0,22),
                new Point(88,35,22),new Point(88,35,20))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry StemSeocnd2 = new Polygon(new Point(90,0,22),new Point(88,0,22),
                new Point(88,35,22),new Point(90,35,22))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry StemSeocnd3 = new Polygon(new Point(88,0,20),new Point(88,0,22),
                new Point(88,35,22),new Point(88,35,20))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry StemSeocnd4 = new Polygon(new Point(90,0,20),new Point(88,0,20),
                new Point(88,35,20),new Point(90,35,20))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));


        //*********************************//


        //tree three
        Geometry triangleHeadOfThirdTree1 = new Triangle((new Point(-10,54,30)),new Point(-4,45,25),
                new Point(-4,45,35))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleHeadOfThirdTree2 = new Triangle((new Point(-10,54,30)),new Point(-4,45,35),
                new Point(-16,45,35))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleHeadOfThirdTree3 = new Triangle((new Point(-10,54,30)),new Point(-16,45,35),
                new Point(-16,45,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleHeadOfThirdTree4 = new Triangle((new Point(-10,54,30)),new Point(-16,45,25),
                new Point(-4,45,25))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);



        Geometry triangleSecondOfThirdTree1 =new Triangle((new Point(-10,49,30)),new Point(-4,40,25),
                new Point(-4,40,35))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSecondOfThirdTree2 =new Triangle((new Point(-10,49,30)),new Point(-4,40,35),
                new Point(-16,40,35))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSecondOfThirdTree3 =new Triangle((new Point(-10,49,30)),new Point(-16,40,35),
                new Point(-16,40,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSecondOfThirdTree4 = new Triangle((new Point(-10,49,30)),new Point(-16,40,25),
                new Point(-4,40,25))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);


        Geometry triangleThirdOfThirdTree1 = new Triangle((new Point(-10,44,30)),new Point(-4,35,25),
                new Point(-4,35,35))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleThirdOfThirdTree2 = new Triangle((new Point(-10,44,30)),new Point(-4,35,35),
                new Point(-16,35,35))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleThirdOfThirdTree3 = new Triangle((new Point(-10,44,30)),new Point(-16,35,35),
                new Point(-16,35,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleThirdOfThirdTree4 = new Triangle((new Point(-10,44,30)),new Point(-16,35,25),
                new Point(-4,35,25))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);


        Geometry triangleFourthOfThirdTree1 = new Triangle((new Point(-10,39,30)),new Point(-4,30,25),
                new Point(-4,30,35))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFourthOfThirdTree2 =  new Triangle((new Point(-10,39,30)),new Point(-4,30,35),
                new Point(-16,30,35))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFourthOfThirdTree3 = new Triangle((new Point(-10,39,30)),new Point(-16,30,35),
                new Point(-16,30,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFourthOfThirdTree4 = new Triangle((new Point(-10,39,30)),new Point(-16,30,25),
                new Point(-4,30,25))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);


        Geometry triangleFifthOfThirdTree1 =   new Triangle((new Point(-10,34,30)),new Point(-4,25,25),
                new Point(-4,25,35))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFifthOfThirdTree2 = new Triangle((new Point(-10,34,30)),new Point(-4,25,35),
                new Point(-16,25,35))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFifthOfThirdTree3 =new Triangle((new Point(-10,34,30)),new Point(-16,25,35),
                new Point(-16,25,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleFifthOfThirdTree4 = new Triangle((new Point(-10,34,30)),new Point(-16,25,25),
                new Point(-4,25,25))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);



        Geometry triangleSixthOfThirdTree1 = new Triangle((new Point(-10,29,30)),new Point(-4,20,25),
                new Point(-4,20,35))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSixthOfThirdTree2 = new Triangle((new Point(-10,29,30)),new Point(-4,20,35),
                new Point(-16,20,35))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSixthOfThirdTree3 = new Triangle((new Point(-10,29,30)),new Point(-16,20,35),
                new Point(-16,20,25))
                .setEmission(naturalGreen)
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0));
        Geometry triangleSixthOfThirdTree4 =  new Triangle((new Point(-10,29,30)),new Point(-16,20,25),
                new Point(-4,20,25))
                .setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setKt(0)).setEmission(naturalGreen);



        Geometry StemThird1 = new Polygon(new Point(-9,0,30),new Point(-9,0,32),
                new Point(-11,29,32),new Point(-11,29,30))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry StemThird2 = new Polygon(new Point(-9,0,32),new Point(-11,0,32),
                new Point(-11,29,32),new Point(-9,29,32))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry StemThird3 = new Polygon(new Point(-11,0,30),new Point(-11,0,32),
                new Point(-11,29,32),new Point(-11,29,30))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));
        Geometry StemThird4 = new Polygon(new Point(-9,0,30),new Point(-11,0,30),
                new Point(-11,29,30),new Point(-9,29,30))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));






       Geometry Moon = new Sphere(new Point(110, 135, -20), 15) //
                .setEmission(new Color(241,229,145)) //
                .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(50).setKt(0.2).setKr(0.2));



        scene1.setBackground(new Color(29,31,47));
        // door1,middle1,middle2,door2,door3,door4,door5,middle3,middle5,floor,handle1,handle2,handle3,handle4,door6,middle4
        scene1.geometries.add(floor,
                foot11,foot12,foot13,foot14,foot21,foot22,foot23,foot24,foot31,foot32,foot33,foot34,foot41,foot42,foot43,foot44,
                plateUp,plateDown,plateSide1,plateSide2,plateSide3,plateSide4
                ,

                triangleHeadOfTree1,triangleHeadOfTree2,triangleHeadOfTree3,triangleHeadOfTree4,
                triangleSecondOfTree1,triangleSecondOfTree2,triangleSecondOfTree3,triangleSecondOfTree4,
                triangleThirdOfTree1,triangleThirdOfTree2,triangleThirdOfTree3,triangleThirdOfTree4,
                triangleFourthOfTree1,triangleFourthOfTree2,triangleFourthOfTree3,triangleFourthOfTree4,
                triangleFifthOfTree1,triangleFifthOfTree2,triangleFifthOfTree3,triangleFifthOfTree4,
                triangleSixthOfTree1,triangleSixthOfTree2,triangleSixthOfTree3,triangleSixthOfTree4,
                Stem1,Stem2,Stem3,Stem4,


                triangleHeadOfSecondTree1,triangleHeadOfSecondTree2,triangleHeadOfSecondTree3,triangleHeadOfSecondTree4,
                triangleSecondOfSecondTree1,triangleSecondOfSecondTree2,triangleSecondOfSecondTree3,triangleSecondOfSecondTree4,
                triangleThirdOfSecondTree1,triangleThirdOfSecondTree2,triangleThirdOfSecondTree3,triangleThirdOfSecondTree4,
                triangleFourthOfSecondTree1,triangleFourthOfSecondTree2,triangleFourthOfSecondTree3,triangleFourthOfSecondTree4,
                triangleFifthOfSecondTree1,triangleFifthOfSecondTree2,triangleFifthOfSecondTree3,triangleFifthOfSecondTree4,
                triangleSixthOfSecondTree1,triangleSixthOfSecondTree2,triangleSixthOfSecondTree3,triangleSixthOfSecondTree4,
                StemSeocnd1,StemSeocnd2,StemSeocnd3,StemSeocnd4,

                triangleHeadOfThirdTree1,triangleHeadOfThirdTree2,triangleHeadOfThirdTree3,triangleHeadOfThirdTree4,
                triangleSecondOfThirdTree1,triangleSecondOfThirdTree2,triangleSecondOfThirdTree3,triangleSecondOfThirdTree4,
                triangleThirdOfThirdTree1,triangleThirdOfThirdTree2,triangleThirdOfThirdTree3,triangleThirdOfThirdTree4,
                triangleFourthOfThirdTree1,triangleFourthOfThirdTree2,triangleFourthOfThirdTree3,triangleFourthOfThirdTree4,
                triangleFifthOfThirdTree1,triangleFifthOfThirdTree2,triangleFifthOfThirdTree3,triangleFifthOfThirdTree4,
                triangleSixthOfThirdTree1,triangleSixthOfThirdTree2,triangleSixthOfThirdTree3,triangleSixthOfThirdTree4,
                StemThird1,StemThird2,StemThird3,StemThird4,







                triangle5,triangle6,triangle7,triangle8,Moon);




        scene1.lights.add(new SpotLight(new Color(java.awt.Color.orange), new Point(-56, 50, 71),new Vector(0,-1,0)));
        scene1.lights.add(new PointLight(
                (new Color(java.awt.Color.YELLOW)).scale(0.2), new Point(0, 65, 35)));
       // scene1.lights.add(new SpotLight(new Color(java.awt.Color.CYAN).scale(0.8), new Point(10, 55, 75),new Vector(1.5,-1,0)));

        //scene1.lights.add(new PointLight(new Color(java.awt.Color.orange),new Point(110, 135, -20)));
        //scene1.lights.add(new SpotLight(new Color(java.awt.Color.orange), new Point(110, 135, -20),new Vector(0,-1,0)));




        Camera camera3 = new Camera.CameraBuilder(new Point(0, 50, 160),
                new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200) //
                .setVPDistance(110)
                .setImageWriter( new ImageWriter("test1004", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene1))
                .build();

        camera3.setDepthOfField(false)
                .setAntiAliasing(true)
                .setFocalDistance(120)
                .setApertureSize(150)
                .setNumOfRaysInAperture(7)
                .setNumOfRaysInPixel(7);

        camera3.setMultithreading(4).setDebugPrint(1);



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