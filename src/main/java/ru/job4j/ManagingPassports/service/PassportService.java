package ru.job4j.ManagingPassports.service;

import ru.job4j.ManagingPassports.model.Passport;

import java.util.List;

public interface PassportService {
    Passport save(Passport passport);

    boolean update(int id, Passport passport);

    boolean delete(int id);

    List<Passport> findAll();

    List<Passport> findBySeries(String series);

    List<Passport> unavailable();

    List<Passport> findReplaceable();

    List<Passport> checkPassportByDate();
}
