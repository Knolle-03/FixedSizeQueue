package de.hawh.ld.fixedsizequeue;
import de.hawh.kahlbrandt.ss2019.a05.interfaces.Queue;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class FixedSizeQueue<E> implements Serializable, Queue<E> {

    private E[] elements;
    private final int size;
    private int front = 0;
    private int rear = 0;

    public FixedSizeQueue()  {
        this.size = DEFAULT_CAPACITY;
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public FixedSizeQueue(int size){
        this.size = size;
        elements = (E[]) new Object[size];
    }


    int getSize() {
        return size;
    }

    @Override
    public void enqueue(E element) {
        if (isFull()) {
            throw new QueueFullException("The queue is already full!");
        } else {
            if(rear < size){
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
            if (front < size) {
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



    private void writeObj(FixedSizeQueue q) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Queues.txt"));

        oos.writeObject(q);

    }

    private Object readObj() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new  FileInputStream("Queues.txt"));

        return ois.readObject();
    }





    @Override
    public String toString() {
        return "elements=" + Arrays.toString(elements) +
               ", size = " + size +
               ", front = " + front +
               ", rear = " + rear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FixedSizeQueue<?> that = (FixedSizeQueue<?>) o;
        return size == that.size &&
                front == that.front &&
                rear == that.rear &&
                Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, front, rear);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }





}





/*
 for (int i = 0; i < elements.length; i++) {
        if (elements[i] == null) {
        elements[i] = element;
        break;
        }
        }*/
