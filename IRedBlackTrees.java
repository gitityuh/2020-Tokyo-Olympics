import java.util.ArrayList;

@SuppressWarnings("unchecked")
public interface IRedBlackTrees<T extends Country, K extends Comparable<K>> extends Iterable<T> {
  // Note that the provided iterators step through the data within this
  // collection in sorted order, as defined by their compareTo() method.
  public boolean insert(T data, K key) throws NullPointerException, IllegalArgumentException;
  //public void rotate(T child, K childKey, T parent, K parentKey) throws IllegalArgumentException;

  //public void remove();

  int size();

  boolean isEmpty();

  boolean contains(T data, K key);
}
