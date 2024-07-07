package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ContactsClickListener;
import com.example.myapplication.Models.Contact;
import com.example.myapplication.R;

import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsViewHolder>{
    Context context;
    List<Contact> contactsList;
    ContactsClickListener clickListener;

    public ContactsListAdapter(Context context, List<Contact> contactsList, ContactsClickListener
            clickListener) {
        this.context = context;
        this.contactsList = contactsList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactsViewHolder(LayoutInflater.from(context).
                inflate(R.layout.contacts_list, parent, false));
    }

    // enables tracking of what contact user clicked on
    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.textview_personName.setText(contactsList.get(position).getPersonName());
        holder.textview_personName.setSelected(true);
        holder.textview_companyName.setText(contactsList.get(position).getCompanyName());

        // user can edit and see the full details of the contact on a separate activity
        holder.contacts_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(contactsList.get(holder.getAdapterPosition()));
            }
        });

        // when user long clicks, user can delete contact
        holder.contacts_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clickListener.onLongClick(contactsList.get(holder.getAdapterPosition()),
                        holder.contacts_container);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    // method to filter contacts based on search query
    public void filterContacts(List<Contact> filteredContacts) {
        contactsList = filteredContacts;
        notifyDataSetChanged();
    }
}

// makes it easier to reference the different components in the xml file in the search screen
class ContactsViewHolder extends RecyclerView.ViewHolder {

    CardView contacts_container;
    TextView textview_personName;
    TextView textview_companyName;
    public ContactsViewHolder(@NonNull View itemView) {
        super(itemView);
        contacts_container = itemView.findViewById(R.id.contacts_container);
        textview_personName = itemView.findViewById(R.id.textview_personName);
        textview_companyName = itemView.findViewById(R.id.textview_companyName);
    }
}
