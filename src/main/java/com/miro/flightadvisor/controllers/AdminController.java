package com.miro.flightadvisor.controllers;

import com.miro.flightadvisor.beans.CityBean;
import com.miro.flightadvisor.entities.Airport;
import com.miro.flightadvisor.repositories.AirportRepository;
import com.miro.flightadvisor.services.AirportService;
import com.miro.flightadvisor.services.CityService;
import com.miro.flightadvisor.services.DataImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Api(description = "Api for main admin activities (import airports/routes, add cities)")
@RestController
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    @Value("${root.path}")
    private String rootPath;


    private final CityService cityService;
    private final AirportRepository airportRepository;
    private final AirportService airportService;
    private final DataImportService dataImportService;

    public AdminController(CityService cityService, AirportRepository airportRepository, DataImportService dataImportService, AirportService airportService) {
        this.cityService = cityService;
        this.airportRepository = airportRepository;
        this.dataImportService = dataImportService;
        this.airportService = airportService;
    }

    @PostMapping("/cities")
    @ApiOperation(value = "add new city")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CityBean> addCity(@RequestBody @Valid CityBean cityBean) {
        LOGGER.debug(String.format("Adding new city %s", LocalDate.now()));
        cityService.addCity(cityBean);
        return ResponseEntity.ok(cityBean);
    }

    @GetMapping("/airports")
    @ApiOperation(value = "return all uploaded airports")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Server error")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Optional<List<Airport>> getAirports() {
        return Optional.of(airportRepository.findAll());
    }

    @PostMapping(value = "/admin/import")
    @ApiOperation(value = "Entry point for importing airports or routes data from file")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Server error")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        LOGGER.debug(String.format("Importing %s data on %s", file.getOriginalFilename(), LocalDate.now()));
        try (InputStream inputStream = file.getInputStream()) {
            if (FilenameUtils.getExtension(file.getOriginalFilename()).equals("txt")) {
                Path filePath = Paths.get(Paths.get(this.rootPath) + "/" + file.getOriginalFilename());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                this.dataImportService.processFile(filePath, file.getOriginalFilename());
                Files.delete(filePath);
            } else {
                return ResponseEntity.ok("File format not allowed");
            }
        } catch (IOException e) {
            throw new IOException(String.format("File %s can't be uploaded", file.getOriginalFilename()));
        }
        if (file.getOriginalFilename().contains("airports")) {
            return ResponseEntity.ok("Airports uploaded!");
        }
        return ResponseEntity.ok("Routes uploaded!");

    }

}
