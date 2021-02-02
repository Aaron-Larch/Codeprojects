package com.pdf.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdf.demo.model.City;
import com.pdf.demo.repository.CityRepository;

@Service
public class CityService implements ICityService {

    @Autowired
    private CityRepository repository;

    public List<City> findAll() {
        return (List<City>) repository.findAll();
    }
}