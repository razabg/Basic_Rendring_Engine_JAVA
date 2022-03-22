package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    /**
     * Test method for {@link Triangle#getNormal(Point)}
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Triangle Tri = new Triangle(
                new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));

        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), Tri.getNormal(new Point(0, 0, 1)), "Bad normal to Triangle");
    }

    @Test
    void findIntsersections()
    {
        // ============ Equivalence Partitions Tests ==============
        Triangle t = new Triangle(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );

        Point intersectionPoint;

        // TC01: Ray intersects the triangle and we should get 1 point
        Ray ray1 = new Ray(new Point(0, -1, 0), new Vector(1, 3, 1));
        List<Point> TC01result = t.findIntsersections(ray1);

        assertEquals(1, TC01result.size(), "Wrong number of intersection points");

        intersectionPoint = new Point(0.4, 0.2, 0.4);
        assertEquals(TC01result.get(0), intersectionPoint, "not the correct intersection point");

        // TC02: Ray doesn't intersects the triangle-against the edge and we're not supposed to get any points
        Ray ray2 = new Ray(new Point(0, -1, 0), new Vector(2, 2, 1));
        List<Point> TC02result = t.findIntsersections(ray2);

        assertNull(TC02result, "Wrong number of intersection points");

        // TC03: Ray doesn't intersects the triangle-against the vertices  and we're not supposed to get any points
        Ray ray3 = new Ray(new Point(0, -1, 0), new Vector(2, 1, 0));
        List<Point> TC03result = t.findIntsersections(ray3);

        assertNull(TC03result, "Wrong number of intersection points");

        // =============== Boundary Values Tests ==================

        // TC04: Ray  intersects the triangle on the edge  and we're not supposed to get any points
        Ray ray4 = new Ray(new Point(0, -1, 0), new Vector(1, 2, 1));
        List<Point> TC04result = t.findIntsersections(ray4);

        assertNull(TC04result, "Wrong number of intersection points");

        // TC05: Ray  intersects the triangle on the vertices  and we're not supposed to get any points
        Ray ray5 = new Ray(new Point(0, -1, 0), new Vector(1, 1, 0));
        List<Point> TC05result = t.findIntsersections(ray5);

        assertNull(TC05result, "Wrong number of intersection points");

        // TC06: Ray intersects on edge's continuation  and we're not supposed to get any points
        Ray ray6 = new Ray(new Point(0, -1, 0), new Vector(1, 0, 0));
        List<Point> TC06result = t.findIntsersections(ray6);

        assertNull(TC06result, "Wrong number of intersection points");

    }





}