package co.edu.eci.proxy.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ActivePassiveService {

    // Read instance URLs from environment variables at startup
    private final String instance1Url;
    private final String instance2Url;

    public ActivePassiveService() {
        String env1 = System.getenv("MATH_SERVICE_1_URL");
        String env2 = System.getenv("MATH_SERVICE_2_URL");

        // Fallback to localhost for local development
        this.instance1Url = (env1 != null && !env1.isBlank()) ? env1 : "http://localhost:8081";
        this.instance2Url = (env2 != null && !env2.isBlank()) ? env2 : "http://localhost:8082";
    }

    public String callWithFailover(String path) throws Exception {
        try {
            return callInstance(instance1Url, path);
        } catch (Exception primaryFailed) {
            try {
                return callInstance(instance2Url, path);
            } catch (Exception secondaryFailed) {
                throw new Exception("Both math service instances are unavailable. " +
                        "Instance 1: " + instance1Url + " | Instance 2: " + instance2Url);
            }
        }
    }

    private String callInstance(String baseUrl, String path) throws Exception {
        String fullUrl = baseUrl + path;
        URL url = new URL(fullUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(3000);
        connection.setReadTimeout(5000);

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            throw new Exception("HTTP error from " + fullUrl + " - code: " + responseCode);
        }
    }

    public String getInstance1Url() {
        return instance1Url;
    }

    public String getInstance2Url() {
        return instance2Url;
    }
}
