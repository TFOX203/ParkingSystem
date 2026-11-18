package recursivealgorithms;

import java.util.Iterator;

public class RecursiveRepeating {
	//First method of doing a repetitive job
	public static void main(String[] args) {
		System.out.println("repeat job");
		System.out.println("repeat job");
		System.out.println("repeat job");
		System.out.println("repeat job");
		//second method
		for (int i = 1; i < 5; i++) {
			System.out.println("repeat job" + i);
			
		}
		//third way of doing the same
		repeatJob(1);
	}

	private static void repeatJob(int i) {
		System.out.println("repeat job" + i);
		
	}

}
