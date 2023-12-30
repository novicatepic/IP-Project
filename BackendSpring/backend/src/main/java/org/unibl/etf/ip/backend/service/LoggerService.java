package org.unibl.etf.ip.backend.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.LogEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LoggerService {

    //private static final String PATH = "logging.log";

    @Value("${logging.directory}")
    private String PATH;

    public LogEntity getLogs() throws IOException {
        String logContent = readLogFile();
        return new LogEntity(logContent);
    }

    private String readLogFile() throws IOException {
        Path path = Paths.get(PATH);
        return String.join("\n", Files.readAllLines(path));
    }

}
