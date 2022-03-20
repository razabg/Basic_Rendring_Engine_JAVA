package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;


// TODO implement this tests from eliezer's github and from the slides of the lectures

class PlaneTest {

    @Test
    void getQ0() {
    }

    /**
     * Test method for {@link Plane#getNormal(Point)}
     */
    @Test
    void getNormal() {
       Plane pl = new Plane(
                new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));

        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to plane");
    }

    @Test
    void testGetNormal() {
    }

    @Test
    void findIntsersections() {


    }

    void findIntsersections1() {


    }
}