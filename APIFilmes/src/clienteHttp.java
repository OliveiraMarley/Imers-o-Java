import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class clienteHttp {
    
    public String buscaDados(String url){

        try {

            URI endereco = URI.create(url);//Atribui a URL na URI(similar a URL, mas generica)
            var client = HttpClient.newHttpClient();//criar um novo http client e joga em uma variavel
            var request = HttpRequest.newBuilder(endereco).GET().build();//Realiza um request da URL, pega e cria as informações
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());//HttpResponse<String> = var; Acessa o site e joga o body na variavel response em string
            String body = response.body();// cria a variavel body com os dados do response
            System.out.println(body);// Printa os dados do body
            return body;        
               
        } catch (IOException | InterruptedException ex ) {

            throw new ClienteHttpException("Erro ao consultar a URL");
            
        }


    }

    
}
