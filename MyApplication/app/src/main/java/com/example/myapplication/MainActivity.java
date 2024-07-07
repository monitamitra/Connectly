package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.ContactsListAdapter;
import com.example.myapplication.Database.RoomDB;
import com.example.myapplication.Models.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ContactsListAdapter contactsListAdapter;
    List<Contact> contacts = new ArrayList<>();
    RoomDB database;
    FloatingActionButton fab_add_contact;

    ActivityResultLauncher<Intent> addContactLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_container);
        fab_add_contact = findViewById(R.id.fab_add_contact);
        database = RoomDB.getInstance(this);
        contacts = database.contactDAO().getAllContacts();

        updateRecyclerView(contacts);

        addContactLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Handle the result from NewContactActivity
                        Intent data = result.getData();

                        Contact newContact = (Contact) data.getSerializableExtra("newContact");
                        database.contactDAO().addContact(newContact);
                        contacts.clear();
                        contacts.addAll(database.contactDAO().getAllContacts());
                        contactsListAdapter.notifyDataSetChanged();
                    }
                });

        fab_add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        NewContactActivity.class);
                addContactLauncher.launch(intent);
            }
        });

    }

    private void updateRecyclerView(List<Contact> contacts) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                RecyclerView.VERTICAL, false));
        contactsListAdapter = new ContactsListAdapter(MainActivity.this, contacts,
                contactsClickListener);
        recyclerView.setAdapter(contactsListAdapter);
    }

    private final ContactsClickListener contactsClickListener = new ContactsClickListener() {
        @Override
        public void onClick(Contact contact) {

        }

        @Override
        public void onLongClick(Contact contact, CardView cardview) {

        }
    };
}