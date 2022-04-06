public interface SortedCollectionInterface<T extends Comparable<T>, K extends Comparable<T>> extends Iterable<T> {
    // Note that the provided iterators step through the data within this
    // collection in sorted order, as defined by their compareTo() method.

    //public boolean insert(T data, K Key) throws NullPointerException, IllegalArgumentException;

    //public int size();

    //public boolean isEmpty();

}
