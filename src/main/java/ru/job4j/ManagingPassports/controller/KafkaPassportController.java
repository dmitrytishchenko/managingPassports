package ru.job4j.ManagingPassports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.ManagingPassports.service.PassportService;

@Controller
public class KafkaPassportController {

    @Qualifier("passportServiceImpl")
    @Autowired
    private PassportService service;

    @GetMapping("/check")
    public String checkPassports() {
        service.checkPassportByDate();
        return "Passports check and sent";
    }
}
