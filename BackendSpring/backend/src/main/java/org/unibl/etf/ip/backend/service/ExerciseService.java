package org.unibl.etf.ip.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.ExerciseAPI;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class ExerciseService {

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    public List<ExerciseAPI> processApiResponse() throws IOException {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("x-api-key", apiKey);
            connection.setRequestProperty("accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream responseStream = connection.getInputStream();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(responseStream);
                //System.out.println("HTTP request success with response code: " + responseCode);
                // Now you can access the JSON elements
                List<ExerciseAPI> exercises = mapper.readValue(
                        root.toString(),
                        new TypeReference<>() {}
                );
                return exercises;
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
            }
        return null;

    }

}
