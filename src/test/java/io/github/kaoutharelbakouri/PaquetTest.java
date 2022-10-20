package io.github.kaoutharelbakouri;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.function.Executable;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class PaquetTest {

    private static final Integer FIRST_ELEMENT = 12;
    private static final Integer SECOND_ELEMENT = 45;
    private Paquet<Integer> paquet;

    @BeforeEach
    public void init() {
        paquet = new Paquet<>();

        // Add some arbitrary values
        paquet.add(FIRST_ELEMENT);
        paquet.add(SECOND_ELEMENT);
    }

    @DisplayName("iterator method should return Iterator<T>")
    @Test
    public void should_return_iterator() {
        Iterator<Integer> iterator = paquet.iterator();
        assertNotNull(iterator);
    }

    @DisplayName("add method with one argument should add element to the last position of array")
    @Test
    public void should_add_with_one_arg_success() {
        paquet.add(4);
        paquet.add(18);
        int lastIndex = paquet.getSize() - 1;
        try {
            // check that last added value is on the last position (which its index equals 2) of the array
            assertEquals(paquet.get(lastIndex), 18);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("add method with two arguments should add element to the given index of array")
    @Test
    public void should_add_with_two_arg_success() {
        int index = 1;
        Integer valueToAdd = 845;

        // Add `valueToAdd` at `index`
        paquet.add(index, valueToAdd);
        try {
            assertEquals(paquet.get(index), valueToAdd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("add method with two arguments should throw exception if given index is out of bounds")
    @Test
    public void should_add_with_two_arg_fail() {
        int index = 34;
        Integer valueToAdd = 13;
        assertThrowsArrayIndexOuOfBounds(index, () -> paquet.add(index, valueToAdd));
    }

    @DisplayName("remove method should remove element if exists in the array")
    @Test
    public void should_remove_success() {
        try {
            // The first element of the array was added in the method `init()`
            assertEquals(paquet.get(0), FIRST_ELEMENT);

            // remove the first element
            paquet.remove(0);

            // The first element is removed successfully and the second one got its position
            assertNotEquals(FIRST_ELEMENT, paquet.get(0));
            assertEquals(SECOND_ELEMENT, paquet.get(0));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("remove method should throw exception if given index is out of bounds")
    @Test
    public void should_remove_fail() {
        int index = 34;
        assertThrowsArrayIndexOuOfBounds(index, () -> paquet.remove(index));
    }

    @DisplayName("size method should return length of array")
    @Test
    public void should_return_size() {
        // We added two values in `init()` method. So, the `expectedSize` would be equal to 2
        int expectedSize = 2;
        assertEquals(expectedSize, paquet.getSize());
    }

    @DisplayName("get method should return element of given index")
    @Test
    public void should_get_element_of_index() {
        try {
            assertEquals(FIRST_ELEMENT, paquet.get(0));
            assertEquals(SECOND_ELEMENT, paquet.get(1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("get method should throw exception if given index is out of bounds")
    @Test
    public void should_get_fail() {
        int index = 21;
        assertThrowsArrayIndexOuOfBounds(index, () -> paquet.get(index));
    }

    @DisplayName("toString method should return a string containing formatted data of the object")
    @Test
    public void should_return_data_of_object() {
        String expected = "Les elements de notre Collection sont : 12 45";
        assertEquals(expected, paquet.toString());
    }

    @DisplayName("isEmpty method should return a boolean true if array is empty")
    @Test
    public void should_return_true_if_empty() {
        assertFalse(paquet.isEmpty());

        paquet = new Paquet<>();
        assertTrue(paquet.isEmpty());
    }

    @DisplayName("set method should edit an element of a given index")
    @Test
    public void should_set_element_of_index() {
        Integer newFirstValue = 152;
        paquet.set(0, newFirstValue);
        try {
            assertEquals(newFirstValue, paquet.get(0));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("set method should throw exception if given index is out of bounds")
    @Test
    public void should_set_fail() {
        int index = 121;
        assertThrowsArrayIndexOuOfBounds(index, () -> paquet.set(index, 45));
    }

    private void assertThrowsArrayIndexOuOfBounds(int index, Executable executable) {
        String expectedMessage = index + " is out of Bounds";
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, executable);
        assertEquals(expectedMessage, exception.getMessage());
    }
}
