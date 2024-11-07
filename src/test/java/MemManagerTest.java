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
		
		
		//check request
		MemoryAllocation memo1 = memoMan.requestMemory(30, "A");
		assertNotNull(memo1);
		assertEquals("A", memo1.getOwner());
		assertEquals(0, memo1.getPosition());
		assertEquals(30, memo1.getLength());
		
		
		
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
        
		
	}

}
