package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    /**
     * Test method for {@link Tube#getNormal(Point)} 
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //  There is a simple single test here
        Tube tube = new Tube(new Ray(new Point(0, 0, 1), new Vector(0, 1, 0)), 1.0);
        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(0, 0.5, 2)), "Bad normal to tube");

    }
}