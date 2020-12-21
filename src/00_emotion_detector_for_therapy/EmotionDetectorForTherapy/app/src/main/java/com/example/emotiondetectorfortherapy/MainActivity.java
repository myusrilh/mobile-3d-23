package com.example.emotiondetectorfortherapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.emotiondetectorfortherapy.databinding.ActivityMainBinding;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

public class MainActivity extends AppCompatActivity {
    NavController navController = null;
    ActivityMainBinding mBinding;
    ImageView medicalButton, homeButton, cameraButton, profileButton;
    LinearLayout progressBarContainer;

    NavDestination fragment = null;
//    int fragmentID = 0;
    String fragmentLabel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.setNama("Hi, Yusril");

        medicalButton = findViewById(R.id.medical_report_button_homescreen);
        homeButton = findViewById(R.id.home_button_homescreen);
        cameraButton = findViewById(R.id.camera_home_button);
        profileButton = findViewById(R.id.profile_button_homescreen);

        progressBarContainer = findViewById(R.id.progress_bar_home_container);

        DrawableCompat.setTint(
                DrawableCompat.wrap(homeButton.getDrawable()),
                ContextCompat.getColor(getApplicationContext(), android.R.color.white)
        );

        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_main);

        Intent getIntent = getIntent();
        Bundle b = getIntent.getExtras();


        if (b != null) {
            assert navHostFragment != null;
            navController = navHostFragment.getNavController();

            if (b.get("page").equals("home")) {
                changeButtonColor(homeButton);
            } else if (b.get("page").equals("medical")) {
                changeButtonColor(medicalButton);
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.home_fragment_nav, true).build();
                navController.navigate(R.id.navigate_home_to_medical_report, null, navOptions);
            } else if (b.getString("page").equals("profile")) {
                changeButtonColor(profileButton);
                progressBarContainer.setVisibility(View.GONE);
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.home_fragment_nav, true).build();
                navController.navigate(R.id.navigate_home_to_profile, null, navOptions);
            }
        }




        medicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeButtonColor(view);
                progressBarContainer.setVisibility(View.VISIBLE);
                assert navHostFragment != null;
                navController = navHostFragment.getNavController();
//                navController = Navigation.findNavController(view);
//                Fragment medicalFragment = new MedicalReportFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main, medicalFragment).commit();

                fragment = navController.getCurrentDestination();
//                fragmentID = fragment.getId();
                fragmentLabel = fragment != null ? fragment.getLabel().toString() : null;


                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.home_fragment_nav, true).build();
                if (fragmentLabel.equals("fragment_home")) {
                    navController.navigate(R.id.navigate_home_to_medical_report, null, navOptions);
                } else if (fragmentLabel.equals("fragment_profile")) {
                    navController.navigate(R.id.navigate_profile_to_medical_report, null, navOptions);
                }
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeButtonColor(view);
                progressBarContainer.setVisibility(View.VISIBLE);
                assert navHostFragment != null;
                navController = navHostFragment.getNavController();
//                navController = Navigation.findNavController(view);
//                Fragment homeFragment = new HomeFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main, homeFragment).commit();

                fragment = navController.getCurrentDestination();
//                fragmentID = fragment.getId();
                fragmentLabel = fragment != null ? fragment.getLabel().toString() : null;

                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.home_fragment_nav, true).build();
                if (fragmentLabel.equals("fragment_medical_report")) {
                    navController.navigate(R.id.navigate_medical_report_to_home, null, navOptions);
                } else if (fragmentLabel.equals("fragment_profile")) {
                    navController.navigate(R.id.navigate_profile_to_home, null, navOptions);
                }
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeButtonColor(view);

                assert navHostFragment != null;
                navController = navHostFragment.getNavController();
                progressBarContainer.setVisibility(View.GONE);
//                navController = Navigation.findNavController(view);
//                Fragment homeFragment = new HomeFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main, homeFragment).commit();

                fragment = navController.getCurrentDestination();
//                fragmentID = fragment.getId();
                fragmentLabel = fragment != null ? fragment.getLabel().toString() : null;

                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.home_fragment_nav, true).build();
                if (fragmentLabel.equals("fragment_home")) {
                    navController.navigate(R.id.navigate_home_to_profile, null, navOptions);
                } else if (fragmentLabel.equals("fragment_medical_report")) {
                    navController.navigate(R.id.navigate_medical_report_to_profile, null, navOptions);
                }
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeButtonColor(view);

                Intent i = new Intent(getApplicationContext(), ScanFaceActivity.class);
                i.putExtra("startCamera",true);
                startActivity(i);
                finish();
            }
        });


    }

//    private Fragment getVisibleFragment() {
//        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
//        List<Fragment> fragments = fragmentManager.getFragments();
//        for (Fragment fragment : fragments) {
//            if (fragment != null && fragment.isVisible())
//                return fragment;
//        }
//        return null;
//    }

    public void changeButtonColor(View view) {
        switch (view.getId()) {
            case R.id.home_button_homescreen:
                DrawableCompat.setTint(
                        DrawableCompat.wrap(medicalButton.getDrawable()),
                        ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)
                );
                DrawableCompat.setTint(
                        DrawableCompat.wrap(profileButton.getDrawable()),
                        ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)
                );
                DrawableCompat.setTint(
                        DrawableCompat.wrap(homeButton.getDrawable()),
                        ContextCompat.getColor(getApplicationContext(), android.R.color.white)
                );
                break;
            case R.id.medical_report_button_homescreen:
                DrawableCompat.setTint(
                        DrawableCompat.wrap(medicalButton.getDrawable()),
                        ContextCompat.getColor(getApplicationContext(), android.R.color.white)
                );
                DrawableCompat.setTint(
                        DrawableCompat.wrap(profileButton.getDrawable()),
                        ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)
                );
                DrawableCompat.setTint(
                        DrawableCompat.wrap(homeButton.getDrawable()),
                        ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)
                );
                break;
            case R.id.profile_button_homescreen:
                DrawableCompat.setTint(
                        DrawableCompat.wrap(medicalButton.getDrawable()),
                        ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)
                );
                DrawableCompat.setTint(
                        DrawableCompat.wrap(profileButton.getDrawable()),
                        ContextCompat.getColor(getApplicationContext(), android.R.color.white)
                );
                DrawableCompat.setTint(
                        DrawableCompat.wrap(homeButton.getDrawable()),
                        ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)
                );
                break;
            case R.id.camera_home_button:
                DrawableCompat.setTint(
                        DrawableCompat.wrap(medicalButton.getDrawable()),
                        ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)
                );
                DrawableCompat.setTint(
                        DrawableCompat.wrap(profileButton.getDrawable()),
                        ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)
                );
                DrawableCompat.setTint(
                        DrawableCompat.wrap(homeButton.getDrawable()),
                        ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)
                );
                break;
        }
    }

}