package mini.java.basic.arrays;

import java.util.Arrays;

public class MathVector {
    /**
     * Tablica licz reprezentująca wektor
     */
    protected int[] i;

    /**
     * Zmienna reprezentująca informację czy wektor jest zrandomizowany
     */
    protected boolean randomized = false;

    /**
     * Tworzy instancję obiektu MathVector z tablicy liczb.
     * @implNote int... (zmienna liczba argumentów) jest innym sposobem zapisu int[] przy przekazywaniu jako parametr funkcji
     * @param i Tablica liczb do utowrzenia obiektu
     */
    public MathVector(int... i) {
        this.i = i;
    }


    /**
     * Prosty getter, zwraca tablicę i
     * @return tablica liczb i
     */
    public int[] getI() {
        return i;
    }

    /**
     * Prosty setter, ustawia wartość tablicy i (przypisuje zmiennej i przekazaną tablicę)
     * @param i
     */
    public void setI(int[] i) {
        this.i = i;
    }

    /**
     *
     * @param o
     * @return true jeżeli obiekty są identyczne, false jeżeli nie
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !o.getClass().equals(this.getClass())) return false;
        MathVector that = (MathVector) o;
        return Arrays.equals(getI(), that.getI());
    }


    /**
     * Wylicza hashcode dla obiektu
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(getI());
    }

    /**
     * Zwraca tekst będący reprezentacją obiektu
     * @return tekst reprezentujący obiekt
     */
    @Override
    public String toString() {
        return "MathVector{" +
                "i=" + Arrays.toString(i) +
                '}';
    }

    /**
     * Zwraca nowy wektor będący iloczynem skalarnym tego wektora oraz liczby s
     * @param s
     * @return wektor
     */
    public MathVector multiplyByScalar(int s) {
        int[] j = new int[this.i.length];
        for( int idx = 0; idx < this.i.length; idx++) {
            j[idx] = this.i[idx] * s;
        }
        return new MathVector(j);
    }

    /**
     * Zwraca długość tablicy i
     * @return długość tablicy i
     */
    public int getLength() {
        return i.length;
    }

    /**
     * Getter dla zmiennej randomized (mówiącej czy wektor jest losowy)
     * @return
     */
    public boolean isRandomized() {
        return randomized;
    }

    /**
     * Setter dla zmiennej randomized (mówiącej czy wektor jest losowy)
     */
    public void setRandomized(boolean randomized) {
        this.randomized = randomized;
    }

    /**
     * Metoda zwracającz informację czy wektor jest zrandomizowany
     * @return
     */
    public boolean isRandomizedMethod() {
        return false;
    }
}
