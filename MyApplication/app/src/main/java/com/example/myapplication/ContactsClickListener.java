package com.example.myapplication;

import androidx.cardview.widget.CardView;

import com.example.myapplication.Models.Contact;

public interface ContactsClickListener {
    void onClick(Contact contact);
    void onLongClick(Contact contact, CardView cardview);
}
