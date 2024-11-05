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
		/*check initializing part
		MemoryAllocation memoinitial = memoMan.requestMemory(100, "Free");
		assertNotNull(memoinitial);
		assertEquals("Free", memoinitial.getOwner());
		assertEquals(100, memoinitial.getLength());
		assertEquals(0, memoinitial.getPosition()); */
		
		//check request
		MemoryAllocation memo1 = memoMan.requestMemory(30, "A");
		assertNotNull(memo1);
		assertEquals("A", memo1.getOwner());
		assertEquals(0, memo1.getPosition());
		assertEquals(30, memo1.getLength());
		
		/*check free's update
		assertNotNull(memoMan.head.next);
		assertEquals("Free", memoMan.head.next.getOwner());
		assertEquals(70, memoMan.head.next.getLength());
		assertEquals(30, memoMan.head.next.getPosition()); */
		
		/*check more 
		MemoryAllocation memo2 = memoMan.requestMemory(30, "B");
		MemoryAllocation memo3 = memoMan.requestMemory(20, "C");
		assertNotNull(memo2);
		assertNotNull(memo3);
		//assertEquals(50, memoMan.head.next.next.getLength()); // there should be 50 remain 
		*/
		
		//check request larger than the capacity
		assertNull(memoMan.requestMemory(120, "Biger"));
		
		//check merge:
		MemoryAllocation memoA = memoMan.requestMemory(30, "A"); 
		MemoryAllocation memoB = memoMan.requestMemory(20, "B");
		MemoryAllocation memoC = memoMan.requestMemory(20, "C");
		memoMan.returnMemory(memoA);
        memoMan.returnMemory(memoC);     
        memoMan.returnMemory(memoB);
        
        MemoryAllocation memo2 = memoMan.requestMemory(70, "D");
        assertEquals(30, memo2.getPosition());
        /*assertEquals("Free", memoA.getOwner());
        assertEquals(0, memoA.getPosition());
        assertEquals(100, memoA.getLength());*/
		
	}

}
