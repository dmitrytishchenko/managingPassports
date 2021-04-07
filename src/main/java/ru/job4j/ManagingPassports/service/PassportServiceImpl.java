package ru.job4j.ManagingPassports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.job4j.ManagingPassports.model.Passport;
import ru.job4j.ManagingPassports.repository.PassportRepository;

import java.util.Date;
import java.util.List;

@Service
public class PassportServiceImpl implements PassportService {
    @Autowired
    private final PassportRepository repository;
    @Autowired
    private KafkaTemplate<Integer, Passport> kafkaTemplate;

    public PassportServiceImpl(PassportRepository repository) {
        this.repository = repository;
    }

    @Override
    public Passport save(Passport passport) {
        repository.save(passport);
        return passport;
    }

    @Override
    public boolean update(int id, Passport passport) {
        boolean result = false;
        if (repository.findById(id).isPresent()) {
            Passport changedPassport = repository.findById(id).get();
            changedPassport.setSeries(passport.getSeries());
            changedPassport.setNumber(passport.getNumber());
            changedPassport.setEndDate(passport.getEndDate());
            repository.save(changedPassport);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        if (repository.findById(id).isPresent()) {
            repository.delete(repository.findById(id).get());
            result = true;
        }
        return result;
    }

    @Override
    public List<Passport> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Passport> findBySeries(String series) {
        return repository.findPassportBySeries(series);
    }

    @Override
    public List<Passport> unavailable() {
        return repository.findPassportByEndDate();
    }

    @Override
    public List<Passport> findReplaceable() {
        return repository.findPassportsByEndDateFor3Months(getDateFor3Months());
    }

    private Date getDateFor3Months() {
        Date current_date = new Date();
        long before3Months = current_date.getTime() - 90 * 24 * 60 * 60 * 1000;
        return new Date(before3Months);
    }

    @Override
//    @Scheduled(cron = "0 0 10 * * *") - every day
    @Scheduled(cron = "*/20 * * * * *" )
    public List<Passport> checkPassportByDate() {
        if (findReplaceable().size() != 0) {
            System.out.println("There are some passports for replacement: ");
            for (Passport p : findReplaceable()) {
                kafkaTemplate.send("passport", p);
            }
        } else {
            System.out.println("There are no passports for replacement");
        }
        return findReplaceable();
    }
}
