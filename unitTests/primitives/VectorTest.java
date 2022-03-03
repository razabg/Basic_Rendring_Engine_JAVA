package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);


    @Test
    void testZeroVector(){
        assertThrows(IllegalArgumentException.class,()-> new Vector(0,0,0),"should have throw exception");

    }


    @Test
    void lengthSquared() {
    }

    @Test
    void length() {
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
    }

    @Test
    void normalize() {
    }
}