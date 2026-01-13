package com.demo.csv;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.List;


public class ReadCSVFile {
    public static void main(String[] args) throws IOException, CsvException {


        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");
        InputStreamReader isr = new InputStreamReader(is);
        CSVReader csvReader = new CSVReader(isr);

        List<String[]> dataList = csvReader.readAll();
        for (String[] dataArray : dataList) {

            // skip empty or invalid rows
            if (dataArray == null || dataArray.length < 2) {
                continue;
            }

            // skip header row
            if ("username".equalsIgnoreCase(dataArray[0])) {
                continue;
            }

            System.out.println(
                    "Username: " + dataArray[0] + ", Password: " + dataArray[1]
            );
        }
    }
}