import java.util.Scanner;

public class EscolhaAPI {

    public API escolhaAPI(){
        
        String userChoice;//Declara variavel, escolha do usuario
        API api = null;
       
        //usuario escolhe a API desejada entre as opções a seguir
        
        try (
        var Lista = new Scanner(System.in)) {
            System.out.println("\n\u001b[95m\u001b[107;1m       Escolha a lista a seguir:      ");
            System.out.println("\n\u001b[95m\u001b[107;1m\u001b[1m 1 \u001b[m- \u001b[3mTop filmes \u001b[m");
            System.out.println("\u001b[1m\u001b[95m\u001b[107;1m 2 \u001b[m- \u001b[3mTop séries \u001b[m");
            System.out.println("\u001b[1m\u001b[95m\u001b[107;1m 3 \u001b[m- \u001b[3mFilmes populares\u001b[m");
            System.out.println("\u001b[1m\u001b[95m\u001b[107;1m 4 \u001b[m- \u001b[3mSéries populares\u001b[m");
            System.out.println("\u001b[1m\u001b[95m\u001b[107;1m 5 \u001b[m- \u001b[3mImagens da NASA\u001b[m");
            userChoice = Lista.nextLine();// user input
            System.out.println("\n\u001b[90m\u001b[106;1m       Lista escolhida: " + userChoice + "     \u001b[m\n");
        }
        switch (userChoice) {
            case "1":
                api = API.IMDB_TOP_MOVIES;
                break;           
            case "2":               
                api = API.IMDB_TOP_SERIES;
                break;            
            case "3":
                api = API.IMDB_POPULAR_MOVIES;
                break;
            case "4":
                api = API.IMDB_POPULAR_SERIES;
                break;
            case "5":
                api = API.NASA_APOD;
                break;
            default:
                System.out.println("\u001b[37m\u001b[41;1m\u001b[1m       Opção invalida          \u001b[m\n");
                break;
        }
        return api;       

    }
    
}
