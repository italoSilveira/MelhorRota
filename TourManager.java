package trabalho;
import java.util.ArrayList;
/*
 * Classe que guarda todas as cidades destino de uma rota (tour)
 * */
public class TourManager {
	//Uma lista que contém todas as cidades que o mercador terá de visitar
    private static ArrayList<City> destinationCities = new ArrayList<City>();

    //Adiciona uma cidade à lista de cidades
    public static void addCity(City city) {
        destinationCities.add(city);
    }
    
    //Retorna a cidade que se encontra na posição 'index' da lista de cidades
    public static City getCity(int index){
        return destinationCities.get(index);
    }
    
    //Retorna o número de cidades da lista
    public static int numberOfCities(){
        return destinationCities.size();
    }
}
