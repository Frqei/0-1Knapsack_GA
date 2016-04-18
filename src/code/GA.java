package code;
/*
* GA.java
* Manages algorithms for evolving population
*/


public class GA {

    /* GA parameters */
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    // Evolves a population over one generation
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveSac(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Select parents
            knapsack parent1 = tournamentSelection(pop);
            knapsack parent2 = tournamentSelection(pop);
            // Crossover parents
            knapsack child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.saveSac(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getSac(i));
        }

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static knapsack crossover(knapsack parent1, knapsack parent2) {
        // Create new child tour
        knapsack child = new knapsack();

        // Get start and end sub tour positions for parent1's tour
        int crossPos = (int) (Math.random() * parent1.getObjects().length);
        
        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < child.getObjects().length; i++) {
        	if(i<crossPos){
        		child.processWeight();
        		if(child.getWeight()+Parameters.listObjects[i].getWeight()<=child.getCapacity()){
        			child.setoneObject(i, parent2.getoneObject(i));
        			child.processWeight();
        			}
        		
        	}else{
        		child.processWeight();
        		if(child.getWeight()+Parameters.listObjects[i].getWeight()<=child.getCapacity()){
        			child.setoneObject(i, parent1.getoneObject(i));
        			child.processWeight();
        			}
        	}
        }
        
        
        
        
        child.processValue();
        return child;
    }

    // Mutate a tour using swap mutation
    private static void mutate(knapsack sac) {
        // Loop through tour cities
    	for(int i=0; i < sac.getObjects().length; i++){
            // Apply mutation rate
            if(Math.random() < mutationRate){
                // Get a second random position in the tour
                int mutationPos = (int) (sac.getObjects().length * Math.random());
                if(sac.getObjects()[mutationPos]){
                	sac.setoneObject(i, false);
                	sac.processValue();
                	sac.processWeight();
                }else{
                	if(sac.getWeight()+Parameters.listObjects[mutationPos].getWeight()<=sac.getCapacity()){
                	sac.setoneObject(i, true);
                	sac.processValue();
                	sac.processWeight();
                	}
                }
            }
        }
    }

    // Selects candidate tour for crossover
    private static knapsack tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveSac(i, pop.getSac(randomId));
        }
        // Get the fittest tour
        knapsack fittest = tournament.getFittest();
        return fittest;
    }
}