package com.example.photogallery.photogallery;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

//make the photo to be viewed in square
public class SquareViewItem extends AppCompatImageView {

    public SquareViewItem(Context context) {
        super(context);
    }

    public SquareViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareViewItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}