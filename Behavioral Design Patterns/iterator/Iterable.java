package iterator;

public interface Iterable<T> {
    public Iterator<T> createIterator();
}
