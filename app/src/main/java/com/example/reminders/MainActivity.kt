package com.example.reminders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.reminders.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pager.adapter=PagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when(position){
                0 -> {
                    tab.text ="Passwords"
                    tab.icon=AppCompatResources.getDrawable(this,R.drawable.password_24)}
                1 -> {
                    tab.text="General Info"
                    tab.icon=AppCompatResources.getDrawable(this,R.drawable.info_24)}
            }
        }.attach()
    }

        private inner class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
            override fun getItemCount(): Int = 2

            override fun createFragment(position: Int): Fragment = when(position) {
                 0 -> PasswordFragment()
                 else -> GeneralInfoFragment()
                }
            }
        }

