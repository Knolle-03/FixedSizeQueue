package de.hawh.ld.fixedsizequeue;
import de.hawh.kahlbrandt.ss2019.a05.interfaces.Queue;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class FixedSizeQueue<E> implements Serializable, Queue<E> {

    private E[] elements;
    private int front = 0;
    private int rear = 0;

    public FixedSizeQueue()  {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public FixedSizeQueue(int size){
        elements = (E[]) new Object[size];
    }


    int getSize() {
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

  /*  private void writeObj() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Queues.bin"));

        oos.writeObject(this);

    }

    private Object readObj() throws IOException, ClassNotFoundException {
            ObjectInputStream ois = new ObjectInputStream(new  FileInputStream("Queues.bin"));

            return ois.readObject();
    }*/


    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException
    {
        FixedSizeQueue<E> queue = new FixedSizeQueue<>();
        E[] elements = (E[]) (ois.readObject());
        front = ois.readInt();
        rear = ois.readInt();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException
    {
        oos.writeObject(elements);
        oos.writeInt(front);
        oos.writeInt(rear);
        oos.close();
    }




    @Override
    public String toString() {
        return "elements=" + Arrays.toString(elements) +
               ", size = " + elements.length +
               ", front = " + front +
               ", rear = " + rear;
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
