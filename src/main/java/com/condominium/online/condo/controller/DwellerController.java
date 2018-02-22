package com.condominium.online.condo.controller;

import com.condominium.online.condo.entity.Dweller;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.service.dweller.DwellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/condo/dweller")
@Api("v1 - Dweller")
public class DwellerController {

    private DwellerService dwellerService;

    @Autowired
    public DwellerController(DwellerService dwellerService) {

        this.dwellerService = dwellerService;
    }

    @PostMapping
    @ApiOperation(value = "Create Dweller")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = false, dataType = "string", paramType = "header") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "BAD_REQUEST"),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR") })
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> insertDweller(@RequestBody Dweller dweller) {

        try {
            dwellerService.insert(dweller);
        } catch (InvalidUserException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Dweller Persisted", HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all Dwellers")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = false, dataType = "string", paramType = "header") })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", responseContainer = "List", response = Dweller.class),
        @ApiResponse(code = 400, message = "BAD_REQUEST"), @ApiResponse(code = 404, message = "NOT_FOUND"),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR") })
    public ResponseEntity<?> findAllDweller() {

        List<Dweller> allDweller = dwellerService.findAll();
        if (allDweller.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(allDweller, HttpStatus.OK);
    }

    @GetMapping("/{dwellerId}")
    @ApiOperation(value = "Get Dwellers by Id")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = false, dataType = "string", paramType = "header") })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", responseContainer = "List", response = Dweller.class),
        @ApiResponse(code = 400, message = "BAD_REQUEST"), @ApiResponse(code = 404, message = "NOT_FOUND"),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR") })
    public ResponseEntity<?> getOneDweller(@PathVariable String dwellerId) {

        Optional<Dweller> oneDweller = dwellerService.findOne(dwellerId);
        if (oneDweller.isPresent()) {
            return new ResponseEntity<>(oneDweller.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{dwellerId}")
    @ApiOperation(value = "Delete Dwellers by Id")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = false, dataType = "string", paramType = "header") })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", responseContainer = "List", response = Dweller.class),
        @ApiResponse(code = 400, message = "BAD_REQUEST"), @ApiResponse(code = 404, message = "NOT_FOUND"),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR") })
    public ResponseEntity<?> deleteDweller(@PathVariable String dwellerId) {

        boolean dwellerDeleted = dwellerService.delete(dwellerId);
        if (dwellerDeleted) {
            return ResponseEntity.noContent()
                                 .build();
        }

        return ResponseEntity.notFound()
                             .build();
    }

    @PutMapping("/{dwellerId}")
    @ApiOperation(value = "Update Dwellers by Id")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = false, dataType = "string", paramType = "header") })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", responseContainer = "List", response = Dweller.class),
        @ApiResponse(code = 400, message = "BAD_REQUEST"), @ApiResponse(code = 404, message = "NOT_FOUND"),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR") })
    public ResponseEntity<?> updateDweller(@PathVariable String dwellerId, @RequestBody Dweller dweller) {

        Optional<Dweller> dwellerOptional;
        try {
            dwellerService.update(dweller);
            return new ResponseEntity<>("Dweller Updated", HttpStatus.OK);
        } catch (InvalidUserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        //TODO: create not found exception
        catch (Exception e) {
            return ResponseEntity.notFound()
                                 .build();
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data Integrity Violation")
    @ExceptionHandler({ DataIntegrityViolationException.class })
    public void DataIntegrityViolation() {

    }
}
