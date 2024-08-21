package com.example.atomi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.atomi.R;
import com.example.atomi.models.ObModel;

import java.util.List;

public class ObAdapter extends PagerAdapter {

    private List<ObModel> mListOb;
    private List<String> mTitles;

    public ObAdapter(List<ObModel> mListOb, List<String> mTitles) {
        this.mListOb = mListOb;
        this.mTitles = mTitles;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_ob, container, false);

        ImageView imageView = view.findViewById(R.id.img_photo);

        ObModel obModel = mListOb.get(position);
        imageView.setImageResource(obModel.getResource());

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        if (mListOb != null) {
            return mListOb.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public String getTitle(int position) {
        return mTitles.get(position);
    }
}
