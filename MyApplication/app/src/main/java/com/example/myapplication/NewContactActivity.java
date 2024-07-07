package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.Contact;

public class NewContactActivity extends AppCompatActivity {
    EditText editText_personName;
    EditText editText_companyName;
    EditText editText_meetingLocation;
    EditText editText_anecdotes;
    EditText editText_additional_notes;
    ImageView imageview_save;

    Contact contact;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        imageview_save = findViewById(R.id.imageview_save);
        editText_personName = findViewById(R.id.editText_personName);
        editText_companyName = findViewById(R.id.editText_companyName);
        editText_meetingLocation = findViewById(R.id.editText_meetingLocation);
        editText_anecdotes = findViewById(R.id.editText_anecdotes);
        editText_additional_notes = findViewById(R.id.editText_additional_notes);
        imageview_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String personName = editText_personName.getText().toString();
                String companyName = editText_companyName.getText().toString();
                String meetingLocation = editText_meetingLocation.getText().toString();
                String anecdotes = editText_anecdotes.getText().toString();
                String additional_notes = editText_additional_notes.getText().toString();

                if (personName.isEmpty()) {
                    Toast.makeText(NewContactActivity.this,
                            "Please add a person's name",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                contact = new Contact();
                contact.setPersonName(personName);
                contact.setCompanyName(companyName);
                contact.setMeetingLocation(meetingLocation);
                contact.setAnecdotes(anecdotes);
                contact.setAdditionalNotes(additional_notes);

                Intent intent = new Intent();
                intent.putExtra("newContact", contact);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
