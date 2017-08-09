package com.mantodeateam.tagcloud.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.mantodeateam.tagcloud.AnimUtils;


public class MainActivity extends AppCompatActivity {
    private ImageView testImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        testOvalAnim();
    }

    private void initView() {
        testImage = (ImageView) findViewById(R.id.testImage);
    }

    private void testOvalAnim() {
        AnimUtils.circleAnimation(0, 0, 300, 600, testImage);
    }
}
