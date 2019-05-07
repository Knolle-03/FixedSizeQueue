package de.hawh.ld.fixedsizequeue;


import java.io.*;
import java.lang.reflect.Method;

public class Demo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Method[]  m = FixedSizeQueue.class.getDeclaredMethods();

        for (Method method : m) {
            System.out.println(method.getName());
        }


        FixedSizeQueue<String> queue1 = new FixedSizeQueue<>(5);

        for (int i = 0; i < queue1.getSize(); i++) {
            queue1.enqueue("Hello");
            System.out.println(queue1);
        }

        ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream("Queues.txt");
        ObjectInputStream ois =new ObjectInputStream(new FileInputStream("Queues.txt")

        queue1.writeObject(oos));
        FixedSizeQueue<String> queue2 = (FixedSizeQueue<String>) queue1.readObject(ois);

        System.out.println(queue1);
        System.out.println(queue2);
        System.out.println(queue1.equals(queue2));


    }




}
