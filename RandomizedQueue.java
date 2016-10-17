import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
   
	//private int capacity = 10;
	private int size;
	private Item[] items;
   
	// construct an empty randomized queue
	public RandomizedQueue(){
	   items = (Item[]) new Object[10];
	}                
  
   // is the queue empty?
   public boolean isEmpty(){
	   return size == 0;
   }                 
   
   // return the number of items on the queue
   public int size(){
	   return size;
   }                      
   
   // add the item
   public void enqueue(Item item){
	   if (item == null) throw new NullPointerException();
	   if (size == items.length) resize(items.length*2);
	   items[size++]=item;
   }         
   
   private void resize(int newCapacity) {
	   //System.out.println("Inside Resize = " + newCapacity);
	   Item[] newItems = (Item[]) new Object[newCapacity];
	   for(int i=0;i<newCapacity/2;i++){
		   //System.out.println("items[i]  = " + items[i]);
		   newItems[i] = items[i]; 
	   }
	   items = newItems;
   }

   // remove and return a random item
   public Item dequeue(){
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   int randomIndex = StdRandom.uniform(size);
	   Item dequeueItem = items[randomIndex];
       if (randomIndex != size-1) 
	      items[randomIndex] = items[size-1];
	   items[size-1] = null;
	   size = size - 1;
	   if (size == items.length/4)
		   {
     		   resize(items.length/2);
		   }
	   return dequeueItem;
   }                  
   
   // return (but do not remove) a random item
   public Item sample()
   {
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   //Find the size of the deque
	   int randomIndex = StdRandom.uniform(size);
	   //System.out.println("size = " + size + "randomIndex = " + randomIndex);
	   return items[randomIndex];
   }                    
   
   
   // return an independent iterator over items in random order
   public Iterator<Item> iterator(){
	   return new RandomizedDequeIterator();
   }
   
   private class RandomizedDequeIterator implements Iterator<Item>{

	private int sizeItems;
	private Item[] returnedItems;
	
	private RandomizedDequeIterator(){
		returnedItems = (Item[]) new Object[size];
		//System.out.println("items.length = "+items.length);
		//System.out.println("returnedItems.length = "+returnedItems.length);
		for (int i=0;i<items.length;i++){
			if(items[i] != null)
				returnedItems[i] = items[i];
		}
		StdRandom.shuffle(returnedItems);
	}
	
	@Override
	public boolean hasNext() {
		//System.out.println("sizeItems" +  sizeItems);
		return sizeItems != size; 
	}
	
	@Override
	public Item next() {
		if (!hasNext()) throw new java.util.NoSuchElementException();
		return returnedItems[sizeItems++];
		
	}

	
	@Override
	public void remove() {
		throw new java.lang.UnsupportedOperationException();
	}
	   
   }
   
   // unit testing
   public static void main(String[] args){
	   /*
	   RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
	   System.out.println("----enqueue 0 to 10 = ");
	   for(int i=0;i<11;i++)
	   {
		   r.enqueue(i);
	   }
	   System.out.println("----deque 6 element ");
	   for(int i=0;i < 6;i++)
		   System.out.print(r.dequeue()+"->");
	   System.out.println();
	   System.out.println("----size = "+ r.size());
	   Iterator d1 = r.iterator();
	   for(Integer i:r)
		   System.out.print(i+"->");
	   System.out.println();
	   System.out.println("----enqueue 10 to 15 ");
	   for(int i=10;i<16;i++)
		   r.enqueue(i);
	   System.out.println();
	   Iterator d2 = r.iterator();
	   for(Integer i:r)
		   System.out.print(i+"->");
	   //System.out.println();
	   //System.out.println("----size = "+ r.size());
	   //Iterator d1 = r.iterator();
	  
	   for(Integer i:r)
		   System.out.print(i+"->");
	   
	   System.out.println();
	   System.out.println("-----------");
	   for(int i=0;i<10;i++)
	   {
		   System.out.print(r.dequeue()+"->");
	   }
	   
	   System.out.println();
	   System.out.println(r.isEmpty());
	   System.out.println();
	   System.out.println(r.size());

	   
	   for(int i=0;i<900;i++)
	   {
		   r.enqueue(i);
	   }
	   
	   System.out.println(r.size());
	   System.out.println();
	   for(Integer i:r)
		   System.out.print(i+"->");
	   
	   System.out.println();
	   System.out.println("---Deleting 900---");
	   for(int i=0;i<900;i++)
	   {
		   System.out.print(r.dequeue()+"->");
	   }
	   System.out.println();
	   System.out.println("---New size---");
	   System.out.println(r.size());
	  */
	   
   }
}
