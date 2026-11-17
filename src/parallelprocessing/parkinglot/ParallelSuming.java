package parallelprocessing.parkinglot;

import java.awt.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelSuming {
	public static void main(String[] args) {
		
		//Define variables
		ArrayList<Integer> nums = new ArrayList<>(1000000);
		//int[] array = new int[1000];
		List<Integer> nums2 = new ArrayList<>(1000000);
		
		ExecutorService executorService =
				Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}

}
