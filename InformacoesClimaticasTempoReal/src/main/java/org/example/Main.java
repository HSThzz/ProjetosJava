package org.example;
import java.net.URI; // representa o endereço da web
import java.net.URLEncoder; // codifica strings
import java.net.http.HttpClient; // envia e recebe dados via http
import java.net.http.HttpRequest; //solicitaçao http
import java.net.http.HttpResponse; //resposta http
import java.nio.charset.StandardCharsets; //define padroes de codificaçao
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import org.json.JSONObject;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static void imprimirDadosCLimaticos(String dadosClimaticos){
        JSONObject dadosJson = new JSONObject(dadosClimaticos);
        JSONObject informaçoesMetereologicas = dadosJson.getJSONObject("current");

        String cidade = dadosJson.getJSONObject("location").getString("name");
        String pais = dadosJson.getJSONObject("location").getString("country");

        String condicaoTempo = informaçoesMetereologicas.getJSONObject("condition").getString("text");
        int umidade = informaçoesMetereologicas.getInt("humidity");
        float velocidadeVento = informaçoesMetereologicas.getFloat("wind_kph");
        float pressaoAtmosferica = informaçoesMetereologicas.getFloat("pressure_mb");
        float sensTermica = informaçoesMetereologicas.getFloat("feelslike_c");
        float tempAtual = informaçoesMetereologicas.getFloat("temp_c");

        String datahora = informaçoesMetereologicas.getString("last_updated");
        System.out.println("Informaçoes metereologicas para "+cidade + ", " + pais);
        System.out.println("Data e hora: "+datahora);
        System.out.println("Temperatura atual: "+tempAtual);
        System.out.println("Sensação térmica: "+sensTermica);
        System.out.println("Condição do tempo: "+condicaoTempo);
        System.out.println("Umidade: "+umidade);
        System.out.println("Velocidade do vento: "+velocidadeVento);
        System.out.println("Pressão atmosférica: "+pressaoAtmosferica);


    }

    private static String getDadosClimaticos(String cidade) throws Exception{
        // Lê a chave de acesso da API de um arquivo .txt
        String apiKey = Files.readString(Paths.get("src/main/java/org/example/api-key.txt")).trim();
        // Codifica o nome da cidade para formato compatível com URLs
        String formataCidade = URLEncoder.encode(cidade, StandardCharsets.UTF_8);//formatando para um formato universal
        // Monta a URL da requisição da API, com chave e cidade
        String apiUrl = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + formataCidade;

        // Cria a requisição HTTP usando a URL
        HttpRequest request = HttpRequest.newBuilder()//solicitaçao http
                .uri(URI.create(apiUrl)) //destino da requisiçao
                .build();

        // Cria um cliente HTTP e envia a requisição get, recebendo a resposta como texto
        HttpClient client = HttpClient.newHttpClient();
        //                              envio da requisiçao    +    como a resposta deve ser recebida
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Retorna o conteúdo da resposta
        return response.body();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome da cidade: ");
        String cidade = scanner.nextLine();

        try{

            String dadosClimaticos = getDadosClimaticos(cidade);//retorna um json

            if(dadosClimaticos.contains("\"code\":1006")){ //esse parametro quer dizer "code":1006, \" representa "
                System.out.println("Localização nao encontrada");
            }else{
                imprimirDadosCLimaticos(dadosClimaticos);
            }

    } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}