import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public enum API {

    IMDB_TOP_MOVIES ("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json",new ExtratorConteudoIMDB()),
    IMDB_TOP_SERIES ("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json",new ExtratorConteudoIMDB()),
    IMDB_POPULAR_MOVIES ("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json",new ExtratorConteudoIMDB()),
    IMDB_POPULAR_SERIES ("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json",new ExtratorConteudoIMDB()),
    NASA_APOD ("https://api.nasa.gov/planetary/apod?api_key=pYaOOfYEEU9iPAsJ5mcnBhnpk8vITYFyWKzsdHo7&count=5", new ExtratorConteudoNasa());

    private String url;
    private ExtratorConteudo extrator;

    API(String url, ExtratorConteudo extrator){
        this.url = url;
        this.extrator = extrator;
    }

    public String getUrl() {
        return url; 
    }

    public ExtratorConteudo getExtrator() {
        return extrator; 
    }

    public String TextoFigurinha (Double classificacaoConteudo, String titulo) {
    
        String textoFigurinha;//Declara o texto da figurinha
    
       if (this == API.NASA_APOD) {    
             textoFigurinha = titulo;        
       } else {    
            if (classificacaoConteudo >= 8.0) {                
                textoFigurinha = "BRABO";//texto da figurinha se o filme for bom    
            } else {        
                textoFigurinha = "HMMMMMMM";//texto da figurinha se o filme for ruim    
            }            
       }
        return textoFigurinha;  
    }

    public InputStream imagemAvaliacao (Double classificacaoConteudo, API api) throws MalformedURLException, IOException{

        InputStream imagemAvaliacao;//Declara inputstram da imagem de sobreposição

        if (api == API.NASA_APOD) {
            imagemAvaliacao = new FileInputStream("nasa-logo.png");//imagem de sobreposição aprovado
            } else {
                if (classificacaoConteudo >= 8.0) {            
                     imagemAvaliacao = new URL("https://cdn-icons-png.flaticon.com/512/2722/2722007.png").openStream();
                 } 
                 else {
                     imagemAvaliacao = new URL("https://png.pngtree.com/png-vector/20211016/ourmid/pngtree-rejected-icon-design-rounded-shape-png-image_3988333.png").openStream();//imagem de sobreposição reprovado
                 }             
        }
        return imagemAvaliacao;
    }

}
