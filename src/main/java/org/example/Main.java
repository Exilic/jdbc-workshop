package org.example;

import org.example.dao.CityDaoJDBC;
import org.example.model.City;

import java.sql.*;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        CityDaoJDBC cityDaoJDBC = new CityDaoJDBC();

        City cityById = cityDaoJDBC.findById(233);
        System.out.println(cityById.getName());

        System.out.println("--------------");

        List<City> codeCities = cityDaoJDBC.findByCode("ETH");
        for (City city : codeCities
             ) {
            System.out.println(city.getName());
        }

        System.out.println("--------------");

        List<City> nameCities = cityDaoJDBC.findByName("San Carlos");
        for (City city : nameCities
        ) {
            System.out.println(city.getID());
        }

        System.out.println("--------------");

        List<City> allCities = cityDaoJDBC.findAll();
        System.out.println(allCities.size());

        System.out.println("--------------");

        City newCity = new City("Good", "DOM", "Puerta Plata", 1310);
        City storedCity = cityDaoJDBC.add(newCity);
        System.out.println(storedCity.getName());

        System.out.println("--------------");

        City cityToUpdate = new City(4082, "Grellic", "DOM", "Puerta Plata", 1530);
        City cityAdded = cityDaoJDBC.update(cityToUpdate);
        System.out.println(cityAdded.getName());
        System.out.println(cityAdded.getCountryCode());
        System.out.println(cityAdded.getDistrict());
        System.out.println(cityAdded.getPopulation());

        System.out.println("--------------");


        City cityToDelete = new City(4082, "Grellic", "DOM", "Puerta Plata", 1530);
        int cityDeleted = cityDaoJDBC.delete(cityToDelete);
        System.out.println(cityDeleted == 1 ? "City was deleted" : "City wasn't deleted" );

        System.out.println("--------------");
    }
}