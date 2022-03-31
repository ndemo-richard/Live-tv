package com.KLTC;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.KLTC.fragment.MainFragment;
import com.firebase.client.Firebase;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class App extends FragmentActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);



        if (savedInstanceState == null) {
            Fragment fragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_browse_fragment, fragment)
                    .commit();
        }


    }

}
