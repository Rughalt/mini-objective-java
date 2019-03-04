package mini.java.basic.arrays;

import java.util.Arrays;

public class MathVector {
    private int[] i;

    /**
     * Tworzy instancję obiektu MathVector z tablicy liczb.
     * @implNote int... (zmienna liczba argumentów) jest innym sposobem zapisu int[] przy przekazywaniu jako parametr funkcji
     * @param i Tablica liczb do utowrzenia obiektu
     */
    public MathVector(int... i) {
        this.i = i;
    }

    public int[] getI() {
        return i;
    }

    public void setI(int[] i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathVector that = (MathVector) o;
        return Arrays.equals(i, that.i);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(i);
    }

    public MathVector multiplyByScalar(int s) {
        int[] j = new int[this.i.length];
        for( int idx = 0; idx < i.length; idx++) {
            j[idx] = i[idx] * s;
        }
        return new MathVector(j);
    }
}
