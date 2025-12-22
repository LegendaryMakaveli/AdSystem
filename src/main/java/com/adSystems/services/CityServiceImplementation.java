package com.adSystems.services;

import com.adSystems.datas.models.City;
import com.adSystems.datas.repositories.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CityServiceImplementation implements CityService{
    @Autowired
    private CityRepository cityRepository;


    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
