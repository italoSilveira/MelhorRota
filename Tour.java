package trabalho;
import java.util.ArrayList;
import java.util.Collections;
public class Tour {
    private ArrayList<City> _tour = new ArrayList<City>(); //Holds our tour of cities
    private double _fitness = 0;
    private double _distance = 0;
    
    //CONSTRUCTOR
    public Tour(){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            _tour.add(null);
        }
    }
    
    public Tour(ArrayList<City> tour){
        _tour = tour;
    }

    //METHODS
    //Create tour
    public void generateIndividual() {
        for(int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
        	setCity(cityIndex, TourManager.getCity(cityIndex));
        }
        Collections.shuffle(_tour); //Randomly reorder the tour
    }
    
    //Get city from specific index on a tour
    public City getCity(int index){
        return _tour.get(index);
    }

    //Sets a city in a certain position within a tour
    public void setCity(int position, City city) {
        _tour.set(position, city);
        //When tour changes, fitness and distance are reseted
        _fitness = 0;
        _distance = 0;
    }
    
    //Calculate tour fitness
    public double getFitness(){
        if (_fitness == 0) {
        	_fitness = 1/(double)getDistance();
        }
        return _fitness;
    }
    
    // Gets the total distance of the tour
    public double getDistance(){
        if (_distance == 0){
            double tourDistance = 0;
            for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
                City fromCity = getCity(cityIndex);
                City destinationCity;
                //Check we're not on our tour's last city, if we are set our 
                //tour's final destination city to our starting city
                if(cityIndex+1 < tourSize()){
                    destinationCity = getCity(cityIndex+1);
                }else{
                    destinationCity = getCity(0);
                }
                // Get the distance between the two cities
                tourDistance += fromCity.distanceTo(destinationCity);
            }
            _distance = tourDistance;
        }
        return _distance;
    }

    // Get number of cities on the tour
    public int tourSize() {
        return _tour.size();
    }
    
    // Check if the tour contains a city
    public boolean containsCity(City city){
        return _tour.contains(city);
    }
    
    public boolean equals(Object object){
    	Tour tour2;
    	if(object instanceof Tour){
    		tour2 = (Tour) object;
    	}else{
    		return false;
    	}
    	if(tourSize() == tour2.tourSize()){
    		City city1, city2;
    		for(int cityIndex = 0; cityIndex < tourSize(); cityIndex++){
    			city1 = getCity(cityIndex);
    			city2 = tour2.getCity(cityIndex);
    			if(!city1.equals(city2)){
    				return false;
    			}
    		}
    		return true;
    	}
    	return false;
    }
    
    @Override
    public String toString() {
        String geneString = new String();
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i)+" -> ";
        }
        return geneString;
    }
}
