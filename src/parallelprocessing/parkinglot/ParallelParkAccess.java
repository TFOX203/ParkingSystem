package parallelprocessing.parkinglot;


import com.parking.modelo.Aparcamiento;
import com.parking.modelo.Empresa;

public class ParallelParkAccess {
	
	public static void main(String[] args) {
		
		Aparcamiento parking1 = new Aparcamiento("Madrid","Aparcamiento1",100);
		Aparcamiento parking2 = new Aparcamiento("Valencia","Aparcamiento2",200);
		Empresa Empresa = new Empresa("Sasha");
		Empresa.registrarAparcamiento(parking1);
		Empresa.registrarAparcamiento(parking2);
		
		int numOfThread = 40;
		Thread[] threads = new Thread[numOfThread];
		for (int i = 0; i < numOfThread; i++) {
			Thread t = new EntraceJob(parking1, "justo" + i);
			threads[i] = t;
			
		}
		Thread t1 = new EntraceJob(parking1,"justo");
		Thread t2 = new EntraceJob(parking1,"carlos");
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(parking1.getCochesAparcados());
		
	}

}


