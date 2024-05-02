/*
 * @author 1: Nicoló Melley
 * @author 2: Andrés Ruiz Sánchez
 */

package org.mps.boundedqueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBoundedQueueTest {

    private ArrayBoundedQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new ArrayBoundedQueue<>(5);
    }

    @Nested
    @DisplayName("Test for Constructor")
    class ConstructorTest{
        @Test
        @DisplayName("Test for Constructor with positive capacity creates a new ArrayBoundedQueue object")
        public void testConstructorWithPositiveCapacityCreatesQueue(){
            // Arrange
            int capacity = 5;
            // Act
            queue = new ArrayBoundedQueue<>(capacity);
            // Assert
            assertThat(queue).isNotNull()
                             .isInstanceOf(ArrayBoundedQueue.class);
        }

        @Test
        @DisplayName("Test for Constructor with negative capacity throws IllegalArgumentException")
        public void testConstructorWithNegativeCapacityThrowsException(){
            // Arrange
            int capacity = -5;
            // Act 
            Throwable thrown = catchThrowable(() -> new ArrayBoundedQueue<>(capacity));
            //Assert
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                              .hasMessage("ArrayBoundedException: capacity must be positive");
        }
    }

    @Nested
    @DisplayName("Test for put method")
    class PutTest{
        @Test
        @DisplayName("Test for put method with null element throws IllegalArgumentException")
        public void testPutWithNullElementThrowsException(){
            // Arrange
            Integer element = null;
            // Act
            Throwable thrown = catchThrowable(() -> queue.put(element));
            // Assert
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                              .hasMessage("put: element cannot be null");
        }

        @Test
        @DisplayName("Test for put method with full queue throws FullBoundedQueueException")
        public void testPutWithFullQueueThrowsException(){
            // Arrange
            for(int i = 0; i < 5; i++){
                queue.put(i);
            }
            // Act
            Throwable thrown = catchThrowable(() -> queue.put(5));
            // Assert
            assertThat(thrown).isInstanceOf(FullBoundedQueueException.class)
                              .hasMessage("put: full bounded queue");
        }

        @Test
        @DisplayName("Test for put method with valid element appends element and increases the size of the queue by 1")
        public void testPutWithValidElementAppendsElement(){
            // Arrange
            int element = 5;
            // Act
            queue.put(element);
            // Assert
            assertThat(queue.size()).isEqualTo(1);
            assertThat(queue.getLast()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("Test for get method")
    class GetTest{
        @Test
        @DisplayName("Test for get method with empty queue throws EmptyBoundedQueueException")
        public void testGetWithEmptyQueueThrowsException(){
            // Arrange
            // Act
            Throwable thrown = catchThrowable(() -> queue.get());
            // Assert
            assertThat(thrown).isInstanceOf(EmptyBoundedQueueException.class)
                              .hasMessage("get: empty bounded queue");
        }

        @Test
        @DisplayName("Test for get method with valid queue removes the first element and decreases the size of the queue by 1")
        public void testGetWithValidQueueGetsElement(){
            // Arrange
            queue.put(5);
            // Act
            int element = queue.get();
            // Assert
            assertThat(element).isEqualTo(5);
            assertThat(queue.size()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("Test for isFull method")
    class IsFullTest{
        @Test
        @DisplayName("Test for isFull method with empty queue returns false")
        public void testIsFullWithEmptyQueueReturnsFalse(){
            // Arrange
            // Act
            boolean result = queue.isFull();
            // Assert
            assertThat(result).isFalse();
        }

        @Test
        @DisplayName("Test for isFull method with full queue returns true")
        public void testIsFullWithFullQueueReturnsTrue(){
            // Arrange
            for(int i = 0; i < 5; i++){
                queue.put(i);
            }
            // Act
            boolean result = queue.isFull();
            // Assert
            assertThat(result).isTrue();
        }

        @Test
        @DisplayName("Test for isFull method with valid not full queue returns false")
        public void testIsFullWithValidQueueReturnsFalse(){
            // Arrange
            queue.put(5);
            // Act
            boolean result = queue.isFull();
            // Assert
            assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("Test for isEmpty method")
    class IsEmptyTest{
        @Test
        @DisplayName("Test for isEmpty method with empty queue returns true")
        public void testIsEmptyWithEmptyQueueReturnsTrue(){
            // Arrange
            // Act
            boolean result = queue.isEmpty();
            // Assert
            assertThat(result).isTrue();
        }

        @Test
        @DisplayName("Test for isEmpty method with not empty queue returns false")
        public void testIsEmptyWithNotEmptyQueueReturnsFalse(){
            // Arrange
            queue.put(5);
            // Act
            boolean result = queue.isEmpty();
            // Assert
            assertThat(result).isFalse();
        }

        @Test
        @DisplayName("Test for isEmpty method with full queue returns false")
        public void testIsEmptyWithFullQueueReturnsFalse(){
            // Arrange
            for(int i = 0; i < 5; i++){
                queue.put(i);
            }
            // Act
            boolean result = queue.isEmpty();
            // Assert
            assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("Test for size method")
    class SizeTest{
        @Test
        @DisplayName("Test for size method with empty queue returns 0")
        public void testSizeWithEmptyQueueReturnsZero(){
            // Arrange
            // Act
            int result = queue.size();
            // Assert
            assertThat(result).isEqualTo(0)
                              .isZero();
        }

        @Test
        @DisplayName("Test for size method with not empty queue returns correct size")
        public void testSizeWithNotEmptyQueueReturnsCorrectSize(){
            // Arrange
            queue.put(5);
            // Act
            int result = queue.size();
            // Assert
            assertThat(result).isEqualTo(1);
        }

        @Test
        @DisplayName("Test for size method with full queue returns correct size")
        public void testSizeWithFullQueueReturnsCorrectSize(){
            // Arrange
            for(int i = 0; i < 5; i++){
                queue.put(i);
            }
            // Act
            int result = queue.size();
            // Assert
            assertThat(result).isEqualTo(5);
        }
    }

    @Nested
    @DisplayName("Test for getFirst method")
    class GetFirstTest{
        @Test
        @DisplayName("Test for getFirst method with empty queue returns 0")
        public void testGetFirstWithEmptyQueueReturnsZero(){
            // Arrange
            // Act
            int result = queue.getFirst();
            // Assert
            assertThat(result).isEqualTo(0)
                              .isZero();
        }

        @Test
        @DisplayName("Test for getFirst method with not empty queue returns first")
        public void testGetFirstWithNotEmptyQueueReturnsFirst(){
            // Arrange
            queue.put(5);
            // Act
            int result = queue.getFirst();
            // Assert
            assertThat(result).isEqualTo(0);
        }

        @Test
        @DisplayName("Test for getFirst method with first element null returns first")
        public void testGetFirstWithFirstElementNullReturnsFirst(){
            // Arrange
            queue.put(5);
            queue.get();
            // Act
            int result = queue.getFirst();
            // Assert
            assertThat(result).isEqualTo(1);
        }

        @Test
        @DisplayName("Test for getFirst method with full queue returns first")
        public void testGetFirstWithFullQueueReturnsFirst(){
            // Arrange
            for(int i = 0; i < 5; i++){
                queue.put(i);
            }
            // Act
            int result = queue.getFirst();
            // Assert
            assertThat(result).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("Test for getLast method")
    class GetLastTest{
        @Test
        @DisplayName("Test for getLast method with empty queue returns 0")
        public void testGetLastWithEmptyQueueReturnsZero(){
            // Arrange
            // Act
            int result = queue.getLast();
            // Assert
            assertThat(result).isEqualTo(0);
        }

        @Test
        @DisplayName("Test for getLast method with not empty queue returns last")
        public void testGetLastWithNotEmptyQueueReturnsLast(){
            // Arrange
            queue.put(5);
            // Act
            int result = queue.getLast();
            // Assert
            assertThat(result).isEqualTo(1);
        }

        @Test
        @DisplayName("Test for getLast method with full queue returns last")
        public void testGetLastWithFullQueueReturnsLast(){
            // Arrange
            for(int i = 0; i < 5; i++){
                queue.put(i);
            }
            // Act
            int result = queue.getLast();
            // Assert
            assertThat(result).isEqualTo(0);
        }

        @Test
        @DisplayName("Test for getLast method with first element null returns last")
        public void testGetLastWithFirstElementNullReturnsLast(){
            // Arrange
            queue.put(5);
            queue.get();
            // Act
            int result = queue.getLast();
            // Assert
            assertThat(result).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("Test for iterator method")
    class IteratorTest{
        
        @Test
        @DisplayName("Test for iterator method with empty queue returns position 0")
        public void testIteratorWithEmptyQueueReturnsZero(){
            //Arrange

            //Act
            int actual = 0;
            while(queue.iterator().hasNext()){
                actual = queue.iterator().next();
            }
            //Assert
            assertThat(actual).isEqualTo(0);
        }
        
        @Test
        @DisplayName("Test for iterator method with not empty queue returns last position")
        public void testIteratorWithNotEmptyQueueReturnsLastPosition(){
            // Arrange
            int actual = 0;
            Iterator<Integer> iterator = queue.iterator();
            queue.put(5);
            // Act
            while(iterator.hasNext()){
                actual = iterator.next();
            }
            // Assert
            assertThat(actual).isEqualTo(5);
        }

        @Test
        @DisplayName("Test for iterator method with full queue returns last position")
        public void testIteratorWithFullQueueReturnsLastPosition(){
            // Arrange
            int actual = 0;
            Iterator<Integer> iterator = queue.iterator();
            for(int i = 0; i < 5; i++){
                queue.put(i);
            }
            // Act
            while(iterator.hasNext()){
                actual = iterator.next();
            }
            // Assert
            assertThat(actual).isEqualTo(4);
        }

        @Test
        @DisplayName("Test for iterator next method with no next element throws NoSuchElementException")
        public void testIteratorNextWithNoNextElementThrowsException(){
            // Arrange
            Iterator<Integer> iterator = queue.iterator();
            // Act
            Throwable thrown = catchThrowable(() -> iterator.next());
            // Assert
            assertThat(thrown).isInstanceOf(NoSuchElementException.class)
                              .hasMessage("next: bounded queue iterator exhausted");
        }
    }
}