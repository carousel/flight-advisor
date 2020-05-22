package com.miro.flightadvisor.controllers;

import com.miro.flightadvisor.beans.CityBean;
import com.miro.flightadvisor.entities.Airport;
import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.repositories.AirportRepository;
import com.miro.flightadvisor.services.AirportService;
import com.miro.flightadvisor.services.CityService;
import com.miro.flightadvisor.services.DataImportService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    @Value("${root.path}")
    private String rootPath;


    private final CityService cityService;
    private final AirportService airportService;
    private final DataImportService dataImportService;

    public AdminController(CityService cityService, DataImportService dataImportService, AirportService airportService) {
        this.cityService = cityService;
        this.dataImportService = dataImportService;
        this.airportService = airportService;
    }

    @PostMapping("/cities")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CityBean> addCity(@RequestBody @Valid CityBean cityBean) {
        cityService.addCity(cityBean);
        return ResponseEntity.ok(cityBean);
    }

    @GetMapping("/airports")
    public Optional<List<Airport>> getAirports() {
        return airportService.allAirports();
    }

    @PostMapping(value = "/admin/import")
    public @ResponseBody
    void handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            if (FilenameUtils.getExtension(file.getOriginalFilename()).equals("txt")) {
                Path filePath = Paths.get(Paths.get(this.rootPath) + "/" + file.getOriginalFilename());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                this.dataImportService.processFile(filePath);
            }
        }
    }

}
