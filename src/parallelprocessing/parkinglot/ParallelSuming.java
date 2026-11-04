package parallelprocessing.parkinglot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ParallelSuming {
	
	public static void main(String[] args) {
		//Create a thread pool object, the thread in the pool are reusable
		ExecutorService executor = 
				Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		System.out.println("The number of processors is: " + Runtime.getRuntime().availableProcessors());
		//Define a runnable task for a thread
		Runnable task = () -> {
			System.out.println("Thread name is: " + Thread.currentThread().getName());
		};
		//Submit task to the thread pool
		executor.submit(task);
		executor.submit(task);
		executor.submit(task);
		executor.submit(task);
		for (int i = 0; i < 20; i++) {
			executor.submit(task);
		}
	}

}