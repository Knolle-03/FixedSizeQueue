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
            if(rear < elements.length){
                elements[rear] = element;
                rear++; }
            else {
                rear = 0;
                elements[rear] = element;
                rear++;
            }
        }


    }


    @Override
    public void dequeue() {
        if (isEmpty()) {
            throw new QueueEmptyException("The queue is already empty!");
        } else {
            if (front < elements.length) {
                elements[front] = null;
                front++;
            } else  {
                front = 0;
                elements[front] = null;
                front++;
            }
        }
    }


    @Override
    public E peek() {
        if (isEmpty()) {
            throw new QueueEmptyException("The queue is already empty!");}
        return elements[front];
    }

    @Override
    public boolean isFull(){
        for(E e: elements)
            if (e == null){
                return false;}
        return true;
    }

    @Override
    public boolean isEmpty(){
        for(E e: elements)
            if (e != null){
                return false;}
        return true;
    }

    public void writeObject(ObjectOutputStream oos) throws IOException {
        this.writeObj(oos);
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.readObj(ois);
    }

  /*  private void writeObj() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Queues.bin"));

        oos.writeObject(this);

    }

    private Object readObj() throws IOException, ClassNotFoundException {
            ObjectInputStream ois = new ObjectInputStream(new  FileInputStream("Queues.bin"));

            return ois.readObject();
    }*/


    private void readObj(ObjectInputStream ois) throws ClassNotFoundException, IOException
    {
        FixedSizeQueue<E> queue = new FixedSizeQueue<>();
        E[] elements = (E[]) (ois.readObject());
        front = ois.readInt();
        rear = ois.readInt();
    }

    private void writeObj(ObjectOutputStream oos) throws IOException
    {
        oos.writeObject(elements);
        oos.writeInt(front);
        oos.writeInt(rear);
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
