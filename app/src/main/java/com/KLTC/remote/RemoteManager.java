package com.KLTC.remote;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import com.KLTC.R;
import com.KLTC.interfaces.IMainFragment;
import com.KLTC.model.LocalData;
import com.KLTC.util.Utils;

public class RemoteManager {

    private IMainFragment mFragment;
    private Context mContext;
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
        String json = Utils.inputStreamToString(mContext.getResources().openRawResource(
                R.raw.data));
        LocalData dataRow = new Gson().fromJson(json, LocalData.class);
        mFragment.notifyOnTvDataAvailable(dataRow.getTv());
        mFragment.notifyOnRadioDataAvailable(dataRow.getRadio());
    }
}
