package org.example.dao;

import org.example.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao{

    private ResultSet getData(String queryString) throws SQLException {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "[gR1#uO+;7H");
            Statement statement = connection.createStatement();
            return statement.executeQuery(queryString);
    }

    private City generateCity(ResultSet resultSet) throws SQLException {
            return new City(resultSet.getInt("ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("CountryCode"),
                    resultSet.getString("District"),
                    resultSet.getInt("Population")
            );
    }

    @Override
    public City findById(int id) {
        try {
            String statement = "SELECT * FROM City WHERE ID = " + id;
            ResultSet resultSet = getData(statement);
            while (resultSet.next()) {
                return generateCity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<City> findByCode(String code) {
        try {
            String statement = "SELECT * FROM City WHERE CountryCode = '" + code + "'";
            ResultSet resultSet = getData(statement);
            List<City> cities = new ArrayList<>();
            while (resultSet.next()) {
                cities.add(generateCity(resultSet));
            }
            return cities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<City> findByName(String name) {
        try {
            String statement = "SELECT * FROM City WHERE Name = '" + name + "'";
            ResultSet resultSet = getData(statement);
            List<City> cities = new ArrayList<>();
            while (resultSet.next()) {
                cities.add(generateCity(resultSet));
            }
            return cities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<City> findAll() {
        try {
            String statement = "SELECT * FROM City";
            ResultSet resultSet = getData(statement);
            List<City> cities = new ArrayList<>();
            while (resultSet.next()) {
                cities.add(generateCity(resultSet));
            }
            return cities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public City add(City city) {
        return null;
    }

    @Override
    public City update(City city) {
        return null;
    }

    @Override
    public int delete(City city) {
        return 0;
    }
}
