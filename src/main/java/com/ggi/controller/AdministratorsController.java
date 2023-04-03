package com.ggi.controller;

import com.ggi.domain.model.Administrator;
import com.ggi.domain.service.AdministratorService;
import com.ggi.resource.AdministratorResource;
import com.ggi.resource.SaveAdministratorResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AdministratorsController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private ModelMapper modelMapper;

    private Administrator convertToEntity(SaveAdministratorResource resource) {
        return modelMapper.map(resource, Administrator.class);
    }

    private AdministratorResource convertToResource(Administrator entity) {
        return modelMapper.map(entity, AdministratorResource.class);
    }

    @Operation(summary = "Get Administrators", description = "Get All Administrator by Pages", tags = {"Administrators"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Administrators returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Administrators not found")
    })
    @GetMapping("/administrators")
    public Page<AdministratorResource> getAllAdministrators(Pageable pageable){
        Page<Administrator> administratorPage = administratorService.getAll(pageable);
        List<AdministratorResource> resources = administratorPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Administrator", description = "Get Administrator by administratorId", tags = {"Administrators"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrator returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Administrator not found")
    })
    @GetMapping("/administrators/{administratorId}")
    public AdministratorResource getAdministratorById(@PathVariable Long id){
        return convertToResource(administratorService.getById(id));
    }

    @Operation(summary = "Add Administrator", description = "Create new administrator", tags = {"Administrators"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrator created", content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/administrators")
    public AdministratorResource createAdministrator(@Valid @RequestBody SaveAdministratorResource resource) {
        Administrator administrator = convertToEntity(resource);
        return convertToResource(administratorService.create(administrator));
    }

    @Operation(summary = "Update Administrator", description = "Update administrator by id", tags = {"Administrators"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrator Updated", content = @Content(mediaType = "application/json")),
    })
    @PutMapping("/administrators/{administratorId}")
    public AdministratorResource updateAdministrator(@PathVariable Long id, @Valid @RequestBody SaveAdministratorResource resource) {
        Administrator administrator = convertToEntity(resource);
        return convertToResource(administratorService.update(id, administrator));
    }

    @Operation(summary = "Delete Administrator", description = "Deleted administrator by id", tags = {"Administrators"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrator deleted", content = @Content(mediaType = "application/json")),
    })
    @DeleteMapping("/administrators/{administratorId}")
    public ResponseEntity<?> deleteAdministrator(@PathVariable Long id) {
        return administratorService.delete(id);
    }
}   
