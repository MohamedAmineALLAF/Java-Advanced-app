package com.example.javafx_project.services;

import com.example.javafx_project.dao.CarDao;
import com.example.javafx_project.dao.impl.CarDaoImp;
import com.example.javafx_project.entities.Car;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class CarService {
    private CarDao carDao = new CarDaoImp();

    public List<Car> findAll() {
        return carDao.findAll();
    }

    public void save(Car car) {

        carDao.insert(car);

    }
    public void update(Car car) {

        carDao.update(car);

    }
    public void remove(Car car) {
        carDao.deleteById(car.getId());
    }

    public List<Car> readFromTextFile(FileReader fileReader){
        return carDao.readFromTextFile(fileReader);
    }

    public void writeInOutputFile(String path){
        carDao.writeInOutputFile(path);
    }

    public void readFromStyleSheet(File file){
        carDao.readFromStyleSheet(file);
    }

    public void createAndWriteInStyleSheet(File file){
        carDao.CreateAndWriteInStyleSheet(file);
    }

    public void readJsonFromTextFile(String path){
        carDao.readJsonFromTextFile(path);
    }

    public void readFromDatabaseToTextFile(String path){
        carDao.readFromDatabaseToTextFile(path);
    }
}
