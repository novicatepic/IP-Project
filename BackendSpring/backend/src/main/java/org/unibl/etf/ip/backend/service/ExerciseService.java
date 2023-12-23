package org.unibl.etf.ip.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

@Service
public class ExerciseService {

    public void processApiResponse() throws Exception {
        URL url = new URL("https://api.api-ninjas.com/v1/exercises?muscle=biceps");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("x-api-key", "UnTW9ey5rROzstJ6IJ3fdQ==0ZZMbTXqO0KJatwX");
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseStream);
        System.out.println(root.path("fact").asText());

    }

}
