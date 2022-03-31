package com.KLTC.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import androidx.leanback.widget.BaseCardView;
import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.Presenter;

import com.KLTC.R;
import com.KLTC.model.Radio;
import com.KLTC.model.Tv;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class RadioPresenter extends Presenter{

    private static Context mContext;
    private static int CARD_WIDTH = 200;
    private static int CARD_HEIGHT = 200;

    static class PicassoImageCardViewTarget implements Target {
        private ImageCardView mImageCardView;

        public PicassoImageCardViewTarget(ImageCardView mImageCardView) {
            this.mImageCardView = mImageCardView;
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Drawable bitmapDrawable = new BitmapDrawable(mContext.getResources(), bitmap);
            mImageCardView.setMainImage(bitmapDrawable);

        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            mImageCardView.setMainImage(errorDrawable);

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    }

    private static class ViewHolder extends Presenter.ViewHolder {
        private Radio radio;
        private ImageCardView mCardView;
        private Drawable mDefaultCardImage;
        private PicassoImageCardViewTarget mImageCardViewTarget;

        public ViewHolder(View view) {
            super(view);
            mCardView=(ImageCardView)view;
            mImageCardViewTarget = new RadioPresenter.PicassoImageCardViewTarget(mCardView);
            mDefaultCardImage = mContext
                    .getResources()
                    .getDrawable(R.drawable.filmi);
        }

        public Radio getRadio() {
            return radio;
        }
        public void setRadio(Radio radio) {
            this.radio = radio;
        }

        public ImageCardView getmCardView(){
            return mCardView;
        }
        protected void updateCardViewImage(String url) {

            Picasso.get()
                    .load(url)
                    .resize(CARD_WIDTH * 2, CARD_HEIGHT * 2)
                    .centerCrop()
                    .error(mDefaultCardImage)
                    .into(mImageCardViewTarget);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {

        mContext = parent.getContext();
        ImageCardView mCardView = new ImageCardView(mContext);
        mCardView.setCardType(BaseCardView.CARD_TYPE_INFO_UNDER);
        mCardView.setInfoVisibility(BaseCardView.CARD_REGION_VISIBLE_ALWAYS);
        mCardView.setFocusable(true);
        mCardView.setFocusableInTouchMode(true);
        mCardView.setBackgroundColor(Color.WHITE);
        return new ViewHolder(mCardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        Radio radio = (Radio) item;
        ((ViewHolder) viewHolder).setRadio(radio);

        ((ViewHolder) viewHolder).mCardView.setTitleText(radio.getTitle());
        ((ViewHolder) viewHolder).mCardView.setContentText(radio.getDescription());
        ((ViewHolder) viewHolder).mCardView.setMainImageDimensions(CARD_WIDTH,CARD_HEIGHT);
        ((ViewHolder) viewHolder).updateCardViewImage(radio.getImageUrl());

    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }
}
