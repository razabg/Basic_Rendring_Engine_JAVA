/**
 * 
 */
package renderer;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {

	List<LightSource> LightSourceInit = new LinkedList<>();

	private Scene scene = new Scene.SceneBuilder("Test scene").setLights(LightSourceInit).build();

	/**
	 * Produce a picture of a sphere lighted by a spotlight
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(150, 150).setVPDistance(1000).build();

		scene.geometries.add( //
				new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),
				new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
//		scene.lights.add( //
//				new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
//						.set_kL(0.0004).set_kQ(0.0000006));
		scene.lights.add(
				new SpotLight(new Color(1000, 600, 0), new Point(-120, -110, 500), new Vector(-1, -1, -2)) //
						.set_kL(0.0004).set_kQ(0.0000006));
		camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //

		camera.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spotlight
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera.CameraBuilder(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000).build(); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( //
				new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setKt(0.5)),
				new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(1)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
				.set_kL(0.00001).set_kQ(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
				camera.writeToImage();
	}

	/**
	 * Produce a picture of two triangles lighted by a spotlight with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(1000).build();

		scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

		scene.geometries.add( //
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
				new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setKt(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.set_kL(4E-5).set_kQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
				camera.writeToImage();
	}


	/**
	 * Produce a picture of a sphere lighted by a spotlight
	 */
	@Test
	public void MyTest() {

		Scene scene = new Scene.SceneBuilder("Test scene")
				.setBackground(Color.BLACK)
				.setLights(LightSourceInit)
				.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)))
				.build();

		Camera camera = new Camera.CameraBuilder(
				new Point(10, -20, 60),
				new Vector(-20, -10, -90),
				new Vector(0, 1, -1d / 9d))
				.setVPSize(40, 40)
				.setVPDistance(50)
				.build();




		// Geometries
		//walls and mirror
		scene.geometries.add(

				new Plane(new Point(100, 0, 0), new Vector(1, 0, 0))
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(0).setKt(0).setKr(0))
						.setEmission((Color.GRAY.add(Color.RED)).reduce(20)),

				new Plane(new Point(-100, 0, 0), new Vector(0, 1, 0))
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(0).setKt(0).setKr(0))
						.setEmission(Color.GRAY.reduce(2)));


		scene.lights.add(
				new SpotLight(
						new Color(0, 0, 0),
						new Point(0, 40, 0),
						new Vector(0, -1, 0)
				).set_kL(0.0005)
						.set_kQ(0.000005));

		scene.lights.add(
				new PointLight(
						new Color(255, 250, 0),
						new Point(0, 40, 0))
						.set_kL(0.001).set_kQ(0.0002));

		scene.lights.add(
				new DirectionalLight(
						new Color(50, 50, 50),
						new Vector(1, -1, -1)));

		scene.geometries.add(
				new Sphere(new Point(-20, -40, -50), 5)
						.setEmission(Color.WHITE.reduce(6))
						.setMaterial(new Material().setkD(1).setkS(1).setnShininess(100).setKt(0).setKr(0.3)));

		scene.geometries.add(
				new Sphere(new Point(-10, -40, -50), 5)
						.setEmission(Color.GREEN.reduce(6))
						.setMaterial(new Material().setkD(1).setkS(1).setnShininess(100).setKt(0).setKr(0.3)));

		scene.geometries.add(
				new Sphere(new Point(-15, -30, -50), 5)
						.setEmission(Color.BLUE.reduce(6))
						.setMaterial(new Material().setkD(1).setkS(1).setnShininess(100).setKt(0).setKr(0.3)));

		scene.geometries.add(
				new Sphere(new Point(-30, -40, -50), 5)
						.setEmission(Color.WHITE.reduce(6))
						.setMaterial(new Material().setkD(1).setkS(1).setnShininess(100).setKt(0).setKr(0.3)));

		scene.geometries.add(
				new Sphere(new Point(-25, -30, -50), 5)
						.setEmission(Color.GREEN.reduce(6))
						.setMaterial(new Material().setkD(1).setkS(1).setnShininess(100).setKt(0).setKr(0.3)));

		scene.geometries.add(
				new Triangle(
						new Point(-12, -25, -50),
						new Point(-28, -25, -50),
						new Point(-20, -15, -50))
						.setEmission(Color.BLUE)
						.setMaterial(new Material().setkD(1).setkS(1).setnShininess(100).setKt(1).setKr(0.3))
		);

		scene.geometries.add(
				new Polygon(
						new Point(-120, -90, -149),
						new Point(-120, 150, -149),
						new Point(120, 150, -149),
						new Point(120, -90, -149)
				)
						.setEmission(new Color(40, 40, 40))
						.setMaterial(new Material().setkD(0).setkS(0).setnShininess(0).setKt(0).setKr(0.3)));

		scene.geometries.add(
				new Tube(new Ray(
						new Point(-35, -47, -50),
						new Vector(1, 0, 0)), 3)
						.setEmission(Color.RED.reduce(5))
						.setMaterial(new Material().setkD(0).setkS(0).setnShininess(0).setKt(0).setKr(0.3)
						));

		ImageWriter imageWriter1 = new ImageWriter("1MyTransparencyTest", 250, 250);
		camera.setImageWriter(imageWriter1) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
		camera.writeToImage();

	}
}
