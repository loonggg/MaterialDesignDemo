package com.loonggg.materialdesigndemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
