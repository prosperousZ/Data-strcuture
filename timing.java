package assignment08;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import assignment08.BinarySearchTree;

/**
 * 
 * @author Paul Carlson, Haoze Zhang
 *
 */
public class timing {
	public static final int TIMES_TO_LOOP = 1000;
	public static final long SEED = 123456l;

	public static void main(String[] args) {
		System.out.println("*****Timing for add and contains for random number of Java's tree*****");
		timingJavaTreeForAddAndContainsForRandomNumber();
		System.out.println("*****************************");
		System.out.println("*****Timing for add and contains for Ordered number of Java's tree*****");
		timingJavaTreeForAddOrderNumberAndContains();
		System.out.println("*****************************");
		System.out.println("*****Timing for add and contains for random number*****");
		timingForAddAndContainsForRandomNumber();
		System.out.println("**********************************");
		System.out.println("*****Timing for add and contains for Ordered number*****");
		timingForAddOrderNumberAndContains();
		System.out.println("*****************************");
		
	}

	private static void timingForAddOrderNumberAndContains() {
		System.out.println("N Elements \t Add \t Contains");
		// let things stabilize
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) {

			for (int n = 500; n <= 1000000; n *= 2) {
				// time to add n elements to the binary search tree
				BinarySearchTree<Integer> tester = new BinarySearchTree<>();
				long totalTime = 0;
				long beginTime, endTime;
				for (int j = 0; j < TIMES_TO_LOOP; j++) {
					beginTime = System.nanoTime();
					for (int item = 0; item < n; item++) {
						tester.add(item);
					}
					endTime = System.nanoTime();
					tester.clear();
					totalTime = totalTime + (endTime - beginTime);
				}
				// make sure there is a tree to time for contains
				for (int item = 0; item < n; item++) {
					tester.add(item);
				}

				long midptTime = System.nanoTime();
				// time the empty loop
				for (long i = 0; i < TIMES_TO_LOOP; i++) {
					for (int j = 0; j < n; j++) {
					}
				}
				long stopTime = System.nanoTime();
				// Start time for contains
				long startContains = System.nanoTime();
				for (int j = 0; j < TIMES_TO_LOOP; j++) {
					for (int item = 0; item < n; item++) {
						tester.contains(item);
					}
				}
				long midContains = System.nanoTime();

				// compute the time to add n elements to the tree
				double timeAdd = totalTime / (TIMES_TO_LOOP);

				// compute average time for contains on same tree
				double avgTimeContains = ((midContains - startContains) - (stopTime - midptTime)) / (TIMES_TO_LOOP);
				double timeContains = avgTimeContains;
				System.out.println(n + "\t" + timeAdd + "\t" + timeContains); // print
																				// to
																				// console
			}
		}
	}

	private static void timingForAddAndContainsForRandomNumber() {
		System.out.println("N Elements \t Add \t Contains");
		// let things stabilize
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000)
			;

		for (int n = 500; n <= 1000000; n *= 2) {
			// time the routine
			ArrayList<Integer> holderList = new ArrayList<Integer>();
			Random random = new Random(SEED);
			for (int i = 0; i < n; i++) {
				int num = random.nextInt(n);
				if (!holderList.contains(num)) {
					holderList.add(num);
				}
			}

			// time to add n elements to the binary search tree
			BinarySearchTree<Integer> tester = new BinarySearchTree<>();
			long totalTime = 0;
			long beginTime, endTime;
			// startTime = System.nanoTime();
			for (int j = 0; j < TIMES_TO_LOOP; j++) {
				beginTime = System.nanoTime();
				for (int item : holderList) {
					tester.add(item);
				}
				endTime = System.nanoTime();
				tester.clear();
				totalTime = totalTime + (endTime - beginTime);
			}
			long midptTime = System.nanoTime();

			// make sure there is a tree!
			for (int item : holderList) {
				tester.add(item);
			}

			// time the empty loop
			for (long i = 0; i < TIMES_TO_LOOP; i++) {
				for (int j = 0; j < n; j++) {
				}
			}
			long stopTime = System.nanoTime();

			long startContains = System.nanoTime();
			for (int j = 0; j < TIMES_TO_LOOP; j++) {
				for (int item : holderList) {
					tester.contains(item);
				}
			}
			long midContains = System.nanoTime();

			// compute the time to add n elements to the tree
			double timeAdd = totalTime / (TIMES_TO_LOOP);

			// compute average time for contains on same tree
			double avgTimeContains = (((midContains - startContains) - (stopTime - midptTime)) / (TIMES_TO_LOOP ));
			double timeContains = avgTimeContains;

			System.out.println(n + "\t" + timeAdd + "\t" + timeContains); // print
																			// to
																			// console
		}
	}
	
	private static void timingJavaTreeForAddOrderNumberAndContains() {
		System.out.println("N Elements \t Add \t Contains");
		// let things stabilize
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) {

			for (int n = 500; n <= 1000000; n *= 2) {
				// time to add n elements to the binary search tree
				TreeSet<Integer> tester = new TreeSet<>();
				long totalTime = 0;
				long beginTime, endTime;
				for (int j = 0; j < TIMES_TO_LOOP; j++) {
					beginTime = System.nanoTime();
					for (int item = 0; item < n; item++) {
						tester.add(item);
					}
					endTime = System.nanoTime();
					tester.clear();
					totalTime = totalTime + (endTime - beginTime);
				}

				// make sure there is a tree to time for contains
				for (int item = 0; item < n; item++) {
					tester.add(item);
				}

				long midptTime = System.nanoTime();

				// time the empty loop
				for (long i = 0; i < TIMES_TO_LOOP; i++) {
					for (int j = 0; j < n; j++) {
					}
				}
				long stopTime = System.nanoTime();

				// Start time for contains
				long startContains = System.nanoTime();
				for (int j = 0; j < TIMES_TO_LOOP; j++) {
					for (int item = 0; item < n; item++) {
						tester.contains(item);
					}
				}
				long midContains = System.nanoTime();

				// compute the time to add n elements to the tree
				double timeAdd = totalTime / (TIMES_TO_LOOP);

				// compute average time for contains on same tree
				double avgTimeContains = ((midContains - startContains) - (stopTime - midptTime))
						/ (TIMES_TO_LOOP);
				double timeContains = avgTimeContains;

				System.out.println(n + "\t" + timeAdd + "\t" + timeContains); // print
																				// to
																				// console
			}
		}
	}

	private static void timingJavaTreeForAddAndContainsForRandomNumber() {
		System.out.println("N Elements \t Add \t Contains");
		// let things stabilize
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000)
			;

		for (int n = 500; n <= 1000000; n *= 2) {
			// time the routine
			ArrayList<Integer> holderList = new ArrayList<Integer>();
			Random random = new Random(SEED);
			for (int i = 0; i < n; i++) {
				int num = random.nextInt(n);
				if (!holderList.contains(num)) {
					holderList.add(num);
				}
			}

			// time to add n elements to the binary search tree
			TreeSet<Integer> tester = new TreeSet<>();
			long totalTime = 0;
			long beginTime, endTime;
			// startTime = System.nanoTime();
			for (int j = 0; j < TIMES_TO_LOOP; j++) {
				beginTime = System.nanoTime();
				for (int item : holderList) {
					tester.add(item);
				}
				endTime = System.nanoTime();
				tester.clear();
				totalTime = totalTime + (endTime - beginTime);
			}
			long midptTime = System.nanoTime();

			// make sure there is a tree!
			for (int item : holderList) {
				tester.add(item);
			}

			// time the empty loop
			for (long i = 0; i < TIMES_TO_LOOP; i++) {
				for (int j = 0; j < n; j++) {
				}
			}
			long stopTime = System.nanoTime();

			long startContains = System.nanoTime();
			for (int j = 0; j < TIMES_TO_LOOP; j++) {
				for (int item : holderList) {
					tester.contains(item);
				}
			}
			long midContains = System.nanoTime();

			// compute the time to add n elements to the tree
			double timeAdd = totalTime / (TIMES_TO_LOOP);

			// compute average time for contains on same tree
			double avgTimeContains = (((midContains - startContains) - (stopTime - midptTime)) / (TIMES_TO_LOOP));
			double timeContains = avgTimeContains;

			System.out.println(n + "\t" + timeAdd + "\t" + timeContains); // print
																			// to
																			// console
		}
	}
}
