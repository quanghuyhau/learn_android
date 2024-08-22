//package com.example.atomi.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.viewpager.widget.ViewPager;
//
//import com.example.atomi.R;
//import com.example.atomi.adapter.ObAdapter;
//import com.example.atomi.models.ObModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import me.relex.circleindicator.CircleIndicator;
//
//public class OnBoardingActivity extends AppCompatActivity {
//
//    private ViewPager mViewPager;
//    private CircleIndicator mCircleIndicator;
//    private List<ObModel> mListObModel;
//    private TextView mTitleTextView,skip;
//    private ImageView backLeft,backRight;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_on_boarding);
//
//        mViewPager = findViewById(R.id.view_pager);
//        mCircleIndicator = findViewById(R.id.circle_indicator);
//        mTitleTextView = findViewById(R.id.title);
//        backLeft = findViewById(R.id.arrow_left);
//        backRight = findViewById(R.id.arrow_right);
//        backLeft.setOnClickListener(v -> {
//            int currentItem = mViewPager.getCurrentItem();
//            if (currentItem > 0) {
//                mViewPager.setCurrentItem(currentItem - 1);
//            }
//        });
//
//        backRight.setOnClickListener(v -> {
//            int currentItem = mViewPager.getCurrentItem();
//            if (currentItem < mListObModel.size() - 1) {
//                mViewPager.setCurrentItem(currentItem + 1);
//            }
//        });
//
//        skip =findViewById(R.id.skipTextView);
//        skip.setOnClickListener(v -> {
//            Intent intent = new Intent(OnBoardingActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//        });
//
//
//        mListObModel = getObList();
//        List<String> titles = getTitles();
//
//        ObAdapter adapter = new ObAdapter(mListObModel, titles);
//        mViewPager.setAdapter(adapter);
//
//        mCircleIndicator.setViewPager(mViewPager);
//
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                mTitleTextView.setText(adapter.getTitle(position));
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//        });
//
//        mTitleTextView.setText(adapter.getTitle(0));
//    }
//
//    private List<ObModel> getObList() {
//        List<ObModel> list = new ArrayList<>();
//        list.add(new ObModel(R.drawable.ob1));
//        list.add(new ObModel(R.drawable.ob2));
//        list.add(new ObModel(R.drawable.ob3));
//        return list;
//    }
//
//    private List<String> getTitles() {
//        List<String> titles = new ArrayList<>();
//        titles.add("Unlock the Power Of Future AI");
//        titles.add("Chat With Your Favourite Ai");
//        titles.add("Boost Your Mind Power with Ai");
//        return titles;
//    }
//
//
//}
