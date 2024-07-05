package com.example.myapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel: ViewModel(){
    var personName = MutableLiveData<String>()
    var companyName = MutableLiveData<String>()
    var meetingLocation = MutableLiveData<String>()
    var specificThings = MutableLiveData<String>()
    var additionalNotes = MutableLiveData<String>()
}