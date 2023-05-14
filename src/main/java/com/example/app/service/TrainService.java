package com.example.app.service;


//import com.example.app.entity.Train;
import com.example.app.repository.TrainRepository;
import com.example.app.service.StationService;
//import jakarta.persistence.*;
//import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class TrainService {
    private StationService stationService;

    @Autowired
    private TrainRepository trainRepository;

    private static final String URL = "jdbc:mysql://localhost:3306/railwaystation";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "puMBsJ92!";
//    private static Connection connection;
//      void Connect(){
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
