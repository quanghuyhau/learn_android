package com.example.atomi.activity

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.atomi.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        val splashImage = findViewById<View>(R.id.splash)
        val brainBoxText = findViewById<View>(R.id.tv1)
        val versionText = findViewById<View>(R.id.tv2)

        Handler(Looper.getMainLooper()).postDelayed({
            val fadeOut = ObjectAnimator.ofFloat(splashImage, "alpha", 1f, 0f)
            fadeOut.duration = 1000
            fadeOut.interpolator = AccelerateDecelerateInterpolator()
            fadeOut.start()

            fadeOut.addListener(object : android.animation.AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    splashImage.visibility = View.GONE

                    brainBoxText.visibility = View.VISIBLE

                    val moveUp = ObjectAnimator.ofFloat(
                        brainBoxText,
                        "y",
                        brainBoxText.y,
                        splashImage.y
                    )
                    moveUp.duration = 1000
                    moveUp.interpolator = AccelerateDecelerateInterpolator()
                    moveUp.start()
                }
            })
        }, 1000)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}
