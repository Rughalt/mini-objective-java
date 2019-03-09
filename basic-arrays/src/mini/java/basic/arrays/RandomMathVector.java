package mini.java.basic.arrays;

public class RandomMathVector extends MathVector {

    public RandomMathVector(int length) {
        this.i = new int[length];
        for (int idx = 0; idx < length; idx++)
            this.i[idx] = (int) (Math.random()*10.0);
        this.randomized = true;
    }

    @Override
    public boolean isRandomizedMethod() {
        return true;
    }
}
