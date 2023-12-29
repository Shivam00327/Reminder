package com.example.reminders

import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.reminders.databinding.DialogEditRemainderBinding
import com.example.reminders.databinding.FragmentPasswordBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.prefs.Preferences

class PasswordFragment:Fragment (){
    private lateinit var binding: FragmentPasswordBinding
    private val preferences by lazy { requireActivity().getSharedPreferences("passwords", Context.MODE_PRIVATE) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentPasswordBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayValues()
        binding.cardViewWifi.setOnClickListener{showEditDialog(PREF_WIFI,"Wifi password")}
        binding.cardViewGmail.setOnClickListener{showEditDialog(PREF_GMAIL,"Gmail Password")}
        binding.cardViewCollege.setOnClickListener{showEditDialog(PREF_COLLEGE,"College i'd Password")}
        binding.cardViewLaptop.setOnClickListener{showEditDialog(PREF_LAPTOP,"MAcBook PassWord")}
    }



    private fun displayValues() {
        binding.textViewWifiValue.text=preferences.getString(PREF_WIFI,null)
        binding.textViewGmailValue.text=preferences.getString(PREF_GMAIL,null)
        binding.textViewCollegeValue.text=preferences.getString(PREF_COLLEGE,null)
        binding.textViewLaptopValue.text=preferences.getString(PREF_LAPTOP,null)

    }
    private fun showEditDialog(preferenceKey: String,name:String) {


        val dialogBinding=DialogEditRemainderBinding.inflate(requireActivity().layoutInflater)
        dialogBinding.editTextValue.setText(preferences.getString(preferenceKey,null))
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(name)
            .setView(dialogBinding.root)

            .setPositiveButton("Save"){ _ , _ ->
                preferences.edit { putString(preferenceKey,dialogBinding.editTextValue.text?.toString()) }
                displayValues()

            }
            .setNegativeButton("Cancel"){ _ , _ ->

            }.show()


    }
    companion object{
        const val PREF_WIFI="pref_wifi"
        const val PREF_GMAIL="pref_gmail"
        const val PREF_COLLEGE="pref_college"
        const val PREF_LAPTOP="pref_laptop"
    }
}