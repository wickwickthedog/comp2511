/**
 *
 */
package unsw.collections;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * An implementation of Set that uses an LinkedList to store the elements.
 *
 * This is used for marking an ArrayListSet
 *
 * @invariant All e in elements occur only once
 *
 * @author Robert Clifton-Everest
 *
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> elements;

    public LinkedListSet() {
        elements = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!elements.contains(e))
            elements.add(e);
    }

    @Override
    public void remove(E e) {
        elements.remove(e);
    }

    @Override
    public boolean contains(Object e) {
        return elements.contains(e);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean subsetOf(Set<?> other) {
        for (E e : this) {
            if (!other.contains(e))
                return false;
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }

    @Override
    public Set<E> union(Set<? extends E> other) {
        Set<E> result = new LinkedListSet<>();
        for (E e : this)
            result.add(e);
        for (E e : other)
            result.add(e);
        return result;
    }

    @Override
    public Set<E> intersection(Set<? extends E> other) {
        Set<E> result = new LinkedListSet<>();
        for (E e : this)
            if (other.contains(e))
                result.add(e);
        return result;
    }

    /**
     * For this method, it should be possible to compare all other possible sets
     * for equality with this set. For example, if an ArrayListSet<Fruit> and a
     * LinkedListSet<Fruit> both contain the same elements they are equal.
     * Similarly, if a Set<Apple> contains the same elements as a Set<Fruit>
     * they are also equal.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (other instanceof Set<?>) {
            Set<?> set = (Set<?>) other;
            if (size() != set.size()) return false;
            for (E e : this)
                if (!set.contains(e))
                    return false;
            return true;
        } else {
            return false;
        }
    }

}
