package com.example.myapplication.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "contacts")
public class Contact implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int ID = 0;

    @ColumnInfo(name = "personName")
    String personName = "";

    @ColumnInfo(name = "companyName")
    String companyName = "";

    @ColumnInfo(name = "meetingLocation")
    String meetingLocation = "";

    @ColumnInfo(name = "anecdotes")
    String anecdotes = "";

    @ColumnInfo(name = "additionalNotes")
    String additionalNotes = "";

    public int getID() {
        return ID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMeetingLocation() {
        return meetingLocation;
    }

    public void setMeetingLocation(String meetingLocation) {
        this.meetingLocation = meetingLocation;
    }

    public String getAnecdotes() {
        return anecdotes;
    }

    public void setAnecdotes(String anecdotes) {
        this.anecdotes = anecdotes;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
}
