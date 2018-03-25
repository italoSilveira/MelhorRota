package trabalho;

import javax.swing.JOptionPane;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class Index {

	public static void main(String[] args) {
		int opcao;
		
		do{
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Escolha opção: \n1 - Calcular melhor caminho."
					+ " \n2- Sair")); // Verifica o que o usuário que fazer
			if(opcao == 1){
				analisarCidades();
				/*String[] endereco = new String[4];
				endereco[0] = "Professor Mario Werneck - 1685 - Buritis, Belo Horizonte - MG, 30455-610";
				endereco[1] = "R. da Manha, 188 - Miramar (Barreiro), Belo Horizonte - MG, 30644-150";
				endereco[2] = "Minas Shopping, Av. Cristiano Machado, 4000 - Uniao, Belo Horizonte - MG, 31160-900";
				endereco[3] = "Macacos, Esmeraldas - MG";
				gerarGrafo(endereco); */
				
			}else if(opcao != 1 && opcao != 2){ //Verifica se é uma opção válida
				JOptionPane.showMessageDialog(null, "Opção inválida!");
			}
		}while(opcao != 2);

	}
	
	public static String removeAcentos(String palavra) {
		// Função para remover acento dos endereços
        palavra = palavra.replaceAll("[áàãâä]","a");  
        palavra = palavra.replaceAll("[éèê]","e");
        palavra = palavra.replaceAll("[íìî]","i");
        palavra = palavra.replaceAll("[óòõô]","o");
        palavra = palavra.replaceAll("[úùû]","u");
        return palavra;  
    }
	
	public static void analisarCidades(){
		String[] endereco = null; // Array onde iremos armazenar os endereços
		int qtItens; // Variável para sabermos quantas cidades iremos manipular
		
		qtItens = Integer.parseInt(JOptionPane.showInputDialog("Quantas cidades vamos analisar? "));
		endereco = new String[qtItens];
		//For para pegar os dados das cidades
		for(int i = 0; i < qtItens; i++){
			endereco[i] = JOptionPane.showInputDialog((i + 1)+"ª cidade\nDigite o endereço completo: ");
			endereco[i] = removeAcentos(endereco[i]); // Remove os acentos, pois a api não reconhece o endereço se tiver
			//JOptionPane.showMessageDialog(null, endereco[i]);
		}
		
		//Chama a função que irá analisar as cidades
		gerarGrafo(endereco);
	}
	
	public static void gerarGrafo(String[] endereco){
		Graphic.createWindow();
		
		String[] alfabeto =    {"A","B","C","D","E","F","G","H","I","J","K","L","M",
				"N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; // Array com todas as letras do alfabeto
		String[] cidade = new String[endereco.length]; // Criamos um array para armazenar as cidades com nomes das letras
		
		//Vamos varrer as distância entre todas as cidades
		String ruaTeste = "Professor Mario Werneck - 1685 - Buritis, Belo Horizonte - MG, 30455-610";
		for(int i = 0; i <= (endereco.length - 1); i++){ // Varre da primeira até a penúltima cidade
				String[] resultado = calcular(endereco[i],ruaTeste); // Cacula a cidade entre as duas cidades
				int x = (int) Math.abs(Double.parseDouble(resultado[0]));
				int y = (int) Math.abs(Double.parseDouble(resultado[1]));
				TourManager.addCity(new City(alfabeto[i], x+i*10, y+(i -2)*20));
		}
		
		//Initialize population
        Population pop = new Population(20000, true);
        System.out.println("Distância inicial: " + pop.getFittest().getDistance());

        //Evolve population for x generations
        for (int i = 0; i < 300; i++) {
            pop = GA.evolvePopulation(pop);
            Graphic.setGeneration(i+1);
        }

        // Print final results
        System.out.println("Distância final: " + pop.getFittest().getDistance());
        System.out.println("Solução:");
        System.out.println(pop.getFittest()); 
		
	}
	
	public static String[] calcular(String origem, String destino) {
        URL url;
        try {
            url = new URL(
                    "http://maps.google.es/maps/api/directions/xml?origin=" + origem + "&destination=" + destino
                            + "&sensor=false"); // Cria a url para conseguir os dados entre as cidades
            Document document = getDocumento(url); // Pega o resultado e armazena
 
            return analisaXml(document); //Chama a função que irá analisar a resposta
        } catch (MalformedURLException | DocumentException e) {
            e.printStackTrace(); //Retorna o erro da api caso aconteça
        }
        return null;
    }
	
	@SuppressWarnings("rawtypes")
	public static String[] analisaXml(Document document) {
        String[] resultado = new String[2];
        
        List list = document
                .selectNodes("//DirectionsResponse/route/leg/start_location/lat");
        Element element = (Element) list.get(list.size() - 1);
        resultado[0] = element.getText();
        
        list = document
                .selectNodes("//DirectionsResponse/route/leg/start_location/lng");
        element = (Element) list.get(list.size() - 1);
        resultado[1] = element.getText();
        
        return resultado;
    }
	
	public static Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }
}
