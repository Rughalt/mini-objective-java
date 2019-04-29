package mini.java.basic.arrays;

import org.junit.Assert;

import static org.junit.Assert.*;

public class MathVectorTest {

    /**
     * Test sprawdzający sposób działania prymitywnego przyrównania (==) dla porównywania obiektów
     */
    @org.junit.Test
    public void primitiveEquals() {
        MathVector vector1 = new MathVector(1,2,3,4);
        int[] j = {1,2,3,4};
        MathVector vector2 = new MathVector(j);
        assertFalse(vector1 == vector2);
    }

    /**
     * Test sprawdzający sposób działania zaimplementowanej metody equals dla porównywania obiektów (obiekty w założeniu różne)
     */
    @org.junit.Test
    public void equalsFalse() {
        MathVector vector1 = new MathVector(1,2,3,4);
        MathVector vector2 = new MathVector(1,2,3);
        assertNotEquals(vector1, vector2);
    }

    /**
     * Test sprawdzający sposób działania zaimplementowanej metody equals dla porównywania obiektów (obiekty w założeniu identyczne)
     */
    @org.junit.Test
    public void equalsTrue() {
        MathVector vector1 = new MathVector(1,2,3,4);
        int[] j = {1,2,3,4};
        MathVector vector2 = new MathVector(j);
        assertEquals(vector1, vector2);
    }

    /**
     * Test sprawdzający sposób działania zaimplementowanej metody hashcode dla porównywania obiektów (obiekty w założeniu identyczne)
     */
    @org.junit.Test
    public void hashcodeEquals() {
        MathVector vector1 = new MathVector(1,2,3,4);
        MathVector vector2 = new MathVector(1,2,3,4);
        assertEquals(vector1.hashCode(), vector2.hashCode());
    }


    /**
     * Test sprawdzający sposób działanie metody multiplyByScalar
     */
    @org.junit.Test
    public void multiplicationTest() {
        MathVector vector1 = new MathVector(1,2,3,4);
        MathVector vector2 = vector1.multiplyByScalar(3);
        assertEquals(vector1, new MathVector(1,2,3,4));
        assertEquals(vector2, new MathVector(3,6,9,12));
    }


    /**
     * Test sprawdzający wynik porównania po rzutowaniu
     */
    @org.junit.Test
    public void castEqualityTest() {
        RandomMathVector vector1 = new RandomMathVector(5);
        MathVector castedVector = (MathVector) vector1;
        RandomMathVector castedVector2 = (RandomMathVector) castedVector;
        assertEquals(castedVector2,vector1);
    }

    /**
     * Test sprawdzający wynik porównania bez rzutowania dla klasy dziedziczącej i głównej
     */
    @org.junit.Test
    public void classEqualityTest() {
        RandomMathVector vector1 = new RandomMathVector(5);
        MathVector vector2 = new MathVector(1,2,3,4);
        assertNotEquals(vector1.getClass(),vector2.getClass());
    }

    /**
     * Test sprawdzający zachowanie metody instanceof dla klasy dziedziczącej
     */
    @org.junit.Test
    public void classEqualityInstanceOfTest() {
        RandomMathVector vector1 = new RandomMathVector(5);
        assertTrue(vector1 instanceof MathVector);
    }

    /**
     * Test sprawdzający długość nowego wektora typu RandomMathVector
     */
    @org.junit.Test
    public void randomVectorLengthTest() {
        MathVector vector1 = new RandomMathVector(5);
        assertEquals(vector1.getLength(),5);
    }

    /**
     * Testy sprawdzające zachowanie metod isRandomized i isRandomizedMethod dla obiektów klas MathVector i RandomMathVector
     */
    @org.junit.Test
    public void isRandomizedTestRandomMathVector() {
        MathVector vector1 = new RandomMathVector(5);
        assertTrue(vector1.isRandomized());
    }


    @org.junit.Test
    public void isRandomizedTestMathVector() {
        MathVector vector1 = new MathVector(5);
        assertFalse(vector1.isRandomized());
    }

    @org.junit.Test
    public void isRandomizedTestRandomMathVectorMethod() {
        MathVector vector1 = new RandomMathVector(5);
        assertTrue(vector1.isRandomizedMethod());
    }


    @org.junit.Test
    public void isRandomizedTestMathVectorMethod() {
        MathVector vector1 = new MathVector(5);
        assertFalse(vector1.isRandomizedMethod());
    }


    @org.junit.Test
    public void lombokTest() {
        MathVectorLombok mlLombok = new MathVectorLombok();
        ml.
    }
}