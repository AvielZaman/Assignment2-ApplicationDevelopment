package com.example.assignment1.Logic;


import com.example.assignment1.Models.Record;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class DataManager {
    private static GameManager gameManager;

    public static ArrayList<Record> getRecords() {
        ArrayList<Record> records = new ArrayList<>();

        records.add(new Record()
                .setScoreRecord(15)
                .setLatitude(32.112923)
                .setLongitude(34.8182147));

        records.add(new Record()
                .setScoreRecord(22)
                .setLatitude(32.112223)
                .setLongitude(34.8187147));

        records.add(new Record()
                .setScoreRecord(43)
                .setLatitude(32.113923)
                .setLongitude(34.8184147));

        records.add(new Record()
                .setScoreRecord(17)
                .setLatitude(32.112983)
                .setLongitude(34.8182107));

        records.add(new Record()
                .setScoreRecord(7)
                .setLatitude(32.112973)
                .setLongitude(34.8172147));

        records.add(new Record()
                .setScoreRecord(10)
                .setLatitude(32.113923)
                .setLongitude(34.8162147));

        records.add(new Record()
                .setScoreRecord(50)
                .setLatitude(32.112923)
                .setLongitude(34.8152147));

        records.add(new Record()
                .setScoreRecord(22)
                .setLatitude(32.119923)
                .setLongitude(34.8112147));

        records.add(new Record()
                .setScoreRecord(34)
                .setLatitude(32.118923)
                .setLongitude(34.8188147));

        records.add(new Record()
                .setScoreRecord(8)
                .setLatitude(32.112963)
                .setLongitude(34.8182547));

        return records;
    }
}
