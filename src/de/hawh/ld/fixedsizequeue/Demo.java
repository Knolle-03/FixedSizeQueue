package de.hawh.ld.fixedsizequeue;


import java.lang.reflect.Method;

public class Demo {

    public static void main(String[] args) {
        Method[]  m = FixedSizeQueue.class.getDeclaredMethods();

        for (Method method : m) {
            System.out.println(method.getName());
        }


        FixedSizeQueue<String> queue = new FixedSizeQueue<>(5);

        for (int i = 0; i < queue.getSize(); i++) {
            queue.enqueue("Hello");
            System.out.println(queue);
        }




    }




}
