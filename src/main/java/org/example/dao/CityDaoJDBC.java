package org.example.dao;

import org.example.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao{

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "[gR1#uO+;7H");
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
            String statement = "SELECT * FROM City WHERE ID = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(statement);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            String statement = "SELECT * FROM City WHERE CountryCode = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(statement);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            String statement = "SELECT * FROM City WHERE Name = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(statement);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            ResultSet resultSet = getConnection()
                    .createStatement()
                    .executeQuery(statement);

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
        try {
            String statement = "INSERT INTO City (Name, CountryCode, District, Population) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                return findById(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public City update(City city) {
        try {
            String statement = "UPDATE City SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(statement);
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getID());
            int result = preparedStatement.executeUpdate();

            if(result == 1){
                return findById(city.getID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delete(City city) {
        try {
            String statement = "DELETE FROM City WHERE ID = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(statement);
            preparedStatement.setInt(1, city.getID());
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
