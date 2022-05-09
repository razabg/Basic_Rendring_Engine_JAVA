package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;




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

    /**
     * Test method for {@link geometries.Plane#findIntersections(Ray)} (primitives.Ray)}.
     */
    @Test
    void findIntsersections() {


            Plane pl = new Plane(new Point(0, 0, 1), new Vector(1, 1, 1));

            // ============ Equivalence Partitions Tests ==============
            // TC01: Ray into plane
            assertEquals(List.of(new Point(1, 0, 0)),
                    pl.findIntersections(new Ray(new Point(0.5, 0, 0), new Vector(1, 0, 0))),
                    "Bad plane intersection");

            // TC02: Ray out of plane
            assertNull(pl.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))),
                    "Must not be plane intersection");

            // =============== Boundary Values Tests ==================
            // TC11: Ray parallel to plane
            assertNull(pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(0, 1, -1))),
                    "Must not be plane intersection");

            // TC12: Ray in plane
            assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, .5), new Vector(0, 1, -1))),
                    "Must not be plane intersection");

            // TC13: Orthogonal ray into plane
            assertEquals(List.of(new Point(1d / 3, 1d / 3, 1d / 3)),
                    pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(-1, -1, -1))),
                    "Bad plane intersection");

            // TC14: Orthogonal ray out of plane
            assertNull(pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))),
                    "Must not be plane intersection");

            // TC15: Orthogonal ray from plane
            assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, 0.5), new Vector(1, 1, 1))),
                    "Must not be plane intersection");

            // TC16: Ray from plane
            assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, 0.5), new Vector(1, 1, 0))),
                    "Must not be plane intersection");

            // TC17: Ray from plane's Q point
          assertThrows(IllegalArgumentException.class,()->pl.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 1, 0))),"Must not be plane intersection");



    }


}