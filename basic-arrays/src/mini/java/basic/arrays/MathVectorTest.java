package mini.java.basic.arrays;

import org.junit.Assert;

import static org.junit.Assert.*;

public class MathVectorTest {

    @org.junit.Test
    public void primitiveEquals() {
        MathVector vector1 = new MathVector(1,2,3,4);
        MathVector vector2 = new MathVector(1,2,3,4);
        assertFalse(vector1 == vector2);
    }

    @org.junit.Test
    public void equalsFalse() {
        MathVector vector1 = new MathVector(1,2,3,4);
        MathVector vector2 = new MathVector(1,2,3);
        assertNotEquals(vector1, vector2);
    }

    @org.junit.Test
    public void equalsTrue() {
        MathVector vector1 = new MathVector(1,2,3,4);
        MathVector vector2 = new MathVector(1,2,3,4);
        assertEquals(vector1, vector2);
    }

    @org.junit.Test
    public void hashcodeEquals() {
        MathVector vector1 = new MathVector(1,2,3,4);
        MathVector vector2 = new MathVector(1,2,3,4);
        assertEquals(vector1.hashCode(), vector2.hashCode());
    }

    @org.junit.Test
    public void multiplicationTest() {
        MathVector vector1 = new MathVector(1,2,3,4);
        MathVector vector2 = vector1.multiplyByScalar(3);
        assertEquals(vector1, new MathVector(1,2,3,4));
        assertEquals(vector2, new MathVector(3,6,9,12));
    }
}