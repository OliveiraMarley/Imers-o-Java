import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa implements ExtratorConteudo{

    public List <Conteudo> extraiConteudos(String json){

        var Parser = new JsonParser();//Usa a aplicação parser
        List<Map<String, String>> ListaDeAtributos = Parser.Parse(json);//Cria a lista do JSON com parser  
        
        return ListaDeAtributos.stream()
            .map( atribudos  ->  new Conteudo(atribudos.get("title"), atribudos.get("url"), "10"))
            .toList();
    }
    
}
