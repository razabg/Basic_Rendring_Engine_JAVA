

package renderer;

import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

import static java.awt.Color.*;


/**
 * Test rendering a basic image
 *
 * @author Dan
 */

public class LightsTests {
	List<LightSource> temp = new LinkedList<>();
	private Scene scene1 = new Scene.SceneBuilder("Test scene").setLights(temp)
			.build();
	private Scene scene2 = new Scene.SceneBuilder("Test scene") //
			.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))).setLights(temp)
			.build();
	private Camera camera1 = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setVPSize(150, 150) //
			.setVPDistance(1000)
			.build();
	private Camera camera2 = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setVPSize(200, 200) //
			.setVPDistance(1000)
			.build();

	private Point[] p = { // The Triangles' vertices:
			new Point(-110, -110, -150), // the shared left-bottom
			new Point(95, 100, -150), // the shared right-top
			new Point(110, -110, -150), // the right-bottom
			new Point(-75, 78, 100) }; // the left-top
	private Point trPL = new Point(30, 10, -100); // Triangles test Position of Light
	private Point spPL = new Point(-50, -50, 25); // Sphere test Position of Light
	private Color trCL = new Color(800, 500, 250); // Triangles test Color of Light
	private Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
	private Vector trDL = new Vector(-2, -2, -2); // Triangles test Direction of Light
	private Material material = new Material().setkD(0.5).setkS(0.5).setnShininess(300);
	private Geometry triangle1 = new Triangle(p[0], p[1], p[2]).setMaterial(material);
	private Geometry triangle2 = new Triangle(p[0], p[1], p[3]).setMaterial(material);
	private Geometry sphere = new Sphere(new Point(0, 0, -50), 50d) //
			.setEmission(new Color(BLUE).reduce(2)) //
			.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300));
	private Geometry sphere_zero = new Sphere(new Point(0, 0, 0), 20d)
			.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)).setEmission(new Color(blue).reduce(2));
	//


	/**
	 * Produce a picture of a sphere lighted by a directional light
	 */

	@Test
	public void sphereDirectional() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new DirectionalLight(spCL, new Vector(1, 1, -0.5)));

		ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
		Camera camera_temp = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
				.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene1)) //
				.setVPSize(150, 150) //
				.setVPDistance(1000)
				.build();
				camera_temp.renderImage(); //
				camera_temp.writeToImage();


		camera1 = camera_temp;
	}


	/**
	 * Produce a picture of a sphere lighted by a point light
	 */

	@Test
	public void spherePoint() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new PointLight(spCL, spPL).set_kL(0.001).set_kQ(0.0002));

		ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);

		Camera camera_temp = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
				.setImageWriter(imageWriter)
				.setRayTracer(new RayTracerBasic(scene1))
				.setVPSize(150, 150) //
				.setVPDistance(1000)
						.build();

		camera_temp.renderImage();
		camera_temp.writeToImage();

		camera1= camera_temp;
	}


	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */

	@Test
	public void sphereSpot() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).set_kL(0.001).set_kQ(0.0001));

		ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);


		Camera camera_temp = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
				.setImageWriter(imageWriter)
				.setRayTracer(new RayTracerBasic(scene1))
				.setVPSize(150, 150) //
				.setVPDistance(1000)
				.build();


		camera_temp.renderImage();
		camera_temp.writeToImage();


		camera1 = camera_temp;
	}


	/**
	 * Produce a picture of a two triangles lighted by a directional light
	 */

	@Test
	public void trianglesDirectional() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new DirectionalLight(trCL, trDL));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);


		Camera camera_temp = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
				.setVPSize(200, 200) //
				.setVPDistance(1000)
				.setImageWriter(imageWriter)
				.setRayTracer(new RayTracerBasic(scene2)).build();

		camera_temp.renderImage();
		camera_temp.writeToImage();



		camera2 = camera_temp;
	}


	/**
	 * Produce a picture of two triangles lighted by a point light
	 */

	@Test
	public void trianglesPoint() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new PointLight(trCL, trPL).set_kL(0.001).set_kQ(0.0002));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);

		Camera camera_temp = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
				.setVPSize(200, 200) //
				.setVPDistance(1000)
				.setImageWriter(imageWriter)
				.setRayTracer(new RayTracerBasic(scene2))
				.build();

		camera_temp.renderImage();
		camera_temp.writeToImage();

		camera2 = camera_temp;
	}


	/**
	 * Produce a picture of two triangles lighted by a spotlight
	 */

	@Test
	public void trianglesSpot() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new SpotLight(trCL, trPL, trDL).set_kL(0.001).set_kQ(0.0001));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);

	 Camera camera_temp = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200) //
				.setVPDistance(1000)
				.setImageWriter(imageWriter)
				.setRayTracer(new RayTracerBasic(scene2)).build();
		camera_temp.renderImage();
		camera_temp.writeToImage();


		camera2 = camera_temp;
	}

	/**
	 * Produce a picture of a sphere lighted by a narrow spot light
	 */

	@Test
	public void sphereSpotSharp() {
		scene1.geometries.add(sphere);
		scene1.lights
				.add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setNarrowBeam(10).set_kL(0.001).set_kQ(0.00004));

		ImageWriter imageWriter = new ImageWriter("lightSphereSpotSharp", 500, 500);


	 Camera camera_temp = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(150, 150) //
				.setVPDistance(1000)
				.setImageWriter(imageWriter)
				.setRayTracer(new RayTracerBasic(scene1))
			 	.build();
	 camera_temp.renderImage();
	 camera_temp.writeToImage();



		camera1 = camera_temp;
	}


	/**
	 * Produce a picture of a two triangles lighted by a narrow spot light
	 */

	@Test
	public void trianglesSpotSharp() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new SpotLight(trCL, trPL, trDL).setNarrowBeam(10).set_kL(0.001).set_kQ(0.00004));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesSpotSharp", 500, 500);

		 Camera camera_temp = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200) //
				.setVPDistance(1000)
				 .setImageWriter(imageWriter)
				 .setRayTracer(new RayTracerBasic(scene2))
				 .build();

		 camera_temp.renderImage();
		 camera_temp.writeToImage();

		camera2 = camera_temp;
	}

	@Test
	public void triangleMultiLightSource()  {


			scene2.geometries.add(triangle1.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)),
					triangle2.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)));
			scene2.geometries.add(sphere_zero);
			//scene2.lights.add(new DirectionalLight(new Color(300, 150, 150), new Vector(0, -0.05, -1)));
			//scene2.lights.add(new PointLight(new Color(100, 250, 250), new Point(50, -10, -130)) //
				//	.set_kL(0.0005).set_kQ(0.0005));
			scene2.lights.add(new SpotLight(new Color(white), new Point(-100, 80, 0), new Vector(0, 0, -1)) //
					.set_kL(0.0001).set_kQ(0.000005));

			ImageWriter imageWriter = new ImageWriter("allLightTriangles", 500, 500);
			//
		Camera camera_temp = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200) //
				.setVPDistance(1000)
				.setImageWriter(imageWriter)
				.setRayTracer(new RayTracerBasic(scene2))

				.build();

		camera_temp.renderImage();
		camera_temp.writeToImage();
		}


	/**
	 * Produce a picture of a sphere lighted by all types of light
	 */
	@Test
	public void sphereAllLights() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new DirectionalLight(new Color(0, 500, 500), new Vector(0, 0, -1)));
		scene1.lights.add(new PointLight(new Color(0, 300, 500), new Point(-50, -50, 0))//
				.set_kL(0.00000001).set_kQ(0.0000001));
		scene1.lights.add(new SpotLight(new Color(500, 300, 0), new Point(-60, 80, -60), new Vector(-1, -1, 5)) //
				.set_kL(0.00001).set_kQ(0.00000001));


		ImageWriter imageWriter = new ImageWriter("allLightSphere", 500, 500);

		Camera camera_temp = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(150, 150) //
				.setVPDistance(1000)
				.setImageWriter(imageWriter)
				.setRayTracer(new RayTracerBasic(scene1))
				.build();

		camera_temp.renderImage();
		camera_temp.writeToImage();


	}



	}






