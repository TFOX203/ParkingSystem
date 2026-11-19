package recursivealgorithms;

public class RecursiveRepeating {
   public static void main(String[] args) {
       //First method of doing a repetitive job
       System.out.println("repeat job");
       System.out.println("repeat job");
       System.out.println("repeat job");
       System.out.println("repeat job");
       //Second method
       for (int i = 1; i < 5; i++) {
           System.out.println("repeat job " + i);
       }
       //Third way of doing the same
       repeatJob(1);
   }

   private static void repeatJob(int i) {
       if (i >= 5) {          // caso base: misma condición de salida que el for
           return;
       }
       System.out.println("repeat job " + i);
       repeatJob(i + 1);      // llamada recursiva: equivale al i++
   }
}