package com.rohan.balloongame;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rohan.balloongame.utils.PixelHelper;

/**
 * Created by Rohan on 21-May-17.
 */

public class Balloon extends ImageView {

    public Balloon(Context context) {
        super(context);
    }

    public Balloon(Context context, int color, int rawHeight) {
        super(context);

        this.setImageResource(R.drawable.balloon);
        this.setColorFilter(color);

        int rawWidth = rawHeight / 2;

        int dpHeight = PixelHelper.pixelsToDp(rawHeight, context);
        int dpWidth = PixelHelper.pixelsToDp(rawWidth, context);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth, dpHeight);
        this.setLayoutParams(params);

    }
}
