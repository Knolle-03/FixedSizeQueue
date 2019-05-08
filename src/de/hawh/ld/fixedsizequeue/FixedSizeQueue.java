package de.hawh.ld.fixedsizequeue;
import de.hawh.kahlbrandt.ss2019.a05.interfaces.Queue;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class FixedSizeQueue<E> implements Serializable, Queue<E> {

    /**
     * queue size
     * @serial
     */
    private int size;
    /**
     * front of queue
     * @serial
     */
    private int front = 0;
    /**
     * rear of queue
     * @serial
     */
    private int rear = 0;
    /**
     * queue data
     */
    private transient E[] elements;

    public FixedSizeQueue()  {
        size = DEFAULT_CAPACITY;
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public FixedSizeQueue(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("The size needs to be greater than 0.");
        } else {
            this.size = size;
            elements = (E[]) new Object[size];
        }
    }

    public int getSize(){
        return elements.length;
    }

    @Override
    public void enqueue(E element) {
        if (isFull()) {
            throw new QueueFullException("The queue is already full!");
        } else {
            elements[rear] = element;
            rear = (rear < elements.length - 1) ? ++rear : 0;
            }
    }

    @Override
    public void dequeue() {
        if (isEmpty()) {
            throw new QueueEmptyException("The queue is already empty!");
        } else {
            elements[front] = null;
            front = (front < elements.length - 1) ? ++front :  0;
            }
        }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new QueueEmptyException("The queue is empty!");}
        return elements[front];
    }

    @Override
    public boolean isFull(){
        return (front == rear && elements[front] != null);
    }

    @Override
    public boolean isEmpty(){
        return (front == rear && elements[rear] == null);
    }

    public void writeObj(ObjectOutputStream oos) throws IOException {
        this.writeObject(oos);
    }

    public void readObj(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.readObject(ois);
    }

    /**
     * @param oos stream to write the data to.
     * @throws IOException if IO conflicts occur
     * @throws ClassNotFoundException if class not found
     */
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        front = ois.readInt();
        rear = ois.readInt();
        size = ois.readInt();
        elements = (E[]) (new Object[size]);
        for (int i = 0; i < size ; i++) {
            elements[i] = (E) ois.readObject();
        }
    }
    /**
     * @param oos stream to write the data to.
     * @throws IOException if IO conflicts occur
     * @serialData front, rear and size of queue to ensure the elements are enqueued in the right order
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeInt(front);
        oos.writeInt(rear);
        oos.writeInt(size);
        for(Object element : elements){
            oos.writeObject(element);
        }
    }

    @Override
    public String toString() {
        return "elements=" + Arrays.toString(elements) + "\n" +
               "size = " + elements.length + "\n" +
               "front = " + front + "\n" +
               "rear = " + rear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FixedSizeQueue<?> that = (FixedSizeQueue<?>) o;
        return front == that.front &&
                rear == that.rear &&
                Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(elements.length, front, rear);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }
}
