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

@SpringBootApplication

public class JSleep
{
    public static Room createRoom(){
        Price price = new Price(100000, 0.5);
        Room room = new Room(1, "Hotel", 30, price, Facility.AC, City.DEPOK, "Jalan Margonda Raya");
        return room;
    }

    class Country {
        public String name;
        public int population;
        public List<String> listOfStates;
    }
    
    public static void main(String[] args) {

        JsonDBEngine.Run(JSleep.class);
        SpringApplication.run(JSleep.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));

        SpringApplication.run(JSleep.class, args);

//        Account testRegex = new Account("Netlab_", "supriyono@ui.ac.id", "sayaReva11");
//        Account testRegexFail = new Account("netlab", "kemas.rafli@gmail2.com.", "sayaReva11");
//        System.out.println(testRegex.validate());
//        System.out.println(testRegexFail.validate());

        for(int i = 0; i < 10; i++)
        {
            ThreadingObject thread = new ThreadingObject("Thread" + i);
            thread.start();
        }

        String filepath = "C:\\Users\\cecil\\Documents\\kuliah\\semester 3\\oop\\JSleep\\JSleep\\json\\account.json";
        try{
            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);
            tableAccount.add(new Account("name", "email", "password"));

            JsonTable.writeJson(tableAccount, filepath);
            tableAccount.forEach(System.out::println);
            System.out.println(tableAccount);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static List<Room> filterByCity(List <Room> list , String search, int page, int pageSize) {
        return Algorithm.paginate(list, page, pageSize, room -> room.city.toString().toLowerCase().contains(search.toLowerCase()));
    }

    public static List<Room> filterByPrice(List<Room> priceList, double minPrice, double maxPrice){
        if(maxPrice == 0){
            return Algorithm.<Room>collect(priceList, temp -> (temp.price.price >= minPrice));
        } return Algorithm.<Room>collect(priceList, temp -> (temp.price.price <= maxPrice) && (temp.price.price >= minPrice));
    }

    public static List<Room> filterByAccountId(List<Room> list, int accountId, int page, int pageSize){
        List<Room> accList = Algorithm.<Room>paginate(list, page, pageSize, a -> a.accountId == accountId);
            return accList;
    }
}



