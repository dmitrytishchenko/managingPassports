package ru.job4j.ManagingPassports.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.ManagingPassports.model.Passport;

import java.util.Collections;
import java.util.List;

@Service
public class PassportApiService implements PassportService {

    @Value("${api-url}")
    private String url;
    private final RestTemplate client;

    public PassportApiService(RestTemplateBuilder builder) {
        this.client = builder.build();
    }

    @Override
    public Passport save(Passport passport) {
        return client.postForEntity(url, passport, Passport.class).getBody();
    }

    @Override
    public boolean update(int id, Passport passport) {
        return client.exchange(
                String.format("%s?id=%s", url, id),
                HttpMethod.PUT,
                new HttpEntity<>(passport),
                Void.class).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    @Override
    public boolean delete(int id) {
        return client.exchange(
                String.format("%s?id=%s", url, id),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    @Override
    public List<Passport> findAll() {
        return getAll(url);
    }

    @Override
    public List<Passport> findBySeries(String series) {
        return getAll(String.format("%s/series?series=%s", url, series));
    }

    @Override
    public List<Passport> unavailable() {
        return getAll(String.format("%s/unavailable", url));
    }

    @Override
    public List<Passport> findReplaceable() {
        return getAll(String.format("%s/replaceable", url));
    }

    private List<Passport> getAll(String url) {
        List<Passport> list = client.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() {
                }
        ).getBody();
        return list == null ? Collections.emptyList() : list;
    }
}
