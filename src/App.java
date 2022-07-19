import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    

    public static void main(String[] args) throws Exception {
        
        // Making a HTTP connection we will get the "Top 250 Movies" on IMDb

        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI adress = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(adress).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        

        // Now we already have the data, but we need to map and parse them
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes= parser.parse(body);
      

        // We already maped the data, so now is the time to show the result in the way we want
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[1m\u001b[4mFilme:\u001b[m " + filme.get("rank") + ". " + filme.get("title"));
            System.out.println("\u001b[1m\u001b[4mPoster:\u001b[m " + filme.get("image"));

            String stars = "\u001b[38;5;88m\u001b[48;5;88m";
            int somaestrelas;
            
            for (somaestrelas = 0;somaestrelas < Double.valueOf(filme.get("imDbRating")).intValue();somaestrelas++) {
                stars += "\u2B50";
            }

            
            System.out.println("\u001b[1m\u001b[4mNota:\u001b[m " + filme.get("imDbRating") + " " + stars +" \u001b[m ");
            System.out.println("\u001b[1m\u001b[4mVotos:\u001b[m " + filme.get("imDbRatingCount"));
            System.out.println();
            
        }




    }
}
