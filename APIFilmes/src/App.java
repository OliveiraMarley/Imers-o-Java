import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        String opcao = null;//Declara e inicializa a variavel
        String userChoice;//Declara variavel
        try (//usuario escolhe a API desejada
        var Lista = new Scanner(System.in)) {
            System.out.println("\n\u001b[95m\u001b[107;1m       Escolha a lista a seguir:      \n\n\u001b[95m\u001b[107;1m\u001b[1m 1 \u001b[m- \u001b[3mTop filmes \u001b[m\n\u001b[1m\u001b[95m\u001b[107;1m 2 \u001b[m- \u001b[3mTop séries \u001b[m\n\u001b[1m\u001b[95m\u001b[107;1m 3 \u001b[m- \u001b[3mFilmes populares\u001b[m \n\u001b[1m\u001b[95m\u001b[107;1m 4 \u001b[m- \u001b[3mSéries populares\u001b[m");
            userChoice = Lista.nextLine();// user input
            System.out.println("\n\u001b[90m\u001b[106;1m       Lista escolhida: " + userChoice + "     \u001b[m\n");
        }
        switch (userChoice) {
            case "1":
            
                opcao = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
                break;
            
            case "2":
                
                opcao = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
                break;
            
            case "3":

                opcao = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
                break;

            case "4":

                opcao = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
                break;

            default:

                System.out.println("\u001b[37m\u001b[41;1m\u001b[1m       Opção invalida          \u001b[m\n");
                break;
        }       



        //Pegar os dados do IMDB:
        //Fazer uma conexão HTTP e buscar
        String URL = opcao;//("URL da API")
        URI endereco = URI.create(URL);//Atribui a URL na URI(similar a URL, mas generica)
        var client = HttpClient.newHttpClient();//criar um novo http client e joga em uma variavel
        var request = HttpRequest.newBuilder(endereco).GET().build();//Realiza um request da URL, pega e cria as informações
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());//HttpResponse<String> = var; Acessa o site e joga o body na variavel response em string
        String body = response.body();// cria a variavel body com os dados do response
        System.out.println(body);// Printa os dados do body

        //Extrair os dados que interessam (titulo, poster, classificação)
        
        var Parser = new JsonParser();//Usa a aplicação parser
        List<Map<String, String>> ListaDeFilmes = Parser.Parse(body);//Cria a lista do JSON com parser
        
        System.out.println(ListaDeFilmes.get(0));
        //Exibir e manipular os dados

        float contador = 0;//Declara e inicializa variavel

        var diretorio = new File("Stickers/");
        diretorio.mkdir();


        var geradorImage = new Stickers();
        for (Map<String,String> filme : ListaDeFilmes) {

            contador = 0;//Reset variavel
            String urlImage = filme.get("image");
            String titulo = filme.get("title");
            String nomeArquivo = "Stickers/" + titulo.replace(":", "-") + ".png";
            String textoFigurinha;
            InputStream imagemAvaliacao;

            double classificacao = Double.parseDouble(filme.get("imDbRating"));//Trata a variavel de classificação
            
            if (classificacao >= 8.0) {
                
                textoFigurinha = "BRABO";
                imagemAvaliacao = new URL("https://cdn-icons-png.flaticon.com/512/2722/2722007.png").openStream();

            } else {

                textoFigurinha = "HMMMMMMM";
                imagemAvaliacao = new URL("https://png.pngtree.com/png-vector/20211016/ourmid/pngtree-rejected-icon-design-rounded-shape-png-image_3988333.png").openStream();
            }
                     
            InputStream pathImage = new URL(urlImage).openStream();
            geradorImage.cria(pathImage, nomeArquivo, textoFigurinha, imagemAvaliacao);

            System.out.println("\n");//Pula uma linha para organização
            System.out.println("\u001b[1m\u001b[97m\u001b[104;1mTitulo:\u001b[m\u001b[3m " + titulo + "\u001b[m");//Pega o titulo
            System.out.println("\u001b[1m\u001b[97m\u001b[104;1mPoster:\u001b[m\u001b[3m " + urlImage + "\u001b[m");//Pega o poster
            System.out.println("\u001b[90m\u001b[103;1mClassificação: \u001b[3m" + classificacao + "\u001b[m");//Devolve a classificação
            while (contador <= classificacao) {//Desenha o emoji = classificação
                contador ++;
                System.out.print("💙️");
                
            }
                       
        }

    }
}
