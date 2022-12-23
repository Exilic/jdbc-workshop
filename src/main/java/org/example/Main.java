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


    }
}