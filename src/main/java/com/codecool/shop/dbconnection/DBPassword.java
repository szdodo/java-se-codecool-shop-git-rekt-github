package com.codecool.shop.dbconnection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBPassword {

    private static final Logger logger = LoggerFactory.getLogger(DBPassword.class);

    public static ArrayList<String> readFile() {
        String filename = "db_config.txt";
        ArrayList<String> records = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
            reader.close();
            return records;

        } catch (Exception e) {
            logger.debug("Exception occurred trying to read {}.", filename);
            e.printStackTrace();
            return null;
        }
    }
}

