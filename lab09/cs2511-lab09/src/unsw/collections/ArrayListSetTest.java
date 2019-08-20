package unsw.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import unsw.fruit.Apple;
import unsw.fruit.Fruit;
import unsw.fruit.Orange;

class ArrayListSetTest {

    @Test
    void testBasics() {
        Set<String> set = new ArrayListSet<>();
        set.add("Hello");
        set.add("World");
        assertTrue(set.contains("Hello"));
        assertTrue(set.contains("World"));

        set.remove("Hello");
        assertFalse(set.contains("Hello"));
        assertTrue(set.contains("World"));
    }

    @Test
    void testSubsetOf() {
        Set<Fruit> fruit = new ArrayListSet<Fruit>();
        fruit.add(new Apple("Gala"));
        fruit.add(new Apple("Fuji"));
        fruit.add(new Orange("Navel"));

        Set<Apple> apples = new ArrayListSet<>();
        apples.add(new Apple("Gala"));
        apples.add(new Apple("Fuji"));

        assertTrue(apples.subsetOf(fruit));
        assertFalse(fruit.subsetOf(apples));

        fruit.remove(new Apple("Fuji"));

        assertFalse(apples.subsetOf(fruit));
    }

    @Test
    void testSubsetOfEdgeCases() {
        Set<Fruit> fruit = new ArrayListSet<Fruit>();
        fruit.add(new Apple("Gala"));
        fruit.add(new Apple("Fuji"));
        fruit.add(new Orange("Navel"));

        Set<Object> emptySet = new ArrayListSet<Object>();
        assertTrue(emptySet.subsetOf(fruit), "the empty set should be a subset for all sets");
        assertFalse(fruit.subsetOf(emptySet), "a non-empty set should never be the subset of the empty set");
    }


    @Test
    void testSubsetOfDifferentImplementation() {
        Set<Fruit> fruit = new LinkedListSet<Fruit>();
        fruit.add(new Apple("Gala"));
        fruit.add(new Apple("Fuji"));
        fruit.add(new Orange("Navel"));

        Set<Apple> apples = new ArrayListSet<>();
        apples.add(new Apple("Gala"));
        apples.add(new Apple("Fuji"));

        assertTrue(apples.subsetOf(fruit), "subsetOf() fails for Sets other than ArrayListSet");
    }

    @Test
    void testIterator() {
        List<String> strings = new ArrayList<>(
                Arrays.asList("Help", "I'm", "trapped", "in", "a", "JUnit", "test", "in", "COMP2511"));
        Set<String> set = new ArrayListSet<>();
        for (String s : strings)
            set.add(s);

        for (String s : set)
            strings.remove(s);

        // Because "in" occurs twice in the list, but only once in the set, there should still be one left in the list.
        assertNotEquals(strings.size(), 0, "iterator returns duplicates");
        assertEquals(strings.size(), 1, "iterator skipped element in set");
        assertEquals(strings.get(0), "in", "iterator skipped element in set");
    }

    @Test
    void testUnion() {
        Set<Fruit> fruit = new ArrayListSet<Fruit>();
        fruit.add(new Apple("Gala"));
        fruit.add(new Apple("Fuji"));
        fruit.add(new Orange("Navel"));

        Set<Orange> oranges = new ArrayListSet<>();
        oranges.add(new Orange("Mandarin"));
        oranges.add(new Orange("Tangerine"));
        oranges.add(new Orange("Clementine"));

        Set<Fruit> combined = fruit.union(oranges);
        assertEquals(combined.size(), 6, "non-overlapping union produces set of incorrect size");

        for (Fruit f : fruit) {
            assertTrue(combined.contains(f), "result of union does not contain element from left hand input");
        }

        for (Fruit f : oranges) {
            assertTrue(combined.contains(f), "result of union does not contain element from right hand input");
        }

        oranges.add(new Orange("Navel"));
        combined = fruit.union(oranges);
        assertEquals(combined.size(), 6, "overlapping union produces set of incorrect size");
    }

