package com.example.atomi;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

public class CustomToolBar extends ConstraintLayout {

    private ConstraintLayout rlRoot;

    private FrameLayout flBack, flClose;

    private TextView tvTitle;

    private ImageView ivClose;
    private ImageView ivBack;



    public CustomToolBar(Context context) {
        super(context);
        initUI(context, null, 0);
    }

    public CustomToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUI(context, attrs, 0);
    }

    public CustomToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context, attrs, defStyleAttr);
    }



    public void setOnBackClickListener(OnClickListener listener) {
        flBack.setOnClickListener(listener);
    }


    public void setOnCloseClickListener(OnClickListener listener) {
        flClose.setOnClickListener(listener);
    }

    public void seTransparentBg(boolean transparent) {
        if (transparent)
            rlRoot.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primary));
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void hideBackIcon(boolean hide) {
        flBack.setVisibility(hide ? View.GONE : View.VISIBLE);
    }

    public void invisibleBackIcon(boolean invisible) {
        flBack.setVisibility(invisible ? View.INVISIBLE : View.VISIBLE);
        flBack.setEnabled(!invisible);
    }

    public void hiddenCloseIcon(boolean hidden) {
        flClose.setVisibility(hidden ? View.GONE : View.VISIBLE);
    }

    public void showCloseIcon(boolean show) {
        flClose.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        flClose.setEnabled(show);
    }

    public void setCLoseIcon(Drawable drawable) {
        ivClose.setImageDrawable(drawable);
    }

    public void setCloseColorFilter(int color) {
        ivClose.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    public void setCLosePadding(int padding) {
        ivClose.setPadding(padding, padding, padding, padding);
    }

    public void setBgColor(int idColor) {
        rlRoot.setBackgroundColor(ContextCompat.getColor(getContext(), idColor));
    }

    public void setColorIvBack(int idColor) {
        ivBack.setImageTintList(ContextCompat.getColorStateList(getContext(), idColor));
    }

    public void setColorTitle(int idColor) {
        tvTitle.setTextColor(ContextCompat.getColor(getContext(), idColor));
    }

    private void initUI(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, R.layout.custom_toolbar, this);
        rlRoot = findViewById(R.id.rl_root);
        flBack = findViewById(R.id.fl_back);
        tvTitle = findViewById(R.id.tv_title);
        flClose = findViewById(R.id.fl_close);
        ivClose = findViewById(R.id.iv_close);
        ivBack = findViewById(R.id.iv_back);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomToolbar, 0, 0);
        try {
//            String title = typedArray.getString(R.styleable.CommonToolbar_title);
//            int titleColor = typedArray.getColor(R.styleable.CommonToolbar_titleColor, Color.BLACK);
//            tvTitle.setText(title != null ? title : "");
//            tvTitle.setTextColor(titleColor);
        } finally {
            typedArray.recycle();
        }
    }

}