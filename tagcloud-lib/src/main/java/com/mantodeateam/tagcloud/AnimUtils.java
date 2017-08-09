package com.mantodeateam.tagcloud;

import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by jia on 2017/8/9.
 */

public class AnimUtils {
    private static final String TAG = "AnimUtils";

    public static void circleAnimation(float left, float top, float right, float bottom, final ImageView imageView) {
        Path path = new Path();
        RectF rectF = new RectF(left, top, right, bottom);
        path.addOval(rectF, Path.Direction.CW);
        final PathMeasure mPathMeasure = new PathMeasure(path, true);

        final float[] mCurrentPosition = new float[2];

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(5000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 当插值计算进行时，获取中间的每个值，
                // 这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）
                float value = (Float) animation.getAnimatedValue();
                Log.i("Value::", value + "");
                // ★★★★★获取当前点坐标封装到mCurrentPosition
                // boolean getPosTan(float distance, float[] pos, float[] tan) ：
                // 传入一个距离distance(0<=distance<=getLength())，然后会计算当前距
                // 离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。
                mPathMeasure.getPosTan(value, mCurrentPosition, null);//mCurrentPosition此时就是中间距离点的坐标值
                // 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
                imageView.setTranslationX(mCurrentPosition[0]);
                imageView.setTranslationY(-mCurrentPosition[1]);
            }
        });
        valueAnimator.start();
    }

}
