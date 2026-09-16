package parallelprocessing.parkinglot;

import com.parking.modelo.Aparcamiento;

public class EntraceJob extends Thread {
	
	private Aparcamiento parking;
	
	//Initialize the parking object through the constructor
	public EntraceJob(Aparcamiento parking) {
		this.parking = parking;
	}

	@Override
	public void run() {
		System.out.println("This is a parallel job");
		System.out.println(parking);
	}
	

}
