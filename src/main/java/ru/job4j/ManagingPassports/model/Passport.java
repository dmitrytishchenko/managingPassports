package ru.job4j.ManagingPassports.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "passports")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String series;
    @Column
    private int number;
    @Column
    private Date endDate;

    public Passport(String series, int number, Date endDate) {
        this.series = series;
        this.number = number;
        this.endDate = endDate;
    }

    public Passport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", series='" + series + '\'' +
                ", number=" + number +
                ", endDate=" + endDate +
                '}';
    }
}