    @Test
    void testUnionDifferentImplementation() {
        Set<Fruit> fruit = new ArrayListSet<Fruit>();
        fruit.add(new Apple("Gala"));
        fruit.add(new Apple("Fuji"));
        fruit.add(new Orange("Navel"));

        Set<Orange> oranges = new LinkedListSet<>();
        oranges.add(new Orange("Mandarin"));
        oranges.add(new Orange("Tangerine"));
        oranges.add(new Orange("Clementine"));

        Set<Fruit> combined = fruit.union(oranges);
        assertEquals(combined.size(), 6, "union with Set other than ArrayListSet fails");
    }

    @Test
    void testIntersection() {
        Set<Fruit> fruit = new ArrayListSet<Fruit>();
        fruit.add(new Apple("Gala"));
        fruit.add(new Apple("Fuji"));
        fruit.add(new Orange("Navel"));

        Set<Apple> apples = new ArrayListSet<>();
        apples.add(new Apple("Gala"));
        apples.add(new Apple("Fuji"));
        apples.add(new Apple("Granny Smith"));

        Set<Fruit> combined = fruit.intersection(apples);
        assertEquals(combined.size(), 2, "overlapping intersection produces set of incorrect size");

        for (Fruit f : combined) {
            assertTrue(fruit.contains(f) && apples.contains(f), "result of intersection contain element not in both inputs");
        }

        apples.remove(new Apple("Gala"));
        apples.remove(new Apple("Fuji"));
        combined = fruit.intersection(apples);
        assertEquals(combined.size(), 0, "non-overlapping intersection should always produce the empty set");
    }

    @Test
    void testIntersectionDifferentImplementation() {
        Set<Fruit> fruit = new ArrayListSet<Fruit>();
        fruit.add(new Apple("Gala"));
        fruit.add(new Apple("Fuji"));
        fruit.add(new Orange("Navel"));

        Set<Apple> apples = new LinkedListSet<>();
        apples.add(new Apple("Gala"));
        apples.add(new Apple("Fuji"));
        apples.add(new Apple("Granny Smith"));

        Set<Fruit> combined = fruit.intersection(apples);
        assertEquals(combined.size(), 2, "overlapping intersection produces set of incorrect size");
    }

    @Test
    void testSimpleEquality() {
        List<String> strings = Arrays.asList("Equality", "is", "never", "as", "simple", "as", "you", "think", "it", "is");
        Set<String> set1 = new ArrayListSet<>();
        for (String s : strings)
            set1.add(s);
        assertTrue(set1.equals(set1), "sets should always be equal to themselves");

        Set<String> set2 = new ArrayListSet<>();
        for (String s : strings)
            set2.add(s);

        assertTrue(set1.equals(set2), "set equality doesn't work for the same objects added in the same order");

        Set<String> set3 = new ArrayListSet<>();
        List<String> strings2 = Arrays.asList("Equality", "is", "as", "simple", "as", "you", "never", "think", "it", "is");
        Collections.reverse(strings2);
        for (String s : strings2)
            set3.add(s);

        assertTrue(set1.equals(set3), "set equality doesn't work for the same objects added in a different order");

        Set<String> set4 = new ArrayListSet<>();
        List<String> strings3 = Arrays.asList("Equality", "is", "simple");
        for (String s : strings3)
            set4.add(s);

        assertFalse(set1.equals(set4), "different sized sets are never equal");
        assertFalse(set4.equals(set1), "different sized sets are never equal");
    }

    @Test
    void testGeneralisedEquality() {
        Set<Fruit> fruit = new ArrayListSet<Fruit>();
        fruit.add(new Apple("Gala"));
        fruit.add(new Apple("Fuji"));

        Set<Apple> apples = new ArrayListSet<>();
        apples.add(new Apple("Gala"));
        apples.add(new Apple("Fuji"));

        assertTrue(fruit.equals(apples), "sets containing elements of different types can still be equal");
        assertTrue(apples.equals(fruit), "sets containing elements of different types can still be equal");

        Set<Apple> apples2 = new LinkedListSet<>();
        apples2.add(new Apple("Gala"));
        apples2.add(new Apple("Fuji"));

        assertTrue(apples.equals(apples2), "sets with a different implementation but the same elements should still be equal");
        assertTrue(fruit.equals(apples2), "sets with a different implementation but the same elements should still be equal");

        Set<Apple> emptyApples = new ArrayListSet<>();
        Set<Orange> emptyOranges = new ArrayListSet<>();
        assertTrue(emptyApples.equals(emptyOranges), "two empty sets should always be equal");
    }

}
