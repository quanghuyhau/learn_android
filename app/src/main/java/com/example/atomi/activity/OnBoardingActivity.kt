package com.example.atomi.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.atomi.R
import com.example.atomi.adapter.ObAdapter
import com.example.atomi.models.ObModel
import me.relex.circleindicator.CircleIndicator

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    private lateinit var mCircleIndicator: CircleIndicator
    private lateinit var mListObModel: List<ObModel>
    private lateinit var mTitleTextView: TextView
    private lateinit var skip: TextView
    private lateinit var backLeft: ImageView
    private lateinit var backRight: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        mViewPager = findViewById(R.id.view_pager)
        mCircleIndicator = findViewById(R.id.circle_indicator)
        mTitleTextView = findViewById(R.id.title)
        backLeft = findViewById(R.id.arrow_left)
        backRight = findViewById(R.id.arrow_right)

        backLeft.setOnClickListener {
            val currentItem = mViewPager.currentItem
            if (currentItem > 0) {
                mViewPager.currentItem = currentItem - 1
            }
        }

        backRight.setOnClickListener {
            val currentItem = mViewPager.currentItem
            if (currentItem < mListObModel.size - 1) {
                mViewPager.currentItem = currentItem + 1
            }
        }

        skip = findViewById(R.id.skipTextView)
        skip.setOnClickListener {
            val intent = Intent(this@OnBoardingActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        mListObModel = getObList()
        val titles = getTitles()

        val adapter = ObAdapter(mListObModel, titles)
        mViewPager.adapter = adapter

        mCircleIndicator.setViewPager(mViewPager)

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                mTitleTextView.text = adapter.getTitle(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        mTitleTextView.text = adapter.getTitle(0)
    }

    private fun getObList(): List<ObModel> {
        return listOf(
            ObModel(R.drawable.ob1),
            ObModel(R.drawable.ob2),
            ObModel(R.drawable.ob3)
        )
    }

    private fun getTitles(): List<String> {
        return listOf(
            "Unlock the Power Of Future AI",
            "Chat With Your Favourite Ai",
            "Boost Your Mind Power with Ai"
        )
    }
}
