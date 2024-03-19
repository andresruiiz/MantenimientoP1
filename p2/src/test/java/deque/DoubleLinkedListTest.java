package deque;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListTest {
    @Nested
    @DisplayName("Tests for prepend method")
    class PrependTests {
        @Test
        @DisplayName("Test prepend method")
        void testPrepend() {
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
    }

    @Nested
    @DisplayName("Tests for append method")
    class AppendTests {
        @Test
        @DisplayName("Test append method")
        void testAppend() {
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
    class DeleteFirstTests {
        @Test
        @DisplayName("Test deleteFirst method")
        void testDeleteFirst() {
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
        @DisplayName("Test deleteFirst method with empty list")
        void testDeleteFirstWithEmptyList() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act
            list.deleteFirst();

            // Assert
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("Test deleteFirst method with one element")
        void testDeleteFirstWithOneElement() {
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
    class DeleteLastTests {
        @Test
        @DisplayName("Test deleteLast method")
        void testDeleteLast() {
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
        @DisplayName("Test deleteLast method with empty list")
        void testDeleteLastWithEmptyList() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act
            list.deleteLast();

            // Assert
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("Test deleteLast method with one element")
        void testDeleteLastWithOneElement() {
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
    class FirstAndLastTests {
        @Test
        @DisplayName("Test first method with empty list")
        void testFirstWithEmptyList() {
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act
            Integer result = list.first();

            // Assert
            assertNull(result);
        }

        @Test
        @DisplayName("Test last method with empty list")
        void testLastWithEmptyList() {
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
    class SizeTests {
        @Test
        @DisplayName("Test size method")
        void testSize() {
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