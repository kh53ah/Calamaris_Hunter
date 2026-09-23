import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CalamarisHunter {

    //Targeted Canteens
    private static final Map<String, String> MENSA_URLS = new LinkedHashMap<>() {{
        put("Hubland Nord", "https://www.swerk-wue.de/wuerzburg/essen-trinken/mensen-speiseplaene/mensateria-campus-hubland-nord-wuerzburg/menu");
        put("Mensa Studentenhaus", "https://www.swerk-wue.de/wuerzburg/essen-trinken/mensen-speiseplaene/mensa-am-studentenhaus-wuerzburg/menu");
    }};

    private static final List<String> TARGET_KEYWORDS = List.of("Calamari"); //Keywords

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient(); //client

        for (Map.Entry<String, String> entry : MENSA_URLS.entrySet()) {
            String mensaName = entry.getKey();
            String url = entry.getValue();


            HttpRequest request = HttpRequest.newBuilder() //request
                    .uri(URI.create(url))
                    .header("User-Agent", "Mozilla/5.0")
                    .GET()
                    .build();

            try {
                HttpResponse<String> response = client.send( //respond
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

                if (response.statusCode() == 200) { //If there is a response
                    String pageContent = response.body().toLowerCase();
                    boolean anyMatchFound = false;

                    for (String keyword : TARGET_KEYWORDS) {
                        if (pageContent.contains(keyword.toLowerCase())) {
                            System.out.println("  -> FOUND: \"" + keyword + "\" at " + mensaName + "!");
                            anyMatchFound = true;
                        }
                    }

                    if (!anyMatchFound) {
                        System.out.println("  -> Not found.");
                    }
                    }
            } catch (IOException | InterruptedException e) {
                System.err.println("  -> Error fetching data: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}