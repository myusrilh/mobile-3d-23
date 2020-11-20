package com.example.emotiondetectorfortherapy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.emotiondetectorfortherapy.databinding.ActivityHomeScreenBinding;

public class HomeScreen extends AppCompatActivity {

    ActivityHomeScreenBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen);

        mBinding.setNama("Hi, Yusril");
    }
}