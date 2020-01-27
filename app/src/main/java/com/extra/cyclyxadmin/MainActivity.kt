package com.extra.cyclyxadmin

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.extra.cyclyxadmin.databinding.ActivityMainBinding
import com.extra.cyclyxadmin.ui.adapter.MainViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    var isOpen : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.lifecycleOwner = this
        val mainVPAdapter = MainViewPager(
            this,
            supportFragmentManager
        )

        binding.fabMain.setOnClickListener {
            if (isOpen){
                tvBtn1.visibility = View.INVISIBLE
                tvBtn2.visibility = View.INVISIBLE
                tvBtn3.visibility = View.INVISIBLE
                tvBtn1.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fab_close))
                tvBtn2.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fab_close))
                tvBtn3.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fab_close))
                btnMot.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fab_close))
                btnTutor.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fab_close))
                btnTips.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fab_close))
                fabMain.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fab_rotate_anticlock))
                btnMot.isClickable = false
                btnTutor.isClickable = false
                btnTips.isClickable = false
                isOpen = false
            } else {
                tvBtn1.visibility = View.VISIBLE
                tvBtn2.visibility = View.VISIBLE
                tvBtn3.visibility = View.VISIBLE
                tvBtn1.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fab_open))
                tvBtn2.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fab_open))
                tvBtn3.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fab_open))
                btnMot.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fab_open))
                btnTutor.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fab_open))
                btnTips.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fab_open))
                fabMain.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fab_rotate_clock))
                btnMot.isClickable = true
                btnTutor.isClickable = true
                btnTips.isClickable = true
                isOpen = true
            }
        }

        binding.container.adapter = mainVPAdapter
        binding.tabs.setupWithViewPager(binding.container)
    }
}
