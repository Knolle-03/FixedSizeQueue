package de.hawh.ld.fixedsizequeue;


import java.io.*;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

public class Demo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Date date = new Date();
        long time = date.getTime();
        Timestamp ts1 = new Timestamp(time);
        Timestamp ts2 = new Timestamp(time);
        Timestamp ts3 = new Timestamp(time);


        Method[]  m = FixedSizeQueue.class.getDeclaredMethods();

        for (Method method : m) {
            System.out.println(method.getName());
        }

        System.out.println(ts1);
        FixedSizeQueue<String> queue1 = new FixedSizeQueue<>(5);

        for (int i = 0; i < queue1.getSize(); i++) {
            queue1.enqueue("Hello");
            System.out.println(queue1);
        }

        for (int i = 0; i < queue1.getSize(); i++) {
            queue1.dequeue();
            System.out.println(queue1);
        }

        System.out.println(queue1.isFull());



        ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream("Queues.txt"));
        ObjectInputStream ois =new ObjectInputStream(new FileInputStream("Queues.txt"));

        queue1.writeObj(oos);


        /*FixedSizeQueue<String> queue2 = (FixedSizeQueue<String>) queue1.readObj(ois);

        System.out.println(queue1);
        System.out.println(queue2);
        System.out.println(queue1.equals(queue2));*/


    }
}
