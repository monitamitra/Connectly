package com.example.myapplication.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.Models.Contact;

import java.util.List;

@Dao
public interface ContactDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addContact(Contact contact);

    @Query("SELECT * FROM contacts ORDER BY id DESC")
    List<Contact> getAllContacts();
    @Query("UPDATE contacts SET personName = :personName, companyName = :companyName, " +
            "meetingLocation = :meetingLocation, anecdotes = :anecdotes, " +
            "additionalNotes = :additionalNotes WHERE ID = :id")
    void updateContact(int id, String personName, String companyName, String meetingLocation,
                String anecdotes, String additionalNotes);

    @Delete
    void deleteContact(Contact contact);

}
