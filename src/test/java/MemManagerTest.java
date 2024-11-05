import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemManagerTest {
	private MemoryManager memoMan;
	@BeforeEach
	void setUp() throws Exception {
		memoMan = new MemoryManager(100);
	}

	@Test
	void test() {
		//check initializing part
		assertNotNull(memoMan.head);
		assertEquals("Free", memoMan.head.getOwner());
		assertEquals(100, memoMan.head.getLength());
		assertEquals(0, memoMan.head.getPosition());
		
		//check request
		MemoryAllocation memo1 = new memoMan.requestMemory(30, "A");
		assertNotNull(memo1);
		assertEquals("A", memo1.getOwner());
		assertEquals(0, memo1.getPosition());
		assertEquals(30, memo1.getLength());
		
		//check remain upate
		assertNotNull(memoMan.head.next);
		assertEquals("Free", memoMan.head.next.getOwner());
		assertEquals(70, memoMan.head.next.getLength());
		assertEquals(30, memoMan.head.next.getPosition());
		
		//check more 
		MemoryAllocation memo2 = new memoMan.requestMemory(30, "A");
		MemoryAllocation memo3 = new memoMan.requestMemory(20, "B");
		assertNotNull(memo2);
		assertNotNull(memo3);
		assertEquals(50, memoMan.head.next.next.getLength()); // there should be 50 remain 
		
		
		//check request larger than the capacity
		MemoryAllocation memo3 = new memoMan.requestMemory(120, "Biger");
		assertNull(memo3);
		
		//check merge:
		
		
		
	}

}
