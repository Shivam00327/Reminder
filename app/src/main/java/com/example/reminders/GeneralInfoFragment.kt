package com.example.reminders

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.reminders.databinding.DialogEditRemainderBinding
import com.example.reminders.databinding.FragmentGeneralInfoBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GeneralInfoFragment: Fragment() {
    private lateinit var binding: FragmentGeneralInfoBinding
    private val preferences by lazy { requireActivity().getSharedPreferences("passwords", Context.MODE_PRIVATE) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentGeneralInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayValues()
        binding.cardViewRollNo.setOnClickListener { showEditDialog(PREF_ROLL) }
        binding.cardViewAadhar.setOnClickListener { showEditDialog(PREF_AADHAR) }
        binding.cardViewPhone.setOnClickListener { showEditDialog(PREF_PHONE) }
    }



    private fun displayValues() {
        binding.textViewRollNoValue.text=preferences.getString(PREF_ROLL,null)
        binding.textViewAadharValue.text=preferences.getString(PREF_AADHAR,null)
        binding.textViewPhoneValue.text=preferences.getString(PREF_PHONE,null)

    }


    private fun showEditDialog(preferenceKey: String) {
        val dialogBinding= DialogEditRemainderBinding.inflate(requireActivity().layoutInflater)
        dialogBinding.editTextValue.setText(preferences.getString(preferenceKey,null))
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Update values")
            .setView(dialogBinding.root)

            .setPositiveButton("Save"){ _ , _ ->
                preferences.edit { putString(preferenceKey,dialogBinding.editTextValue.text?.toString()) }
                displayValues()

            }
            .setNegativeButton("Cancel"){ _ , _ ->

            }.show()

    }




    companion object{
        const val PREF_ROLL="pref_roll"
        const val PREF_AADHAR="pref_aadhar"
        const val PREF_PHONE="pref_phone"

    }
}