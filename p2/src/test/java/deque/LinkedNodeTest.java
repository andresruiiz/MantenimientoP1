/*
 * @author 1: Nicoló Melley
 * @author 2: Andrés Ruiz Sánchez
 */

package deque;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedNodeTest {
    
    @Nested
    @DisplayName("Tests for getItem method")
    class GetItemTests {
        @Test
        @DisplayName("When getting item, then return item")
        void whenGettingItem_thenReturnItem() {
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
        @DisplayName("When setting item, then item is set")
        void whenSettingItem_thenItemIsSet() {
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
        @DisplayName("When getting previous node, then return previous node")
        void whenGettingPrevious_thenReturnPreviousNode() {
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
        @DisplayName("When setting previous node, then previous node is set")
        void whenSettingPrevious_thenPreviousNodeIsSet() {
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
        @DisplayName("When getting next node, then return next node")
        void whenGettingNext_thenReturnNextNode() {
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
        @DisplayName("When setting next node, then next node is set")
        void whenSettingNext_thenNextNodeIsSet() {
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
        @DisplayName("When checking if first node, then return true")
        void whenCheckingIfFirstNode_thenReturnTrue() {
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
        @DisplayName("When checking if last node, then return true")
        void whenCheckingIfLastNode_thenReturnTrue() {
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
        @DisplayName("When checking if not a terminal node, then return true")
        void whenCheckingIfNotTerminalNode_thenReturnTrue() {
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
        @DisplayName("When checking if not a terminal node with first node, then return false")
        void whenCheckingIfNotTerminalNodeWithFirstNode_thenReturnFalse() {
            // Arrange
            LinkedNode<Integer> nextNode = new LinkedNode<>(20, null, null);
            LinkedNode<Integer> node = new LinkedNode<>(15, null, nextNode);

            // Act
            boolean result = node.isNotATerminalNode();

            // Assert
            assertFalse(result);
        }
        
        @Test
        @DisplayName("When checking if not a terminal node with last node, then return false")
        void whenCheckingIfNotTerminalNodeWithLastNode_thenReturnFalse() {
            // Arrange
            LinkedNode<Integer> previousNode = new LinkedNode<>(10, null, null);
            LinkedNode<Integer> node = new LinkedNode<>(15, previousNode, null);

            // Act
            boolean result = node.isNotATerminalNode();

            // Assert
            assertFalse(result);
        }
        
        @Test
        @DisplayName("When checking if not a terminal node with only node, then return false")
        void whenCheckingIfNotTerminalNodeWithOnlyNode_thenReturnFalse() {
            // Arrange
            LinkedNode<Integer> node = new LinkedNode<>(15, null, null);

            // Act
            boolean result = node.isNotATerminalNode();

            // Assert
            assertFalse(result);
        }
    }
}