package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {

        List<City> cityPage = cityRepository.findAll(Sort.by("name"));

        return cityPage.stream().map(city -> new CityDTO(city)).collect(Collectors.toList());
    }

    @Transactional
    public CityDTO insert(CityDTO cityDTO) {

        City city = new City(null, cityDTO.getName());
        city = cityRepository.save(city);

        return new CityDTO(city);
    }

    public void delete(Long id) {
        try {
            cityRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new ObjNotFoundException("City not found!");
        }

        catch(DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("breach of data integrity!");
        }
    }
}