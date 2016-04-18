package code;

public class Population {
	knapsack[] sacs;

    // Construct a population
    public Population(int populationSize, boolean initialise) {
        sacs=new knapsack[populationSize];
            
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize(); i++) {
            	knapsack newSacs = new knapsack();
                newSacs.generateIndividual();
                saveSac(i, newSacs);
            }
        }
    }
    
    // Saves a tour
    public void saveSac(int index, knapsack sac) {
        sacs[index]=sac;
    }
    
    // Gets a tour from population
    public knapsack getSac(int index) {
    	return sacs[index];
    }

    // Gets the best tour in the population
    public knapsack getFittest() {
    	knapsack fittest = sacs[0];
    	 for (int i = 1; i < populationSize(); i++) {
             if (fittest.getFitness() <= getSac(i).getFitness()) {
                 fittest = getSac(i);
             }
         }
   
    	return fittest;
    }

    // Gets population size
    public int populationSize() {
    	return sacs.length;
    }
}

