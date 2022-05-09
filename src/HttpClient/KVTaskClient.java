package HttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KVTaskClient {
    private String API_KEY;
    private int port = 8079;
    private final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) throws IOException, InterruptedException {
        KVTaskClient client = new KVTaskClient("http://localhost:8078/register");
        String json = "\"1,NewTask,Description,NEW,2021-03-01 13:00,PT40M\"";
        client.put("1", json);

    }

    public KVTaskClient(String URL) throws IOException, InterruptedException {
        URI uri = URI.create(URL);
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, handler);
        API_KEY = response.body();
        System.out.println("Body of response " + API_KEY);
    }

    public void put(String key, String json) throws IOException, InterruptedException {
        String URL = "http://localhost:" + this.port + "/save/" + key + "?API_KEY=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(URL))
                .build();
        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, handler);
        System.out.println("Status " + response.statusCode());
    }

    public String load(String key) throws IOException, InterruptedException {
        String URL = "http://localhost:" + this.port + "/load/" + key + "?API_KEY=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();
        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, handler);
        return response.body();
    }


}
