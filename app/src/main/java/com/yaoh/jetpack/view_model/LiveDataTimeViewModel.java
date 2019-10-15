package com.yaoh.jetpack.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author yaoh
 * @date Create in 2019-10-11 15:12
 * @description TODO
 */
public class LiveDataTimeViewModel extends AndroidViewModel {

    private static MutableLiveData<TimeInfo> mTimeLivaData = new MutableLiveData<>();

    public LiveDataTimeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<TimeInfo> getTimeInfo() {
        if (mTimeLivaData == null) {
            mTimeLivaData = new MutableLiveData<>();
        }
        return mTimeLivaData;
    }


    public void setTimeInfo(TimeInfo timeInfo) {
        if (mTimeLivaData != null) {
            mTimeLivaData.setValue(timeInfo);
        }
    }


}
