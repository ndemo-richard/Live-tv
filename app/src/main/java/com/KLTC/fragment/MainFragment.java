package com.KLTC.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.leanback.app.BackgroundManager;
import androidx.leanback.app.BrowseSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import com.google.android.youtube.player.YouTubePlayer;
import java.util.List;

import com.KLTC.R;
import com.KLTC.interfaces.IMainFragment;
import com.KLTC.model.Radio;
import com.KLTC.model.Tv;
import com.KLTC.presenter.TvChannelPresenter;
import com.KLTC.presenter.RadioPresenter;
import com.KLTC.remote.RemoteManager;

public class MainFragment extends BrowseSupportFragment implements IMainFragment {

    private static final String TAG = "MainFragment";
    private static ArrayObjectAdapter mRowsAdapter;
    private static BackgroundManager mBackgroundManager;
    private static ArrayObjectAdapter tvRowAdapter;
    private static ArrayObjectAdapter radioRowAdapter;

    private YouTubePlayer YPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState){
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        setupUIElements();
        setupAdapter();
        loadData();
        setupBackgroundManager();
        setupEventListeners();

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }


    private void setupUIElements() {
        //setBadgeDrawable(getActivity().getResources().getDrawable(R.drawable.videos_by_google_banner));
        setTitle("TV/Radio Ent"); // Badge, when set, takes precedent
        // over title
        setHeadersState(HEADERS_DISABLED);
        setHeadersTransitionOnBackEnabled(false);

        // set fastLane (or headers) background color
        setBrandColor(Color.TRANSPARENT);

        setHeaderPresenterSelector(new PresenterSelector() {
            @Override
            public Presenter getPresenter(Object item) {
                return new TvChannelPresenter();
            }
        });
    }

    private void setupAdapter(){
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

        /* Card Row Adapter*/
        HeaderItem tvHeader = new HeaderItem(1,"TV Channel");
        TvChannelPresenter mTvChannelPresenter = new TvChannelPresenter();
        tvRowAdapter = new ArrayObjectAdapter(mTvChannelPresenter);
        mRowsAdapter.add(new ListRow(tvHeader,tvRowAdapter));

        /* First Row Adapter*/
        HeaderItem radioHeader = new HeaderItem(0,"Radio");
        RadioPresenter mRadioPresenter = new RadioPresenter();
        radioRowAdapter = new ArrayObjectAdapter(mRadioPresenter);
        mRowsAdapter.add(new ListRow(radioHeader, radioRowAdapter));



        setAdapter(mRowsAdapter);

    }

    private void setupBackgroundManager(){
        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());

    }

    private void setupEventListeners(){
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());

    }

    private void loadData(){
        RemoteManager.newInstance(getContext(),this).featchData();

    }


    @Override
    public void notifyOnTvDataAvailable(List<Tv> tv) {
        for (Tv t : tv) tvRowAdapter.add(t);
    }

    @Override
    public void notifyOnRadioDataAvailable(List<Radio> radio) { for(Radio r : radio) radioRowAdapter.add(r); }

    private class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {

            if (item instanceof Tv) {
                Tv tv = (Tv)item;
                String videoId = getVideoIdFromUrl(tv.getUrl());
                String videoTag  = getVideoTagFromUrl(tv.getUrl());
                String videoUrl  = tv.getUrl();
                String videoCheck = "";




                if(videoCheck != videoId){
                    YoutubeFragment fragment = new YoutubeFragment();
                    FragmentManager manager = getParentFragmentManager();

                    Bundle bundle = new Bundle();
                    String myMessage = videoId;
                    bundle.putString("message", myMessage );
                    fragment.setArguments(bundle);
                    manager.beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.main_browse_fragment, fragment)
                            .addToBackStack(null)
                            .commit();


                }else if(videoCheck != videoTag){
                    Fragment mFragment= new DailymotionFragment(getContext());
                    FragmentManager mManager = getParentFragmentManager();
                    Bundle bundle = new Bundle();
                    String myMessage2 = videoTag;
                    bundle.putString("message",myMessage2);
                    mFragment.setArguments(bundle);
                    mManager.beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.main_browse_fragment, mFragment)
                            .addToBackStack(null)
                            .commit();


                }else if(videoCheck != videoUrl) {
                    Fragment mFragments = new TwitchFragment();
                    FragmentManager mManager = getParentFragmentManager();
                    Bundle bundle = new Bundle();
                    String myMessage3 = videoUrl;
                    bundle.putString("message", myMessage3);
                    mFragments.setArguments(bundle);
                    mManager.beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.main_browse_fragment, mFragments)
                            .addToBackStack(null)
                            .commit();
                }else{
                    Log.e("Tag","tumefikia ingine " );
                }

            }else {
                Radio radio = (Radio) item;

                String radioTag  = radio.getUrl();

                Fragment radioFrag = new RadioFragment();
                FragmentManager mManager = getParentFragmentManager();
                Bundle bundle = new Bundle();
                String radioMsg = radioTag;
                bundle.putString("message", radioMsg);
                radioFrag.setArguments(bundle);
                mManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.main_browse_fragment, radioFrag)
                        .addToBackStack(null)
                        .commit();
            }


        }

        //for youtube
        protected String getVideoIdFromUrl(String url){
            if (url.contains("http")){
                if (!url.contains("https")){
                    url = url.replace("http", "https");
                }
            }
            if (url.contains("watch?v=")){
                if(url.contains("&list=")){
                    return url.substring(url.indexOf("?v=")+3, url.indexOf("&list="));
                }
                return url.replace("https://www.youtube.com/watch?v=", "");
            }else if(url.contains("https://youtu.be/")){
                return url.replace("https://youtu.be/", "");
            }
            return "";
        }

        //for dailymotion
        protected String getVideoTagFromUrl(String url){
            if (url.contains("http")){
                if (!url.contains("https")){
                    url = url.replace("http", "https");
                }
            }
            if (url.contains("//dai.ly/")){
                if(url.contains("&list=")){
                    return url.substring(url.indexOf("//dai.ly/")+0, url.indexOf("&list="));
                }
                return url.replace("https://dai.ly/", "");
            }else if(url.contains("https://dai.ly/")){
                return url.replace("https://dai.ly/", "");
            }
            return "";
        }
    }


    private class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {

            if (item instanceof Tv) {
                mBackgroundManager.setDrawable(getResources().getDrawable(R.drawable.bg_tv));
            }else {
                mBackgroundManager.setDrawable(getResources().getDrawable(R.drawable.bg_radio));
            }
        }
    }

}