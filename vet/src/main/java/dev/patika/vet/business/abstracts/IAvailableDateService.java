package dev.patika.vet.business.abstracts;


import dev.patika.vet.entities.AvailableDate;

import java.util.List;

public interface IAvailableDateService {

    AvailableDate save(AvailableDate availableDate);

    AvailableDate get(int id);

    AvailableDate update(AvailableDate availableDate);

    boolean delete(int id);

    List<AvailableDate> findAll();
}
