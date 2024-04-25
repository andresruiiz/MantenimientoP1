package org.mps.boundedqueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ArrayBoundedQueueTest {

    private ArrayBoundedQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new ArrayBoundedQueue<>(5);
    }

    @Nested
    @DisplayName("Put and Get Operations")
    class PutAndGetOperations {
        @Test
        @DisplayName("Test put and get operations")
        public void testPutAndGet() {
            // Arrange
            queue.put(1);
            queue.put(2);
            queue.put(3);

            // Act & Assert
            assertThat(queue.get()).isEqualTo(1);
            assertThat(queue.get()).isEqualTo(2);
            assertThat(queue.get()).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("Queue Full")
    class QueueFull {
        @Test
        @DisplayName("Test if queue is full")
        public void testIsFull() {
            // Arrange
            queue.put(1);
            queue.put(2);
            queue.put(3);
            queue.put(4);
            queue.put(5);

            // Act & Assert
            assertThat(queue.isFull()).isTrue();
        }
    }

    @Nested
    @DisplayName("Queue Empty")
    class QueueEmpty {
        @Test
        @DisplayName("Test if queue is empty")
        public void testIsEmpty() {
            // Arrange & Act
            queue.put(1);

            // Assert
            assertThat(queue.isEmpty()).isFalse();
        }
    }

    @Nested
    @DisplayName("Queue Size")
    class QueueSize {
        @Test
        @DisplayName("Test size of queue")
        public void testSize() {
            // Arrange
            queue.put(1);
            queue.put(2);
            queue.put(3);

            // Act & Assert
            assertThat(queue.size()).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("Queue Iterator")
    class QueueIterator {
        @Test
        @DisplayName("Test iterator of queue")
        public void testIterator() {
            // Arrange
            queue.put(1);
            queue.put(2);
            queue.put(3);

            StringBuilder sb = new StringBuilder();
            for (Integer item : queue) {
                sb.append(item);
            }

            // Act & Assert
            assertThat(sb.toString()).isEqualTo("123");
        }
    }

    @Nested
    @DisplayName("Get First Element")
    class GetFirstElement {
        @Test
        @DisplayName("Test getFirst of queue")
        public void testGetFirst() {
            // Arrange
            queue.put(1);
            queue.put(2);
            queue.put(3);

            // Act & Assert
            assertThat(queue.getFirst()).isEqualTo(0);
        }
    }
}