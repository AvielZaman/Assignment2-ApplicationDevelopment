package com.example.assignment1.Models;

public class Record {

    private int scoreRecord;
    private double latitude;
    private double longitude;
    private String name;

    public Record() {
    }

    public int getScoreRecord() {
        return scoreRecord;
    }
    public String getName() {
        return name;
    }
    public Record setName(String name) {
        this.name = name;
        return this;
    }

    public Record setScoreRecord(int scoreRecord) {
        this.scoreRecord = scoreRecord;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public Record setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Record setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }
}
