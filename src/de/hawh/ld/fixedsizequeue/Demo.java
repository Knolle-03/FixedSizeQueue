package de.hawh.ld.fixedsizequeue;


import java.io.*;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

public class Demo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Queues.txt"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Queues.txt"));


        FixedSizeQueue<Integer> lol1 = new FixedSizeQueue<>(4);
        FixedSizeQueue<Integer> lol2 = new FixedSizeQueue<>(4);

        for (int i = 0; i < lol1.getSize() - 1 ; i++) {
            lol1.enqueue(42);
        }

        for (int i = 0; i < lol2.getSize() - 1 ; i++) {
            lol2.enqueue(1);
        }

        lol1.writeObj(oos);
        lol2.writeObj(oos);

        FixedSizeQueue<Integer> lol3 = new FixedSizeQueue<>(4);
        FixedSizeQueue<Integer> lol4 = new FixedSizeQueue<>(4);
        lol3.readObj(ois);
        lol4.readObj(ois);
        System.out.println(lol1.toString());
        System.out.println(lol3.toString());
        System.out.println(lol1.equals(lol3));

        System.out.println(lol2.toString());
        System.out.println(lol4.toString());
        System.out.println(lol2.equals(lol4));

    }
}
