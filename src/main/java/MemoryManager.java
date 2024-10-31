public class MemoryManager
{
   protected MemoryAllocation head;
    
   protected final String Free = "Free";
   private long size;

    /* size- how big is the memory space.  
     *  Memory starts at 0
     *
     */
   public MemoryManager(long size)
   {
	   this.size = size;
   }



    /**
       takes the size of the requested memory and a string of the process requesting the memory
       returns a memory allocation that satisfies that request, or
       returns null if the request cannot be satisfied.
     */
    
   public MemoryAllocation requestMemory(long size,String requester)
   {
      return null;
   }


    
    /**
       takes a memoryAllcoation and "returns" it to the system for future allocations.
       Assumes that memory allocations are only returned once.       

     */
   public void returnMemory(MemoryAllocation mem)
   {
	   MemoryAllocation current = head;
	   merge(current);
   }
   
   private void merge(MemoryAllocation Node) {
	   
   }
    



}
