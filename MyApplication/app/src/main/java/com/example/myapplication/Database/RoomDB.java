package com.example.myapplication.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.myapplication.Models.Contact;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.Synchronized;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    public abstract ContactDAO contactDAO();

    private static volatile RoomDB database;
    private static final String DATABASE_NAME = "ContactsApp";

    @JvmStatic
    @Synchronized
    public static RoomDB getInstance(Context context) {
        if (database == null) {
            synchronized (RoomDB.class) {
                if (database == null) {
                    database = Room.databaseBuilder(context.getApplicationContext(),
                                    RoomDB.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return database;
    }
}