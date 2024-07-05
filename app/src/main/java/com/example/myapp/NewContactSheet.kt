package com.example.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.myapp.databinding.FragmentNewContactSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewContactSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewContactSheetBinding
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = requireActivity()
        contactViewModel = ViewModelProvider(activity)[ContactViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewContactSheetBinding.inflate(inflater, container, false)

        binding.addContactButton.setOnClickListener {
            saveAction()
        }

        return binding.root
    }

    private fun saveAction() {
        contactViewModel.personName = MutableLiveData(binding.personName.text.toString())
        contactViewModel.companyName = MutableLiveData(binding.companyName.text.toString())
        contactViewModel.meetingLocation = MutableLiveData(binding.meetingEvent.toString())
        contactViewModel.specificThings = MutableLiveData(binding.threeFacts.toString())
        contactViewModel.additionalNotes = MutableLiveData(binding.additionalNotes.toString())
        binding.personName.setText("")
        binding.companyName.setText("")
        binding.meetingEvent.setText("")
        binding.threeFacts.setText("")
        binding.additionalNotes.setText("")
        dismiss()

    }
}