package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    @Test
    void getHeight() {
    }

    /**
     * Test method for {@link Cylinder#getNormal(Point)} 
     */
    @Test
    void getNormal() {


            // ============ Equivalence Partitions Tests ==============
            Ray ray = new Ray(new Point(0, -2, 0), new Vector(0, 1, 0));
            Cylinder cl = new Cylinder(ray, 2, 3);

            // Test that result of getNormal is proper

            // - inside first base:
            assertEquals(new Vector(0, -1, 0),cl.getNormal(new Point(0, -2, 1)),"the normal of the first base is not proper");

            // - inside far base:
            assertEquals(new Vector(0, 1, 0),cl.getNormal(new Point(0, 1, 1)),"the normal of the second base is not proper" );

            // - round surface:
            assertEquals(new Vector(0, 0, 1),cl.getNormal(new Point(0, 0, 2)),"the normal of the round surface is not proper" );


            // =============== Boundary Values Tests ==================

            // corner first base, normal should  be inside the base
            assertEquals(new Vector(0, -1, 0),cl.getNormal(new Point(0, -2, 2)),"the normal of the first base is out of bounds" );

            // - corner far base - normal should be  inside the base
            assertEquals(new Vector(0, 1, 0),cl.getNormal(new Point(0, 1, 2)), "the normal of the first base is out of bounds");



    }
}