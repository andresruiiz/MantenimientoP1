package org.mps.boundedqueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Iterator;

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
        @DisplayName("Test for Constructor with positive capacity")
        public void testConstructorWithPositiveCapacity(){
            // Arrange
            int capacity = 5;
            // Act
            queue = new ArrayBoundedQueue<>(capacity);
            // Assert
            assertThat(queue).isNotNull()
                             .isInstanceOf(ArrayBoundedQueue.class);
        }

        @Test
        @DisplayName("Test for Constructor with negative capacity")
        public void testConstructorWithNegativeCapacity(){
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
        @DisplayName("Test for put method with null element")
        public void testPutWithNullElement(){
            // Arrange
            Integer element = null;
            // Act
            Throwable thrown = catchThrowable(() -> queue.put(element));
            // Assert
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                              .hasMessage("put: element cannot be null");
        }

        @Test
        @DisplayName("Test for put method with full queue")
        public void testPutWithFullQueue(){
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
        @DisplayName("Test for put method with valid element")
        public void testPutWithValidElement(){
            // Arrange
            int element = 5;
            // Act
            queue.put(element);
            // Assert
            assertThat(queue.size()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("Test for get method")
    class GetTest{
        @Test
        @DisplayName("Test for get method with empty queue")
        public void testGetWithEmptyQueue(){
            // Arrange
            // Act
            Throwable thrown = catchThrowable(() -> queue.get());
            // Assert
            assertThat(thrown).isInstanceOf(EmptyBoundedQueueException.class)
                              .hasMessage("get: empty bounded queue");
        }

        @Test
        @DisplayName("Test for get method with valid queue")
        public void testGetWithValidQueue(){
            // Arrange
            queue.put(5);
            // Act
            int element = queue.get();
            // Assert
            assertThat(element).isEqualTo(5);
        }
    }

    @Nested
    @DisplayName("Test for isFull method")
    class IsFullTest{
        @Test
        @DisplayName("Test for isFull method with empty queue")
        public void testIsFullWithEmptyQueue(){
            // Arrange
            // Act
            boolean result = queue.isFull();
            // Assert
            assertThat(result).isFalse();
        }

        @Test
        @DisplayName("Test for isFull method with full queue")
        public void testIsFullWithFullQueue(){
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
        @DisplayName("Test for isFull method with valid not full queue")
        public void testIsFullWithValidQueue(){
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
        @DisplayName("Test for isEmpty method with empty queue")
        public void testIsEmptyWithEmptyQueue(){
            // Arrange
            // Act
            boolean result = queue.isEmpty();
            // Assert
            assertThat(result).isTrue();
        }

        @Test
        @DisplayName("Test for isEmpty method with not empty queue")
        public void testIsEmptyWithNotEmptyQueue(){
            // Arrange
            queue.put(5);
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
        @DisplayName("Test for size method with empty queue")
        public void testSizeWithEmptyQueue(){
            // Arrange
            // Act
            int result = queue.size();
            // Assert
            assertThat(result).isEqualTo(0)
                              .isZero();
        }

        @Test
        @DisplayName("Test for size method with not empty queue")
        public void testSizeWithNotEmptyQueue(){
            // Arrange
            queue.put(5);
            // Act
            int result = queue.size();
            // Assert
            assertThat(result).isEqualTo(1);
        }

        @Test
        @DisplayName("Test for size method with full queue")
        public void testSizeWithFullQueue(){
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
        @DisplayName("Test for getFirst method with empty queue")
        public void testGetFirstWithEmptyQueue(){
            // Arrange
            // Act
            int result = queue.getFirst();
            // Assert
            assertThat(result).isEqualTo(0);
        }

        @Test
        @DisplayName("Test for getFirst method with not empty queue")
        public void testGetFirstWithNotEmptyQueue(){
            // Arrange
            queue.put(5);
            // Act
            int result = queue.getFirst();
            // Assert
            assertThat(result).isEqualTo(0);
        }

        @Test
        @DisplayName("Test for getFirst method with first element null")
        public void testGetFirstWithFirstElementNull(){
            // Arrange
            queue.put(5);
            queue.get();
            // Act
            int result = queue.getFirst();
            // Assert
            assertThat(result).isEqualTo(1);
        }

        @Test
        @DisplayName("Test for getFirst method with full queue")
        public void testGetFirstWithFullQueue(){
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
        @DisplayName("Test for getLast method with empty queue")
        public void testGetLastWithEmptyQueue(){
            // Arrange
            // Act
            int result = queue.getLast();
            // Assert
            assertThat(result).isEqualTo(0);
        }

        @Test
        @DisplayName("Test for getLast method with not empty queue")
        public void testGetLastWithNotEmptyQueue(){
            // Arrange
            queue.put(5);
            // Act
            int result = queue.getLast();
            // Assert
            assertThat(result).isEqualTo(1);
        }

        @Test
        @DisplayName("Test for getLast method with full queue")
        public void testGetLastWithFullQueue(){
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
        @DisplayName("Test for getLast method with first element null")
        public void testGetLastWithFirstElementNull(){
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
        @DisplayName("Test for iterator method with empty queue")
        public void testIteratorWithEmptyQueue(){
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
        @DisplayName("Test for iterator method with not empty queue")
        public void testIteratorWithNotEmptyQueue(){
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
        @DisplayName("Test for iterator method with full queue")
        public void testIteratorWithFullQueue(){
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
    }

    
}