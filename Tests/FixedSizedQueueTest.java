import static org.junit.jupiter.api.Assertions.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hawh.ld.fixedsizequeue.FixedSizeQueue;
import de.hawh.ld.fixedsizequeue.QueueFullException;
import de.hawh.ld.fixedsizequeue.QueueEmptyException;
import de.hawh.ld.fixedsizequeue.Queue;
/**
 * Testcases for a class to be written as a lab task.
 * @author Bernd Kahlbrandt
 *
 */
class FixedSizedQueueTest {
	private Queue<String> emptyQueue = new FixedSizeQueue<>();
	private List<String> stringTestData = List.of("42", "11", "08/15");
	private Queue<String> smallQueue01;
	private Queue<String> smallQueue02;

	/**
	 * Builds some {@link FixedSizeQueue}s from test data.
	 * May throw exceptions, if methods are not correctly implemented.
	 */
	@BeforeEach
	public void setUp() {
	try {
		smallQueue01 = new FixedSizeQueue<>(3);
		smallQueue02 = new FixedSizeQueue<>(3);
		for(String s : stringTestData) {
			smallQueue01.enqueue(s);
			smallQueue02.enqueue(s);
		}
	}catch (Exception e) {
		fail(e.toString() + "thrown. May be due to errors concerning constructors in enqueue");
	}
	}
	@Test
	void testConstructor() {
		assertTrue(emptyQueue.isEmpty());
		assertFalse(smallQueue01.isEmpty());
	}
	@Test
	void testEnqueue() {
		assertTrue(emptyQueue.isEmpty());
		assertFalse(emptyQueue.isFull());
		assertEquals(stringTestData.get(0), smallQueue01.peek());
		assertDoesNotThrow(()->smallQueue01.dequeue());
		assertDoesNotThrow(()->smallQueue01.enqueue("42"));
		assertTrue(smallQueue01.isFull());
		
	}
	@Test
	void testEnqueueException() {
		assertThrows(QueueFullException.class, ()->smallQueue01.enqueue("One too many"));
	}

	@Test
	void testDequeue() {
		for (String s : stringTestData) {
			assertEquals(s, smallQueue01.peek());
			smallQueue01.dequeue();
		}
		assertTrue(smallQueue01.isEmpty());
	}

	@Test
	void testDequeueException() {
		assertThrows(QueueEmptyException.class, ()->emptyQueue.dequeue());
		/**
		 * Uncritical: Here it's the simplest loop. 
		 * Looking for a better solution. 
		 */
		for(@SuppressWarnings("unused") String s:stringTestData) {			
			smallQueue01.dequeue();
		}
		assertThrows(QueueEmptyException.class, ()->smallQueue01.dequeue());
		
		
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(emptyQueue.isEmpty());
		assertFalse(smallQueue01.isEmpty());
		assertDoesNotThrow(()->smallQueue01.dequeue());
	}
	
	@Test
	void testIsFull() {
		assertTrue(smallQueue01.isFull());
		assertFalse(emptyQueue.isFull());
		assertDoesNotThrow(()->smallQueue01.dequeue());
	}

	@Test
	void testPeek() {
		assertEquals(stringTestData.get(0), smallQueue01.peek());
	}
	
	@Test
	void testPeekException() {
		assertThrows(QueueEmptyException.class,() -> emptyQueue.peek());
	}

	@Test
	void testEqualsHashcode() {
		if(smallQueue01.equals(smallQueue02)) {
			assertEquals(smallQueue01.hashCode(), 
					     smallQueue02.hashCode(),
					     "equals and hashCode may be inconsistent!");
		}
	}

	@Test
	void testOverwrites() {
		try {
			FixedSizeQueue.class.getDeclaredMethod("toString");
		} catch (NoSuchMethodException | SecurityException e) {
			fail("Have you overwritten 'toString'?");
		}
		try {
			FixedSizeQueue.class.getDeclaredMethod("equals");
		} catch (NoSuchMethodException | SecurityException e) {
			fail("Have you overwritten 'equals'?");
		}
		try {
			FixedSizeQueue.class.getDeclaredMethod("hashCode");
		} catch (NoSuchMethodException | SecurityException e) {
			fail("Have you overwritten 'hashCode'?");
		}
	}
	@Test
	void testCustomSerializedForm() {
		List<Method> declaredMethods = Arrays.asList(FixedSizeQueue.class.getDeclaredMethods());
		try {
			Method writeObj = FixedSizeQueue.class.getDeclaredMethod("writeObject",ObjectOutputStream.class);
			Method readObj = FixedSizeQueue.class.getDeclaredMethod("readObject",ObjectInputStream.class);
			assertTrue(Modifier.isPrivate(writeObj.getModifiers()), "writeObject is not private!");
			assertTrue(Modifier.isPrivate(readObj.getModifiers()), "readObject is not private!");
			assertTrue(declaredMethods.contains(writeObj));
			assertTrue(declaredMethods.contains(readObj));
		} catch (NoSuchMethodException | SecurityException e) {
			fail("readObject or writeObject not defined!");
		}
	}
	
}


