package code;
/*
* TSP_GA.java
* Create a tour and evolve a solution
*/



public class Knapsack_GA {

    public static void main(String[] args) {
    	long startTime = System.currentTimeMillis();
    	Parameters Param = new Parameters();
		Param.CapaciteDuSac=200;
		Param.nbObjects=10;
		Objects[] L = new Objects[Param.nbObjects];
		
		for (int i = 0; i < Param.nbObjects; i++) {
			L[i]=new Objects();
		}
		
		//Values and weights from http://rosettacode.org/wiki/Knapsack_problem/0-1
		//
		
		
		L[0].setWeight(9);
		L[0].setValue(150);
		L[1].setWeight(13);
		L[1].setValue(35);
		L[2].setWeight(100);
		L[2].setValue(150);
		L[3].setWeight(50);
		L[3].setValue(160);
		L[4].setWeight(15);
		L[4].setValue(60);
		L[5].setWeight(68);
		L[5].setValue(40);
		L[6].setWeight(27);
		L[6].setValue(30);
		L[7].setWeight(39);
		L[7].setValue(10);
		L[8].setWeight(23);
		L[8].setValue(70);
		L[9].setWeight(52);
		L[9].setValue(30);
		
		
														
														
														
		
		Parameters.listObjects=L;
		for (Objects objects : Param.listObjects) {
			objects.Display();
		}
        
        // Initialize population
        
        Population pop = new Population(50, true);
       

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 100; i++) {
            pop = GA.evolvePopulation(pop);
        }

        // Print final results
        System.out.println("Finished");
        long endTime = System.currentTimeMillis();

    	System.out.println("That took " + (endTime - startTime) + " milliseconds");
        System.out.println("Solution:");
        pop.getFittest().Display();
    }
}