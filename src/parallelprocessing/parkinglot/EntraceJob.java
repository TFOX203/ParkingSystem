package parallelprocessing.parkinglot;

import com.parking.modelo.Aparcamiento;

public class EntraceJob extends Thread {
	
	private Aparcamiento parking;
	
	//Initialize the parking object through the constructor
	public EntraceJob(Aparcamiento parking, String name) {
		super(name);
		this.parking = parking;
	}

	@Override
	public void run() {
		System.out.println("Start the job with thread " + Thread.currentThread().getName());
		System.out.println(parking + " object adress from thread " + Thread.currentThread().getName());
		System.out.println("End of the job with thread " + Thread.currentThread().getName());
		parking.entrarCoche();
	}
	

}
