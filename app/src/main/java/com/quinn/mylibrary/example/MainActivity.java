package com.quinn.mylibrary.example;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;

import com.quinn.mylibrary.R;
import com.quinn.mylibrary.utils.TintDrawableUtil;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText editText;
    private AppCompatImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        editText = (AppCompatEditText) findViewById(R.id.edittext);
        imageView = (AppCompatImageView) findViewById(R.id.imageView);

        //替换背景色
        editText.setBackgroundDrawable(TintDrawableUtil.tintDrawable(editText.getBackground(), ColorStateList.valueOf(Color.RED)));
        editText.setBackgroundDrawable(TintDrawableUtil.tintDrawable(editText.getBackground(), ColorStateList.valueOf(Color.parseColor("#03A9F4"))));
        editText.setBackgroundDrawable(TintDrawableUtil.tintDrawable(editText.getBackground(), getResources().getColorStateList(R.color.edittext_tint_colors)));

        final Drawable imageDrawable = getResources().getDrawable(android.R.drawable.ic_menu_manage);
        imageView.setBackgroundDrawable(TintDrawableUtil.tintDrawable(imageDrawable,ColorStateList.valueOf(Color.RED)));
        //防止状态共享，讲两个同样的资源都修改可以加上mutate
        imageView.setBackgroundDrawable(TintDrawableUtil.tintDrawable(imageDrawable.mutate(),ColorStateList.valueOf(Color.RED)));
    }
}
