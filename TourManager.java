package trabalho;
import java.util.ArrayList;
/*
 * Classe que guarda todas as cidades destino de uma rota (tour)
 * */
public class TourManager {
	//Uma lista que cont�m todas as cidades que o mercador ter� de visitar
    private static ArrayList<City> destinationCities = new ArrayList<City>();

    //Adiciona uma cidade � lista de cidades
    public static void addCity(City city) {
        destinationCities.add(city);
    }
    
    //Retorna a cidade que se encontra na posi��o 'index' da lista de cidades
    public static City getCity(int index){
        return destinationCities.get(index);
    }
    
    //Retorna o n�mero de cidades da lista
    public static int numberOfCities(){
        return destinationCities.size();
    }
}
