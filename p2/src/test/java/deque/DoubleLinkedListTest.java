/*
 * @author 1: Nicoló Melley
 * @author 2: Andrés Ruiz Sánchez
 */

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
        @DisplayName("When deleteFirst method is called with empty list, then exception is thrown")
        void whenDeleteFirstCalledWithEmptyList_thenExceptionThrown() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act and Assert
            assertThrows(DoubleLinkedQueueException.class, list::deleteFirst);
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
        @DisplayName("When deleteLast method is called with empty list, then exception is thrown")
        void whenDeleteLastCalledWithEmptyList_thenExceptionThrown() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act and Assert
            assertThrows(DoubleLinkedQueueException.class, list::deleteLast);
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
        @DisplayName("When first method is called with empty list, then exception is thrown")
        void whenFirstCalledWithEmptyList_thenExceptionThrown() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act and Assert
            assertThrows(DoubleLinkedQueueException.class, list::first);
        }

        @Test
        @DisplayName("When last method is called with empty list, then exception is thrown")
        void whenLastCalledWithEmptyList_thenExceptionThrown() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act and Assert
            assertThrows(DoubleLinkedQueueException.class, list::last);
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

    @Nested
    @DisplayName("Tests for get method")
    class WhenGetMethodCalled{

        @Test
        @DisplayName("When get method is called with index out of range, then exception is thrown")
        void whenGetCalledWithIndexOutOfRange_thenExceptionThrown() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            // Act and Assert
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
        }

        @Test
        @DisplayName("When get method is called with valid index, then it returns the correct element")
        void whenGetCalledWithValidIndex_thenReturnsCorrectElement() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            // Act
            int result = list.get(1);

            // Assert
            assertEquals(20, result);
        }
    }

    @Nested
    @DisplayName("Tests for Contains method")
    class whenContainsMethodCalled{

        @Test
        @DisplayName("When contains method is called with an element that is in the list, then it returns true")
        void whenContainsCalledWithElementInList_thenReturnsTrue() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            // Act
            boolean result = list.contains(20);

            // Assert
            assertTrue(result);
        }

        @Test
        @DisplayName("When contains method is called with an element that is not in the list, then it returns false")
        void whenContainsCalledWithElementNotInList_thenReturnsFalse() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            // Act
            boolean result = list.contains(40);

            // Assert
            assertFalse(result);
        }
    }

    @Nested
    @DisplayName("Tests for remove method")
    class WhenRemoveMethodCalled{
        @Test
        @DisplayName("When remove method is called with an element that is in the list, then it removes the element")
        void whenRemoveCalledWithElementInList_thenElementRemoved() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            // Act
            list.remove(20);

            // Assert
            assertEquals(10, list.first());
            assertEquals(30, list.last());
            assertEquals(2, list.size());
        }

        @Test
        @DisplayName("When remove method is called with an element that is not in the list, then the list remains the same")
        void whenRemoveCalledWithElementNotInList_thenListRemainsSame() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            // Act
            list.remove(40);

            // Assert
            assertEquals(10, list.first());
            assertEquals(30, list.last());
            assertEquals(3, list.size());
        }
    }

    @Nested
    @DisplayName("Tests for sort method")
    class WhenSortMethodCalled{
        @Test
        @DisplayName("When sort method is called with an empty list, then the list remains the same")
        void whenSortCalledWithEmptyList_thenListRemainsSame() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act
            list.sort(Integer::compareTo);

            // Assert
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("When sort method is called with a list that is already sorted, then the list remains the same")
        void whenSortCalledWithSortedList_thenListRemainsSame() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            // Act
            list.sort(Integer::compareTo);

            // Assert
            assertEquals(10, list.first());
            assertEquals(30, list.last());
            assertEquals(3, list.size());
        }

        @Test
        @DisplayName("When sort method is called with a list that is not sorted, then the list is sorted")
        void whenSortCalledWithUnsortedList_thenListSorted() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(30);
            list.append(10);
            list.append(20);

            // Act
            list.sort(Integer::compareTo);

            // Assert
            assertEquals(10, list.first());
            assertEquals(30, list.last());
            assertEquals(3, list.size());
        }
    }
}