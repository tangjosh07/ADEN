package MyLinkedList;
import java.util.ArrayList;
import java.util.Random;

public class MyLinkedListBenchmark {

	public static void main(String[] args) {
		ArrayList<Integer> aList=new ArrayList<Integer>();
		MyLinkedList<Integer> lList=new MyLinkedList<Integer>();
		
		System.out.println("Benchmarking ArrayList vs MyLinkedList: Add to index 0");
		int trials=10;
		int numIntsToAdd = 5000;
		int trialInc = 5000;
		for (int t = 0; t < trials; t++) {
			long alStartTime = System.nanoTime();
			for (int i = 0; i < numIntsToAdd; i++)
				aList.add(0,i);
			long alStopTime = System.nanoTime();
			aList = new ArrayList<Integer>();

			long llStartTime = System.nanoTime();
			for (int i = 0; i < numIntsToAdd; i++)
				lList.add(0,i);
			long llStopTime = System.nanoTime();
			lList = new MyLinkedList<Integer>();
			System.out.printf("%-10d%-12d%d\n",numIntsToAdd,(alStopTime-alStartTime),(llStopTime-llStartTime));		
			numIntsToAdd+=trialInc;
		}

		
		System.out.println("\n\nBenchmarking ArrayList vs MyLinkedList: Add to end");
		numIntsToAdd = 5000;
		for (int t = 0; t < trials; t++) {
			long alStartTime = System.nanoTime();
			for (int i = 0; i < numIntsToAdd; i++)
				aList.add(i);
			long alStopTime = System.nanoTime();
			aList = new ArrayList<Integer>();

			long llStartTime = System.nanoTime();
			for (int i = 0; i < numIntsToAdd; i++)
				lList.add(i);
			long llStopTime = System.nanoTime();
			lList = new MyLinkedList<Integer>();
			System.out.printf("%-10d%-12d%d\n",numIntsToAdd,(alStopTime-alStartTime),(llStopTime-llStartTime));
			numIntsToAdd+=trialInc;
		}
		
		System.out.println("\n\nBenchmarking ArrayList vs MyLinkedList: Add to middle");
		numIntsToAdd = 5000;
		for (int t = 0; t < trials; t++) {
			long alStartTime = System.nanoTime();
			for (int i = 0; i < numIntsToAdd; i++)
				aList.add(i>>1,i);
			long alStopTime = System.nanoTime();
			aList = new ArrayList<Integer>();

			long llStartTime = System.nanoTime();
			for (int i = 0; i < numIntsToAdd; i++)
				lList.add(i>>1,i);
			long llStopTime = System.nanoTime();
			lList = new MyLinkedList<Integer>();
			System.out.printf("%-10d%-12d%d\n",numIntsToAdd,(alStopTime-alStartTime),(llStopTime-llStartTime));
			numIntsToAdd+=trialInc;
		}
		
		Random rn = new Random();
		System.out.println("\n\nBenchmarking ArrayList vs MyLinkedList: Random gets");
		numIntsToAdd = 5000;
		for (int t = 0; t < trials; t++) {
			int[] rndInd = new int[numIntsToAdd];  // creates random indexes array of the appropriate length
		
			for (int i = 0; i < numIntsToAdd; i++)
				rndInd[i] = rn.nextInt(numIntsToAdd);  // initialize the random indexes array to valid, random indices

			for(int i = 0; i < numIntsToAdd;i++) {
				aList.add(i);
			}
			long alStartTime = System.nanoTime();
			for ( int i = 0; i < numIntsToAdd; i++) {
				aList.get(rndInd[i]);
			}
			long alStopTime = System.nanoTime();
			aList = new ArrayList<Integer>();

			for (int i = 0; i < numIntsToAdd; i++) {
				lList.add(i);
			}
			long llStartTime = System.nanoTime();
			for (int i = 0; i < numIntsToAdd; i++) {
				lList.get(rndInd[i]);
			}
			long llStopTime = System.nanoTime();
			lList = new MyLinkedList<Integer>();
			System.out.printf("%-10d%-12d%d\n",numIntsToAdd,(alStopTime-alStartTime),(llStopTime-llStartTime));
			numIntsToAdd+=trialInc;
		}
		
	
	}

}
