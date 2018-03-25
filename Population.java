package trabalho;
public class Population {
    Tour[] _tours;

    //Construct a population
    public Population(int populationSize, boolean initialise) {
        _tours = new Tour[populationSize];
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }
    
    //Saves a tour
    public void saveTour(int index, Tour tour) {
        _tours[index] = tour;
    }
    
    //Gets a tour from population
    public Tour getTour(int index) {
        return _tours[index];
    }

    //Gets the best tour in the population
    public Tour getFittest() {
        Tour fittest = getTour(0);
        for(int i = 1; i < populationSize(); i++) {
            if(fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }

    //Gets population size
    public int populationSize() {
    	return _tours.length;
    }
}
