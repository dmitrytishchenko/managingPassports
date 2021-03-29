package ru.job4j.ManagingPassports.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.ManagingPassports.model.Passport;
import ru.job4j.ManagingPassports.service.PassportService;

import java.util.List;

@RestController
public class PassportController {

    private final PassportService service;

    public PassportController(@Qualifier("passportServiceImpl") PassportService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String index() {
        return "Hello";
    }

    @PostMapping()
    public Passport save(@RequestBody Passport passport) {
        return service.save(passport);
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestParam int id, @RequestBody Passport passport) {
        boolean status = service.update(id, passport);
        return ResponseEntity.status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> delete(@RequestParam int id) {
        boolean status = service.delete(id);
        return ResponseEntity.status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/series")
    public List<Passport> getBySeries(@RequestParam String series) {
        return service.findBySeries(series);
    }

    @GetMapping()
    public List<Passport> getAll() {
        return service.findAll();
    }

    @GetMapping("/unavailable")
    public List<Passport> getUnavailable() {
        return service.unavailable();
    }

    @GetMapping("/replaceable")
    public List<Passport> getReplaceable() {
        return service.findReplaceable();
    }
}
