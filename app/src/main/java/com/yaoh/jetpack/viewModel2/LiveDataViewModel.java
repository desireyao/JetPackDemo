package com.yaoh.jetpack.viewModel2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author yaoh
 * @date Create in 2020-01-06 18:10
 * @description TODO
 */
public class LiveDataViewModel extends ViewModel {

    private static final int ONE_SECOND = 1000;

    private MutableLiveData<MessageInfo> mMessageData = new MutableLiveData<>();

    public MutableLiveData<MessageInfo> getMessageData() {
        return mMessageData;
    }

    public void sendMessage(MessageInfo value) {
        mMessageData.setValue(value);
    }


}
