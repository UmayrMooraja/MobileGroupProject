package com.tc.parkingproject.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * Group Project created by user
 * student ID: 991522432
 * on 2019-11-14
 */

@Entity(tableName = "parking_table")
public class Parking {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "buildingCode")
    private String buildingCode;

    @ColumnInfo(name = "numHours")
    private String numHours;

    @ColumnInfo(name = "plateNum")
    private String plateNum;

    @ColumnInfo(name = "suitNum")
    private String suitNum;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "numVisits")
    private int numVisits;

    @ColumnInfo(name = "cost")
    private int cost;

    public Parking(String buildingCode, String numHours, String plateNum, String suitNum, Date date, int numVisits, int cost) {
        this.buildingCode = buildingCode;
        this.numHours = numHours;
        this.plateNum = plateNum;
        this.suitNum = suitNum;
        this.date = date;
        this.numVisits = numVisits;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getNumHours() {
        return numHours;
    }

    public void setNumHours(String numHours) {
        this.numHours = numHours;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getSuitNum() {
        return suitNum;
    }

    public void setSuitNum(String suitNum) {
        this.suitNum = suitNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumVisits() {
        return numVisits;
    }

    public void setNumVisits(int numVisits) {
        this.numVisits = numVisits;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "id=" + id +
                ", buildingCode='" + buildingCode + '\'' +
                ", numHours='" + numHours + '\'' +
                ", plateNum='" + plateNum + '\'' +
                ", suitNum='" + suitNum + '\'' +
                ", date='" + date + '\'' +
                ", numVisits='" + numVisits + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
