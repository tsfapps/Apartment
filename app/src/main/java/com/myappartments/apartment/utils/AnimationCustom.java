package com.myappartments.apartment.utils;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class AnimationCustom {
    public static void animRotate(ImageView tImageView){
        RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.RELATIVE_TO_SELF);
        anim.setDuration(700);

// Start animating the image
//        final ImageView splash = (ImageView) findViewById(R.id.splash);
        tImageView.startAnimation(anim);

// Later.. stop the animation
       // tImageView.setAnimation(null);
    }
}
