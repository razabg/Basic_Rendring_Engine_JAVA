package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);


    @Test
    void testZeroVector(){
        // ============ Equivalence Partitions Tests ==============


        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,()-> new Vector(0,0,0),"should have throw exception");

    }


    /**
     * test method for {@link Vector#lengthSquared()}
     */
    @Test
    void lengthSquared() {

        assertTrue(isZero(v1.lengthSquared() - 14),"\"ERROR: lengthSquared() wrong value\"");

    }

    /**
     * test method for {@link Vector#length()}
     */
    @Test
    void length() {
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5),"\"ERROR: length() wrong value\"");

    }

    /**
     * test method for {@link Vector#dotProduct(Vector)}
     */
    @Test
    void dotProduct() {

        assertEquals(-28d,v1.dotProduct(v2),0.0000001,"dotproduct not working");
    }

    @Test
    void crossProduct() {

        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(v1.length() * v3.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        assertThrows(
                IllegalArgumentException.class,
                () -> v1.crossProduct(v2),
                "crossProduct() for parallel vectors does not throw an exception"
        );

    }

    @Test
    void normalize() {
        // ============ Equivalence Partitions Tests ==============



        // =============== Boundary Values Tests ==================

        // test vector normalization vs vector length and cross-product
        Vector u=v1.normalize();
        assertTrue(isZero(u.length()-1),"ERROR: the normalized vector is not a unit vector");

        // test that the vectors are co-lined
        assertThrows(IllegalArgumentException.class,
                () -> v1.crossProduct(u),
                "ERROR: the normalized vector is not parallel to the original one");

        /**
         *
         * if (v.dotProduct(u) < 0)
         *             out.println("ERROR: the normalized vector is opposite to the original one");
         */




    }
}