package com.example.javafx_project.dao;

import com.example.javafx_project.entities.Owner;

import java.io.FileReader;
import java.util.List;

public interface OwnerDao {
    void insert(Owner owner);

    void update(Owner owner);

    void deleteById(Integer id);

    Owner findById(Integer id);

    List<Owner> findAll();

    List<Owner> readFromTextFile(FileReader fileReader);

    void readFromDatabaseToTextFile();

    void readFromStylSheetAndInsertInDatabase(String path);
}
