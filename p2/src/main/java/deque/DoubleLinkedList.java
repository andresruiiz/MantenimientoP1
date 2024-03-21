package deque;

public class DoubleLinkedList<T> implements DoubleLinkedQueue<T> {

    private LinkedNode<T> first;
    private LinkedNode<T> last;
    private int size;

    @Override
    public void prepend(T value) {
        LinkedNode<T> newNode = new LinkedNode<>(value, null, first);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.setPrevious(newNode);
        }
        first = newNode;
        size++;
    }

    @Override
    public void append(T value) {
        LinkedNode<T> newNode = new LinkedNode<>(value, last, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    @Override
    public void deleteFirst() {
        if (isEmpty()) {
            throw new DoubleLinkedQueueException("Deque is empty");
        }
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.getNext();
            first.setPrevious(null);
        }
        size--;
    }

    @Override
    public void deleteLast() {
        if (isEmpty()) {
            throw new DoubleLinkedQueueException("Deque is empty");
        }
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.getPrevious();
            last.setNext(null);
        }
        size--;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new DoubleLinkedQueueException("Deque is empty");
        }
        return first.getItem();
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new DoubleLinkedQueueException("Deque is empty");
        }
        return last.getItem();
    }

    @Override
    public int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }
}
