package com.miro.flightadvisor.controllers;

import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.services.CityService;
import com.miro.flightadvisor.services.DataImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    @Value("${root.path}")
    private String rootPath;


    private final CityService cityService;
    private final DataImportService dataImportService;

    public AdminController(CityService cityService, DataImportService dataImportService) {
        this.cityService = cityService;
        this.dataImportService = dataImportService;
    }

    @PostMapping("/cities")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<City> addCity(@RequestBody @Valid City city) {
        cityService.addCity(city);
        return ResponseEntity.ok(city);
    }

    @PostMapping(value = "/admin/import")
    public @ResponseBody void handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        LOGGER.info("Attempting to read uploaded file");
        if (!file.isEmpty()) {
            Path rootPath = Paths.get(this.rootPath);
            Path filePath = Paths.get(rootPath + file.getOriginalFilename());
            if (!Files.exists(rootPath)) {
                Files.createDirectory(rootPath);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            } else {
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            dataImportService.processFile(filePath);
            LOGGER.info("Finish reading uploaded file");
        }
    }
}
