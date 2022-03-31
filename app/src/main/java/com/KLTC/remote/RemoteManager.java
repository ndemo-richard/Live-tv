package com.KLTC.remote;

import static com.KLTC.remote.Rawdata.Link;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.KLTC.interfaces.IMainFragment;
import com.KLTC.model.LocalData;


public class RemoteManager {

    private IMainFragment mFragment;
    private Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static RemoteManager INSTANCE = null;


    private RemoteManager(Context mContext,IMainFragment mFragment){
        this.mContext = mContext;
        this.mFragment = mFragment;
    }

    public static RemoteManager newInstance(Context mContext, IMainFragment mFragment){
        return (INSTANCE == null ? INSTANCE= new RemoteManager(mContext,mFragment) : INSTANCE);
    }

    public void featchData(){
        Log.e("TAG", "featchData: " );
        Firebase mRef;
        mRef = new Firebase(Link);  // DataBase Profile Link
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                LocalData s = dataSnapshot.getValue(LocalData.class);
                Log.i("TAG","trying " + s);
                mFragment.notifyOnTvDataAvailable(s.getTv());
                mFragment.notifyOnRadioDataAvailable(s.getRadio());
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }




}
