package com.KLTC.interfaces;

import com.KLTC.model.Radio;
import com.KLTC.model.Tv;

import java.util.List;

public interface IMainFragment {

    void notifyOnTvDataAvailable(List<Tv> tv);

    void notifyOnRadioDataAvailable(List<Radio> radio);
}