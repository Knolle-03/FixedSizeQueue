package de.hawh.ld.fixedsizequeue;
import de.hawh.ld.fixedsizequeue.Queue;

import java.lang.reflect.Type;

public class FixedSizeQueue implements Queue<E>{

    private final int size;

    public FixedSizeQueue(){
        size = DEFAULT_CAPACITY;
    }

    public FixedSizeQueue(int size) {
        this.size = size;
    }

    @Override
    public void enqueue(E element){

    }

    @Override
    public void dequeue(){}

    @Override
    public E peek() {}

    @Override
    public boolean isFull(){}

    @Override
    public boolean isEmpty(){}



}
