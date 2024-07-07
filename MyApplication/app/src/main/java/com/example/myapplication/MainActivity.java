package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.SearchView;

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

    SearchView searchHome;

    Contact selectedContact;

    ActivityResultLauncher<Intent> addContactLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_container);
        fab_add_contact = findViewById(R.id.fab_add_contact);
        searchHome = findViewById(R.id.searchHome);
        database = RoomDB.getInstance(this);
        contacts = database.contactDAO().getAllContacts();

        updateRecyclerView(contacts);

        addContactLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == 101) {
                        // Handle the result from NewContactActivity
                        Intent data = result.getData();

                        Contact newContact = (Contact) data.getSerializableExtra("newContact");
                        database.contactDAO().addContact(newContact);
                        contacts.clear();
                        contacts.addAll(database.contactDAO().getAllContacts());
                        contactsListAdapter.notifyDataSetChanged();
                    } else if (result.getResultCode() == 102) {
                        Intent data = result.getData();
                        Contact newContact = (Contact) data.getSerializableExtra("oldContact");
                        database.contactDAO().updateContact(newContact.getID(),
                                newContact.getPersonName(), newContact.getCompanyName(),
                                newContact.getMeetingLocation(), newContact.getAnecdotes(),
                                newContact.getAdditionalNotes());
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

        searchHome.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

    }

    private void filter(String text) {
        List<Contact> filteredContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getPersonName().toLowerCase().contains(text.toLowerCase()) ||
            contact.getCompanyName().toLowerCase().contains(text.toLowerCase())) {
                filteredContacts.add(contact);
            }
        }
        contactsListAdapter.filterContacts(filteredContacts);
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
            Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
            intent.putExtra("oldContact", contact);
            addContactLauncher.launch(intent);
        }

        @Override
        public void onLongClick(Contact contact, CardView cardview) {
            selectedContact = new Contact();
        }
    };
}