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
	   head = new MemoryAllocation(Free, 0, size);
	   head.next = head;
	   head.prev = head;
   }



    /**
       takes the size of the requested memory and a string of the process requesting the memory
       returns a memory allocation that satisfies that request, or
       returns null if the request cannot be satisfied.
     */
    
   public MemoryAllocation requestMemory(long size,String requester)
   {
	   MemoryAllocation curr = head;
	   
	   while (curr != null) {
		   if (curr.owner.equals(Free) && curr.len >= size) {
			   if (curr.len > size) {
				   //cuz the len larger than size, there will be
				   //some free space left, so we need new node to hold the left
				   MemoryAllocation newNode = new MemoryAllocation(Free, 
						   curr.pos+size, curr.len-size);
				   if (curr.next != null) {
					   curr.next.prev = newNode;
				   }
				   curr.next = newNode;
				   curr.len = size;
			   }
			   curr.owner = requester;
			   return curr;
		   }
		   curr = curr.next; //move to next one to check
	   }
	   return null; // if there is no enough space, then should return null
   }


    
    /**
       takes a memoryAllcoation and "returns" it to the system for future allocations.
       Assumes that memory allocations are only returned once.       

     */
   public void returnMemory(MemoryAllocation mem)
   {
	   MemoryAllocation curr = head;
	   while (curr != null) {
		   if (curr.pos == mem.getPosition() && curr.owner.equals(mem.getOwner())) {
			   curr.owner = Free;
			   MergeMem(curr);
			   return;
		   }
		   curr = curr.next;
	   }
	   
   }
   
   private void MergeMem(MemoryAllocation mem) {
	   //merge left
	   if (mem.prev != null && mem.prev.owner.equals(Free)) {
		   mem.prev.len += mem.len;
		   mem.prev.next = mem.next;
		   if (mem.next != null) {
			   mem.next.prev = mem.prev;
		   }
		   mem = mem.next.prev;
	   }
	   //merge right
	   if (mem.next != null && mem.next.owner.equals(Free)){
		   mem.len += mem.next.len;
		   mem.next = mem.next.next;
		   if (mem.next != null) {
			   mem.next.prev = mem;
		   }
	   }
	   
   }
    



}
