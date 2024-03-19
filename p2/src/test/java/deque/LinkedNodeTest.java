package deque;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedNodeTest {
    
    @Nested
    @DisplayName("Tests for getItem method")
    class GetItemTests {
        @Test
        @DisplayName("Test getItem method")
        void testGetItem() {
            // Arrange
            LinkedNode<Integer> node = new LinkedNode<>(10, null, null);

            // Act
            Integer result = node.getItem();

            // Assert
            assertEquals(10, result);
        }
    }
    
    @Nested
    @DisplayName("Tests for setItem method")
    class SetItemTests {
        @Test
        @DisplayName("Test setItem method")
        void testSetItem() {
            // Arrange
            LinkedNode<Integer> node = new LinkedNode<>(10, null, null);

            // Act
            node.setItem(20);

            // Assert
            assertEquals(20, node.getItem());
        }
    }
    
    @Nested
    @DisplayName("Tests for getPrevious method")
    class GetPreviousTests {
        @Test
        @DisplayName("Test getPrevious method")
        void testGetPrevious() {
            // Arrange
            LinkedNode<Integer> previousNode = new LinkedNode<>(10, null, null);
            LinkedNode<Integer> node = new LinkedNode<>(20, previousNode, null);

            // Act
            LinkedNode<Integer> result = node.getPrevious();

            // Assert
            assertEquals(previousNode, result);
        }
    }
    
    @Nested
    @DisplayName("Tests for setPrevious method")
    class SetPreviousTests {
        @Test
        @DisplayName("Test setPrevious method")
        void testSetPrevious() {
            // Arrange
            LinkedNode<Integer> previousNode = new LinkedNode<>(10, null, null);
            LinkedNode<Integer> node = new LinkedNode<>(20, null, null);

            // Act
            node.setPrevious(previousNode);

            // Assert
            assertEquals(previousNode, node.getPrevious());
        }
    }
    
    @Nested
    @DisplayName("Tests for getNext method")
    class GetNextTests {
        @Test
        @DisplayName("Test getNext method")
        void testGetNext() {
            // Arrange
            LinkedNode<Integer> nextNode = new LinkedNode<>(20, null, null);
            LinkedNode<Integer> node = new LinkedNode<>(10, null, nextNode);

            // Act
            LinkedNode<Integer> result = node.getNext();

            // Assert
            assertEquals(nextNode, result);
        }
    }
    
    @Nested
    @DisplayName("Tests for setNext method")
    class SetNextTests {
        @Test
        @DisplayName("Test setNext method")
        void testSetNext() {
            // Arrange
            LinkedNode<Integer> nextNode = new LinkedNode<>(20, null, null);
            LinkedNode<Integer> node = new LinkedNode<>(10, null, null);

            // Act
            node.setNext(nextNode);

            // Assert
            assertEquals(nextNode, node.getNext());
        }
    }
    
    @Nested
    @DisplayName("Tests for isFirstNode method")
    class IsFirstNodeTests {
        @Test
        @DisplayName("Test isFirstNode method")
        void testIsFirstNode() {
            // Arrange
            LinkedNode<Integer> node = new LinkedNode<>(10, null, null);

            // Act
            boolean result = node.isFirstNode();

            // Assert
            assertTrue(result);
        }
    }
    
    @Nested
    @DisplayName("Tests for isLastNode method")
    class IsLastNodeTests {
        @Test
        @DisplayName("Test isLastNode method")
        void testIsLastNode() {
            // Arrange
            LinkedNode<Integer> node = new LinkedNode<>(10, null, null);

            // Act
            boolean result = node.isLastNode();

            // Assert
            assertTrue(result);
        }
    }
    
    @Nested
    @DisplayName("Tests for isNotATerminalNode method")
    class IsNotATerminalNodeTests {
        @Test
        @DisplayName("Test isNotATerminalNode method")
        void testIsNotATerminalNode() {
            // Arrange
            LinkedNode<Integer> previousNode = new LinkedNode<>(10, null, null);
            LinkedNode<Integer> nextNode = new LinkedNode<>(20, null, null);
            LinkedNode<Integer> node = new LinkedNode<>(15, previousNode, nextNode);

            // Act
            boolean result = node.isNotATerminalNode();

            // Assert
            assertTrue(result);
        }
        
        @Test
        @DisplayName("Test isNotATerminalNode method with first node")
        void testIsNotATerminalNodeFirstNode() {
            // Arrange
            LinkedNode<Integer> nextNode = new LinkedNode<>(20, null, null);
            LinkedNode<Integer> node = new LinkedNode<>(15, null, nextNode);

            // Act
            boolean result = node.isNotATerminalNode();

            // Assert
            assertFalse(result);
        }
        
        @Test
        @DisplayName("Test isNotATerminalNode method with last node")
        void testIsNotATerminalNodeLastNode() {
            // Arrange
            LinkedNode<Integer> previousNode = new LinkedNode<>(10, null, null);
            LinkedNode<Integer> node = new LinkedNode<>(15, previousNode, null);

            // Act
            boolean result = node.isNotATerminalNode();

            // Assert
            assertFalse(result);
        }
        
        @Test
        @DisplayName("Test isNotATerminalNode method with only node")
        void testIsNotATerminalNodeOnlyNode() {
            // Arrange
            LinkedNode<Integer> node = new LinkedNode<>(15, null, null);

            // Act
            boolean result = node.isNotATerminalNode();

            // Assert
            assertFalse(result);
        }
    }
}