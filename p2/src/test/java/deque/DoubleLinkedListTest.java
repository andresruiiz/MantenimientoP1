package deque;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListTest {
    @Nested
    @DisplayName("Tests for prepend method")
    class WhenPrependMethodCalled {
        @Test
        @DisplayName("When prepend method is called, then elements are added to the beginning of the list")
        void whenPrependCalled_thenElementsAddedToBeginning() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act
            list.prepend(10);
            list.prepend(20);
            list.prepend(30);

            // Assert
            assertEquals(30, list.first());
            assertEquals(10, list.last());
            assertEquals(3, list.size());
        }

        @Test
        @DisplayName("When prepend method is called with empty list, then element is added as the only element")
        void whenPrependCalledWithEmptyList_thenElementAddedAsOnlyElement() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act
            list.prepend(10);

            // Assert
            assertEquals(10, list.first());
            assertEquals(10, list.last());
            assertEquals(1, list.size());
        }
    }

    @Nested
    @DisplayName("Tests for append method")
    class WhenAppendMethodCalled {
        @Test
        @DisplayName("When append method is called, then elements are added to the end of the list")
        void whenAppendCalled_thenElementsAddedToEnd() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act
            list.append(10);
            list.append(20);
            list.append(30);

            // Assert
            assertEquals(10, list.first());
            assertEquals(30, list.last());
            assertEquals(3, list.size());
        }
    }

    @Nested
    @DisplayName("Tests for deleteFirst method")
    class WhenDeleteFirstMethodCalled {
        @Test
        @DisplayName("When deleteFirst method is called, then the first element is removed")
        void whenDeleteFirstCalled_thenFirstElementRemoved() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            // Act
            list.deleteFirst();

            // Assert
            assertEquals(20, list.first());
            assertEquals(30, list.last());
            assertEquals(2, list.size());
        }

        @Test
        @DisplayName("When deleteFirst method is called with empty list, then no change occurs")
        void whenDeleteFirstCalledWithEmptyList_thenNoChangeOccurs() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act
            list.deleteFirst();

            // Assert
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("When deleteFirst method is called with one element, then the list becomes empty")
        void whenDeleteFirstCalledWithOneElement_thenListBecomesEmpty() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);

            // Act
            list.deleteFirst();

            // Assert
            assertEquals(0, list.size());
        }
    }

    @Nested
    @DisplayName("Tests for deleteLast method")
    class WhenDeleteLastMethodCalled {
        @Test
        @DisplayName("When deleteLast method is called, then the last element is removed")
        void whenDeleteLastCalled_thenLastElementRemoved() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            // Act
            list.deleteLast();

            // Assert
            assertEquals(10, list.first());
            assertEquals(20, list.last());
            assertEquals(2, list.size());
        }

        @Test
        @DisplayName("When deleteLast method is called with empty list, then no change occurs")
        void whenDeleteLastCalledWithEmptyList_thenNoChangeOccurs() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act
            list.deleteLast();

            // Assert
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("When deleteLast method is called with one element, then the list becomes empty")
        void whenDeleteLastCalledWithOneElement_thenListBecomesEmpty() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);

            // Act
            list.deleteLast();

            // Assert
            assertEquals(0, list.size());
        }
    }

    @Nested
    @DisplayName("Tests for first and last methods")
    class WhenFirstAndLastMethodsCalled {
        @Test
        @DisplayName("When first method is called with empty list, then it returns null")
        void whenFirstCalledWithEmptyList_thenReturnsNull() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act
            Integer result = list.first();

            // Assert
            assertNull(result);
        }

        @Test
        @DisplayName("When last method is called with empty list, then it returns null")
        void whenLastCalledWithEmptyList_thenReturnsNull() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act
            Integer result = list.last();

            // Assert
            assertNull(result);
        }
    }

    @Nested
    @DisplayName("Tests for size method")
    class WhenSizeMethodCalled {
        @Test
        @DisplayName("When size method is called, then it returns the correct size of the list")
        void whenSizeMethodCalled_thenReturnsCorrectSize() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            // Act
            int result = list.size();

            // Assert
            assertEquals(3, result);
        }
    }
}