/**
 *
 */
package unsw.collections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of Set that uses an ArrayList to store the elements.
 *
 * @invariant All e in elements occur only once
 *
 * @author Robert Clifton-Everest
 *
 */
public class ArrayListSet<E> implements Set<E> {

    private ArrayList<E> elements;

    public ArrayListSet() {
        elements = new ArrayList<E>();
    }
    
//    public ArrayListSet(ArrayListSet<E> arrayListSet) {
//		elements = new ArrayList<E>(arrayListSet.getElements());
//	}
//
//    public ArrayList<E> getElements() {
//    	return this.elements;
//    }
    
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
        // DONE Implement me
    	for (E e : this) { // Set<E> implements Iterable<E>
    		if (!other.contains(e))
    			return false;
    	}
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        // DONE Implement me
        return elements.iterator();
    }

    @Override
    public Set<E> union(Set<? extends E> other) {
        // DONE Implement me
//    	Set<E> newSet = new ArrayListSet<E>(this); apparently this doesn't duplicate the arraylist
//    	for (E e : elements) {
//    		if (!other.contains(e)) 
//    			newSet.add(e);
//    	}
//        return newSet;
    	Set<E> result = new ArrayListSet<>();
        for (E e : this)
            result.add(e);
        for (E e : other)
            result.add(e);
        return result;
    }

    @Override
    public Set<E> intersection(Set<? extends E> other) {
        // DONE Implement me
    	Set<E> newSet = new ArrayListSet<E>();
    	for (E e : elements) {
    		if (other.contains(e)) 
    			newSet.add(e);
    	}
        return newSet;
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
        // DONE Implement me
    	if (this == other) return true;
    	if (other == null) return false;
    	if (!(other instanceof Set)) return false;
    	Set<?> set = (Set<?>) other;
    	if(set.size() != size()) return false;
    	return containsAll(set);
    }
    
    public boolean containsAll(Set<?> secondSet)
	{
		// return setElements.containsAll(secondSet.setElements); can't do this because setElements is not in Set<E>
		for (Object o : secondSet)
			if (!contains(o))
				return false;
		return true;
	}

}
