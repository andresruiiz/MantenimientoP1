package deque;

public class DoubleLinkedList<T> implements DoubleLinkedQueue<T> {

    private LinkedNode<T> first;
    private LinkedNode<T> last;
    private int size;

    public DoubleLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void prepend(T value) {
        LinkedNode<T> newNode = new LinkedNode<>(value, null, null);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setNext(first);
            first.setPrevious(newNode);
            first = newNode;
        }
        size++;
    }

    @Override
    public void append(T value) {
        LinkedNode<T> newNode = new LinkedNode<>(value, null, null);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setPrevious(last);
            last.setNext(newNode);
            last = newNode;
        }
        size++;
    }

    @Override
    public void deleteFirst() {
        if (!isEmpty()) {
            if (size == 1) {
                first = null;
                last = null;
            } else {
                first = first.getNext();
                first.setPrevious(null);
            }
            size--;
        }
    }

    @Override
    public void deleteLast() {
        if (!isEmpty()) {
            if (size == 1) {
                first = null;
                last = null;
            } else {
                last = last.getPrevious();
                last.setNext(null);
            }
            size--;
        }
    }

    @Override
    public T first() {
        if (!isEmpty()) {
            return first.getItem();
        }
        return null;
    }

    @Override
    public T last() {
        if (!isEmpty()) {
            return last.getItem();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }
}
