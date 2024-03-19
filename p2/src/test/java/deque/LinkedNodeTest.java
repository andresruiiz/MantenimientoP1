package deque;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedNodeTest {
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
}