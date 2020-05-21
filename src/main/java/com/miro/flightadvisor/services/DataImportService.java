package com.miro.flightadvisor.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DataImportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataImportService.class);

    public void processFile(Path filePath) throws IOException {
        try (
                Stream<String> lines = Files.lines(filePath, Charset.defaultCharset());
        ) {
            lines.forEachOrdered(s -> {
                String[] values = s.split(",");
                List<String> list = Arrays.asList(values);
                //1. attach @Async to method
//                2. check if city exists/load city by name
//                3. if so, save airport/route to database/with relation?
                if(list.size() > 1){
                    System.out.println(list.get(2));
                }

            });
        } catch (IOException e) {
            throw new IOException(e.getMessage(), e);
        }
    }
}
