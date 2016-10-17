import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private Node first, last;
	private int size;
	
	private class Node{
		Item item;
		Node next;
		Node previous;
	}
	
	
	// construct an empty deque
	public Deque() {
		size = 0;
	}    
	
	// is the deque empty?
    public boolean isEmpty(){
    	return size == 0;
    }    
    
    // return the number of items on the deque
   public int size(){
	   return size;
   }   
   
   // add the item to the front O(1)
   public void addFirst(Item item){
	   if (item == null) throw new NullPointerException();
	   if (isEmpty()){
		   //System.out.println("adding the 1st time" + item);
		   first = last = new Node();
		   first.item = item;
	   }
	   else{
		   //System.out.println("adding the 2nd item onwards" + item);
		   Node oldFirst = first;
		   first = new Node();
		   first.item = item;
		   first.next = oldFirst;
		   //System.out.println("Linking the new node" + first.item + "to previous " + oldFirst.item);
		   oldFirst.previous = first;
	   }
	   size++;
   }
   
// add the item to the last O(1)
   public void addLast(Item item){
	   if (item == null) throw new NullPointerException();
	   if (isEmpty()){
		   first = last = new Node();
		   last.item = item;
	   }
	   else{
		   Node oldLast = last;
		   last = new Node();
		   last.item = item;
		   last.previous = oldLast;
		   oldLast.next = last;
	   }
	   size++;
   }
   
   
   // remove and return the item from the front
   public Item removeFirst(){
	   if(isEmpty()) throw new java.util.NoSuchElementException();
	   Item firstItem;
	   if (size()==1){
		   firstItem = first.item;
		   first = last = null;
	   }
	   else{
		   Node oldFirst = first;
		   firstItem = first.item;
		   first = first.next;
		   first.previous = null;
		   oldFirst = null;
	   }
	   size--;
	   return firstItem;
   }
   
   
   // remove and return the item from the end
   /**
    * Complexity is O(1) here
    * 
    * @return
    */
   public Item removeLast(){
	   if(isEmpty()) throw new java.util.NoSuchElementException();
	   Item lastItem;
	   lastItem = last.item;
	   if (size()==1){
		   first = last = null;
	   } 
	   else{
		    Node oldLast = last;
		    last = last.previous;
		    last.next = null;
		    oldLast = null;
	   }
	   size--;
	   return lastItem;
	   
   }

   // return an iterator over items in order from front to end
   public Iterator<Item> iterator(){
	   return new DequeIterator();
   }
   
   private class DequeIterator implements Iterator<Item>{

	private Node current = first;
	
	@Override
	public boolean hasNext() {
		return current != null;
	}
	
	@Override
	public Item next() {
		if (current == null) throw new java.util.NoSuchElementException();
		Item currentItem = current.item;
		current = current.next;
		return currentItem;
	}


	@Override
	public void remove() {
		throw new java.lang.UnsupportedOperationException();
	}
  
   }
   
   // unit testing
   public static void main(String[] args){
	   Deque<Integer> di = new Deque<Integer>();
	   di.addLast(1);
	   di.addFirst(2);
	   di.addLast(3);
	   di.addLast(4);
	   di.addFirst(5);
	   di.addFirst(6);
	   di.addLast(7);
	   di.removeLast();
	   /*
	   System.out.println("Add 1st");
	   for(int i=0;i<10;i++)
	   {
		   di.addFirst(i);
	   }
	   Iterator<Integer> d1 = di.iterator();
	   while(d1.hasNext()){
		   System.out.print(d1.next()+"->");
	   }
	   
	   System.out.println("Add Last");
	   for(int i=10;i<20;i++)
	   {
		  di.addLast(i);
	   }
	   System.out.println("----size = "+ di.size());
	   Iterator<Integer> d = di.iterator();
	   while(d.hasNext()){
		   System.out.print(d.next()+"->");
	   }
	   
	   
	   System.out.println();
	   System.out.println("Removing First");
	   for(int i=0;i<10;i++)
	   {
		   System.out.print(di.removeFirst()+"->");
	   }
	   Iterator<Integer> d2 = di.iterator();
	   while(d2.hasNext()){
		   System.out.print(d2.next()+"->");
	   }
	   
	   
	   System.out.println();
	   System.out.println(di.size());
	   
	   
	   System.out.println();
	   System.out.println("Removing Last");
	   for(int i=0;i<10;i++)
	   {
		   System.out.print(di.removeLast()+"->");
	   }
	   
	   System.out.println();
	   System.out.println(di.size());
	  
	   //System.out.print(di.removeFirst());
	   System.out.println("Add Last");
	   for(int i=10;i<20;i++)
	   {
		  di.addLast(i);
	   }
	   System.out.println("Final ");
	   for(Integer i:di)
		   System.out.print(i+",");
	   System.out.println();
	   System.out.println(di.size());
	   */
   }
}
