package com.KLTC;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.KLTC.fragment.MainFragment;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class App extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment fragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_browse_fragment, fragment)
                    .commit();
        }

    }
}
