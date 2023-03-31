import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        EscolhaAPI escolhaAPI = new EscolhaAPI();//Realiza chamada da classe de seleção de PI
        API api = escolhaAPI.escolhaAPI();//Pega a API escolhida

        String URL = api.getUrl();//("URL da API") 
        var http = new clienteHttp();//Realiza a chamada da classe de conexão
        String json = http.buscaDados(URL);//Pega os dados da API

        float contador = 0;//Declara e inicializa variavel contador
        var diretorio = new File("Stickers/");//Declara o diretorio onde as imagens serão criadas
        diretorio.mkdir();//Cria o diretorio caso não exista

        var geradorImage = new Stickers();//Realiza a chamada do app Stickers
        ExtratorConteudo extrator = api.getExtrator();//Usa o extrator de dados correspondente a API
        List<Conteudo> Conteudos = extrator.extraiConteudos(json);//Pega os dados tratados da API

        for (var Conteudo : Conteudos) {

            contador = 0;//Reset variavel
            String urlImage = Conteudo.urlImagem();//Pega aURL da imagem
            String titulo = Conteudo.titulo();//Pega o titulo da imagem
            String nomeArquivo = "Stickers/" + titulo.replace(":", "-") + ".png";//Cria o nome da imagem e trata
            double classificacao = Double.parseDouble(Conteudo.classificacao()); //Transforma a classificação em double

            String textoFigurinha = api.TextoFigurinha(classificacao, titulo);//Escolhe a frase da figurinha
            InputStream imagemAvaliacao = api.imagemAvaliacao(classificacao, api);//Escolhe imagem de sobreposição
         
            InputStream pathImage = new URL(urlImage).openStream();//pega a imagem da URL do poster
            geradorImage.cria(pathImage, nomeArquivo, textoFigurinha, imagemAvaliacao);//Cria a imagem 

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
