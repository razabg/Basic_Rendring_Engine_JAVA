package renderer;

import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CameraIntegrationsTest {

    private void testPoint3DS(Geometry geo, Camera cam, int points) {

        List<Point> allPoints = null;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray = cam.constructRay(3, 3, j, i);
                List<Point> lst = geo.findIntersections(ray);

                if (lst != null) {
                    if (allPoints == null) {
                        allPoints = new LinkedList<>();
                    }
                    allPoints.addAll(lst);
                }
            }
        }

        // check if the amount of intersection points is correct
        if (points == 0) {
            assertNull(allPoints);
        } else {
            assertEquals(allPoints.size(), points, "wrong number of intersections");
        }
    }

    Camera cam1 = new Camera(
            Point.ZERO,                   // camera center
            new Vector(0, 0, -1),   // Vup
            new Vector(0, 1, 0))    // Vto
            .setVPDistance(1)
            .setVPSize(3, 3);

    Camera cam2 = new Camera(
            new Point(0, 0, 0.5), // camera center
            new Vector(0, 0, -1),   // Vup
            new Vector(0, 1, 0))    // Vto
            .setVPDistance(1)
            .setVPSize(3, 3);

    Camera cam3 = new Camera(
            Point.ZERO,                   // camera center
            new Vector(0, 0, -1),   // Vup
            new Vector(0, -1, 0))   // Vto
            .setVPDistance(1)
            .setVPSize(3, 3);

    /**
     * Test method for spheres
     */
    @Test
    void testSphereWithCam() {

        // =================================================================================
        // Sphere 1 test (slide 14)
        Sphere sphere1 = new Sphere(new Point(0, 0, -3), 1);
        testPoint3DS(sphere1, cam1, 2);
        // =================================================================================


        // =================================================================================
        // Sphere 2 test (slide 15)
        Sphere sphere2 = new Sphere(new Point(0, 0, -2.5), 2.5);
        testPoint3DS(sphere2, cam2, 18);
        // =================================================================================

        // =================================================================================
        // Sphere 3 test (slide 16)
        Sphere sphere3 = new Sphere(new Point(0, 0, -2), 2);
        testPoint3DS(sphere3, cam2, 10);
        // =================================================================================

        // =================================================================================
        // Sphere 4 test (slide 17)
        Sphere sphere4 = new Sphere(new Point(0, 0, -1), 4);
        testPoint3DS(sphere4, cam1, 9);
        // =================================================================================

        // =================================================================================
        // Sphere 5 test (slide 18)
        Sphere sphere5 = new Sphere(new Point(0, 0, 1), 0.5);
        testPoint3DS(sphere5, cam1, 0);
        // =================================================================================
    }

    /**
     * Test method for planes
     */
    @Test
    void testPlaneWithCam() {

        // =================================================================================
        // Plane 1 test (slide 19)
        Plane plane1 = new Plane(new Point(0, 0, -5), new Vector(0, 0, 1));
        testPoint3DS(plane1, cam3, 9);
        // =================================================================================

        // =================================================================================
        // Plane 2 test (slide 20)
        Plane plane2 = new Plane(new Point(0, 0, -5), new Vector(0, 1, 2));
        testPoint3DS(plane2, cam3, 9);
        // =================================================================================

        // =================================================================================
        // Plane 3 test (slide 21)
        Plane plane3 = new Plane(new Point(0, 0, -5), new Vector(0, 1, 1));
        testPoint3DS(plane3, cam3, 6);
        // =================================================================================
    }

    /**
     * Test method for triangles
     */
    @Test
    void testTriangleWithCam() {

        // =================================================================================
        // Triangle 1 test (slide 22)
        Triangle triangle1 = new Triangle(
                new Point(0, 1, -2),
                new Point(-1, -1, -2),
                new Point(1, -1, -2));
        testPoint3DS(triangle1, cam1, 1);
        // =================================================================================

        // =================================================================================
        // Triangle 2 test (slide 23)
        Triangle triangle2 = new Triangle(
                new Point(0, 20, -2),
                new Point(-1, -1, -2),
                new Point(1, -1, -2));
        testPoint3DS(triangle2, cam1, 2);
        // =================================================================================

    }





}
