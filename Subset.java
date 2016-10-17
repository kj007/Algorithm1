import edu.princeton.cs.algs4.StdIn;
public class Subset {

	private static RandomizedQueue<String> randomQueue;
	
	public static void main(String[] args) {
		int k = Integer.valueOf(args[0]);
		randomQueue = new RandomizedQueue<String>();
		while(!StdIn.isEmpty()){
			String line = StdIn.readString();
			randomQueue.enqueue(line);
		}
		
		for(int i=0;i < k;i++)
			System.out.println(randomQueue.dequeue());
	}

}
