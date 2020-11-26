package com.example.emotiondetectorfortherapy;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.emotiondetectorfortherapy.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

public class MainActivity extends AppCompatActivity {
    NavController navController=null;
     ActivityMainBinding mBinding;
     ImageView medicalButton, homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.setNama("Hi, Yusril");

        medicalButton = findViewById(R.id.medical_report_button_homescreen);
        homeButton = findViewById(R.id.home_button_homescreen);

        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_main);


        medicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert navHostFragment != null;
                navController = navHostFragment.getNavController();
//                navController = Navigation.findNavController(view);
//                Fragment medicalFragment = new MedicalReportFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main, medicalFragment).commit();


                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.home_fragment_nav,true).build();
                navController.navigate(R.id.navigate_home_to_medical_report,null,navOptions);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert navHostFragment != null;
                navController = navHostFragment.getNavController();
//                navController = Navigation.findNavController(view);
//                Fragment homeFragment = new HomeFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main, homeFragment).commit();
                navController.navigate(R.id.navigate_medical_report_to_home);
            }
        });

    }
}