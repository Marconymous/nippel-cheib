package bot.utils;

public final class Tuple<T, G> {
    final T x;
    final G y;

    public Tuple(T x, G y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public G getY() {
        return y;
    }
}
