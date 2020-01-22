package com.extra.cyclyxadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.extra.cyclyxadmin.databinding.ActivityMainBinding
import com.extra.cyclyxadmin.ui.adapter.MainViewPager

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.lifecycleOwner = this
        val mainVPAdapter = MainViewPager(
            this,
            supportFragmentManager
        )
        binding.container.adapter = mainVPAdapter
        binding.tabs.setupWithViewPager(binding.container)
    }
}
