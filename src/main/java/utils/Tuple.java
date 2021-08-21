package utils;

public class Tuple<T, G> {
    final T val1;
    final G val2;

    public Tuple(T val1, G val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    public T getVal1() {
        return val1;
    }

    public G getVal2() {
        return val2;
    }
}
