package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for primitives.Point class
 * @author raz
 */
class PointTest {

    Point p1 = new Point(1, 2, 3);

    /**
     * Test method for {@link primitives.Point#add(Vector)} (primitives.Point)}
     */
    @Test
    void add() {

        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Point(0,0,0), p1.add(new Vector(-1, -2, -3)),"\"ERROR: Point + Vector does not work correctly\"");

        // =============== Boundary Values Tests ==================
    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}} (primitives.Point)}
     */
    @Test
    void testDistance() {
        Point a = new Point(1,2,3);
        Point b = new Point(1,2,3);

        double distance = a.distance(b);

        assertEquals(distance, 0, "wrong distance");
        // =============== Boundary Values Tests ==================

    }


    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)} (primitives.Point)}
     */
    @Test
    void testDistanceSquared()
    {

        // ============ Equivalence Partitions Tests ==============
        Point a = new Point(1,2,3);
        Point b = new Point(1,2,3);

        double distance = a.distanceSquared(b);

        assertEquals(distance, 0, "wrong distance");
           // =============== Boundary Values Tests ==================
    }


    /**
     * Test method for {@link primitives.Point#subtract(Point)} (primitives.Point)}
     */
    @Test
    void subtract() {

        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(1, 1, 1),new Point(2, 3, 4).subtract(p1),"\"ERROR: Point - Point does not work correctly\"");


        // =============== Boundary Values Tests ==================

    }
}