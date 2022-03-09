package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for primitives.Point class
 * @author raz
 */
class PointTest {
    /**
     * Test method for {@link primitives.Point#add(Vector)} (primitives.Point)}
     */
    Point p1 = new Point(1, 2, 3);
    @Test
    void add() {


        // ============ Equivalence Partitions Tests ==============
       assertEquals(new Point(0,0,0), p1.add(new Vector(-1, -2, -3)),"\"ERROR: Point + Vector does not work correctly\"");


        // =============== Boundary Values Tests ==================
    }

    @Test
    void subtract() {

        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(1, 1, 1),new Point(2, 3, 4).subtract(p1),"\"ERROR: Point - Point does not work correctly\"");


        // =============== Boundary Values Tests ==================


    }
}