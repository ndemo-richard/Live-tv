package com.KLTC.fragment;

import static com.KLTC.remote.DevKey.DEVELOPER_KEY;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.leanback.app.BackgroundManager;
import androidx.leanback.app.BrowseSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ImageCardView;
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
        setTitle("Kenyan TV Channels"); // Badge, when set, takes precedent
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
    public void notifyOnRadioDataAvailable(List<Radio> radio) {
        for(Radio r : radio) radioRowAdapter.add(r);

    }

    private class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {

            Tv tv = (Tv)item;

            // get video Id from url
            String videoId = getVideoIdFromUrl(tv.getUrl());

            YoutubeFragment fragment = new YoutubeFragment();
            FragmentManager manager = getParentFragmentManager();

            Bundle bundle = new Bundle();
            String myMessage = videoId;
            bundle.putString("message", myMessage );
            fragment.setArguments(bundle);
            manager.beginTransaction()
                    .replace(R.id.main_browse_fragment, fragment)
                    .addToBackStack(null)
                    .commit();


        }

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