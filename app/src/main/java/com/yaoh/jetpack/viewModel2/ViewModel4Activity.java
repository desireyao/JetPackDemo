package com.yaoh.jetpack.viewModel2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.yaoh.jetpack.R;
import com.yaoh.jetpack.log.LogTool;

import java.util.PrimitiveIterator;

public class ViewModel4Activity extends AppCompatActivity {

    private static final String TAG = "ViewModel4Activity";

    private LiveDataViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model4);

        mViewModel = ViewModelProviders.of(this).get(LiveDataViewModel.class);
        LogTool.LogE_DEBUG(TAG, "mViewModel222---> " + mViewModel);

        subscribe();
    }


    private void subscribe() {
        LogTool.LogE_DEBUG(TAG, "subscribe--->");
        mViewModel.getMessageData().observe(this, new Observer<MessageInfo>() {
            @Override
            public void onChanged(MessageInfo messageInfo) {
                LogTool.LogE_DEBUG("ViewModel3Activity", "onChanged---> messageInfo: " + messageInfo);
            }
        });
    }
}
