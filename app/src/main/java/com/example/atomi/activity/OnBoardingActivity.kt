package com.example.atomi.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.ViewPager
import com.example.atomi.R
import com.example.atomi.adapter.ObAdapter
import com.example.atomi.databinding.ActivityOnBoardingBinding
import com.example.atomi.models.ObModel
import me.relex.circleindicator.CircleIndicator


private lateinit var  binding: ActivityOnBoardingBinding
class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var viewPager: ViewPager
    private lateinit var circleIndicator: CircleIndicator
    private lateinit var listObModel: List<ObModel>
    private lateinit var titleTextView: TextView
    private lateinit var skip: TextView
    private lateinit var backLeft: ImageView
    private lateinit var backRight: ImageView
    private lateinit var switchMode: SwitchCompat
    private lateinit var cardView: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewPager = binding.viewPager
        circleIndicator = binding.circleIndicator
        titleTextView = binding.title
        skip = binding.skipTextView
        backLeft = binding.arrowLeft
        backRight = binding.arrowRight
        switchMode = binding.switchMode
        cardView = binding.cardView

        switchMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.main.setBackgroundColor(resources.getColor(R.color.black))
                titleTextView.setTextColor(resources.getColor(R.color.white))
                skip.setTextColor(resources.getColor(R.color.white))
                cardView.setCardBackgroundColor(resources.getColor(R.color.ob1))
                backRight.setImageResource(R.drawable.svg_ob5)
            } else {
                binding.main.setBackgroundColor(resources.getColor(R.color.white))
                titleTextView.setTextColor(resources.getColor(R.color.black))
                skip.setTextColor(resources.getColor(R.color.subTitle))
                cardView.setCardBackgroundColor(resources.getColor(R.color.white))
                backRight.setImageResource(R.drawable.svg_ob2)

            }
        }



        listObModel = getObList()
        val titles = getTitles()

        val adapter = ObAdapter(listObModel, titles)
        viewPager.adapter = adapter
        circleIndicator.setViewPager(viewPager)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                titleTextView.text = adapter.getTitle(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        titleTextView.text = adapter.getTitle(0)

        skip.setOnClickListener {
            val intent = Intent(this@OnBoardingActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        backLeft.setOnClickListener {
            val currentItem = viewPager.currentItem
            if (currentItem > 0) {
                viewPager.currentItem = currentItem - 1
            }
        }

        backRight.setOnClickListener {
            val currentItem = viewPager.currentItem
            if (currentItem < listObModel.size - 1) {
                viewPager.currentItem = currentItem + 1
            }
        }
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

