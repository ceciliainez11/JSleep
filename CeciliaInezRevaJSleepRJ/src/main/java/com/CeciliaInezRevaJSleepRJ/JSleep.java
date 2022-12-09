package com.CeciliaInezRevaJSleepRJ;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.FileWriter;
import com.google.gson.*;

import com.CeciliaInezRevaJSleepRJ.dbjson.JsonDBEngine;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class JSleep {
    public static void main(String[] args) {

        JsonDBEngine.Run(JSleep.class);
        SpringApplication.run(JSleep.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
    }
}