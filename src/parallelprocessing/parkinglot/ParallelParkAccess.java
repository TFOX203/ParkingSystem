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
		
		Thread t1 = new EntraceJob(parking1);
		Thread t2 = new Thread();
		
		t1.start();
		t2.start();
		
	}

}


